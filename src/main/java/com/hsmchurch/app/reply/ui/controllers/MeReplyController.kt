package com.hsmchurch.app.reply.ui.controllers

import com.hsmchurch.app.reply.application.ReplyService
import com.hsmchurch.app.reply.ui.ReplyApplyRequest
import com.hsmchurch.app.reply.ui.ReplyUpdateRequest
import com.hsmchurch.app.reply.ui.ResponseDtos
import com.hsmchurch.app.security.AuthenticationHelper.currentUserId
import com.hsmchurch.app.security.AuthenticationHelper.currentUserIsAdmin
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping(path = ["/api/v1/me/replies"])
class MeReplyController constructor(private val replyService: ReplyService){

    @PostMapping
    fun apply(@RequestBody replyApplyRequest: ReplyApplyRequest,
              b: UriComponentsBuilder): ResponseEntity<Void> {
        val response = replyService.apply(replyApplyRequest, currentUserId()).toResponse()
        val uriComponents = b.path("/api/v1/notices/{id}").buildAndExpand(response.id)
        return ResponseEntity.created(uriComponents.toUri()).build()
    }

    @GetMapping
    fun getAll(pageable: Pageable): ResponseEntity<Page<ResponseDtos>> {
        val result = replyService.findAllByWriter(currentUserId(), pageable).map { it.toResponse() }
        return ResponseEntity.ok(result)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long,
               @RequestBody replyUpdateRequest: ReplyUpdateRequest): ResponseEntity<Void> {
        replyService.update(replyUpdateRequest, id, currentUserId())
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun deleteByReplier(@PathVariable id: Long): ResponseEntity<Void> {
        if (currentUserIsAdmin()) {
            return if (replyService.forceDeleteReply(id)) ResponseEntity.noContent().build() else ResponseEntity.badRequest().build()
        }
        return if (replyService.checkDeleteReply(id, currentUserId())) ResponseEntity.noContent().build() else ResponseEntity.badRequest().build()
    }

    @DeleteMapping
    fun deleteAll(@RequestParam videoId: Long): ResponseEntity<Void> {
        return if (replyService.deleteReplies(videoId, currentUserId())) ResponseEntity.noContent().build() else ResponseEntity.badRequest().build()
    }

}
