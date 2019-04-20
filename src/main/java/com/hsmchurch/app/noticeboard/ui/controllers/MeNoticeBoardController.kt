package com.hsmchurch.app.noticeboard.ui.controllers

import com.hsmchurch.app.noticeboard.application.NoticeService
import com.hsmchurch.app.noticeboard.ui.request.NoticeUpdateRequest
import com.hsmchurch.app.noticeboard.ui.request.NoticeUploadRequest
import com.hsmchurch.app.security.AuthenticationHelper.currentUserId
import com.hsmchurch.app.security.AuthenticationHelper.currentUserIsAdmin
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RequestMapping(path = ["/api/v1/me/notices"])
@RestController
class MeNoticeBoardController constructor(
        private val noticeService: NoticeService
) {

    @PostMapping
    fun register(@RequestBody noticeUploadRequest: NoticeUploadRequest,
                 b: UriComponentsBuilder): ResponseEntity<Void> {
        val savedNotice = noticeService.upload(noticeUploadRequest, currentUserId())
        val uriComponents = b.path("/api/v1/notices/{id}").buildAndExpand(savedNotice.id)

        return ResponseEntity.created(uriComponents.toUri()).build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long,
               @RequestBody noticeUpdateRequest: NoticeUpdateRequest): ResponseEntity<Void> {
        noticeService.updateNotice(noticeUpdateRequest, id, currentUserId())

        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        if (currentUserIsAdmin()) {
            return if (noticeService.delete(id)) ResponseEntity.noContent().build()
            else ResponseEntity.badRequest().build()
        }
        return if (noticeService.delete(id, currentUserId())) ResponseEntity.noContent().build()
        else ResponseEntity.badRequest().build()
    }

}
