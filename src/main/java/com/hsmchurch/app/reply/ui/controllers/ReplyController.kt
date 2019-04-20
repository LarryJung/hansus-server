package com.hsmchurch.app.reply.ui.controllers

import com.hsmchurch.app.reply.application.ReplyService
import com.hsmchurch.app.reply.ui.ResponseDtos
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/replies"])
class ReplyController constructor(private val replyService: ReplyService){

    @GetMapping("/{id}")
    fun getOneById(@PathVariable id: Long): ResponseEntity<ResponseDtos> {
        val replyResponse = replyService.findById(id).toResponse()

        return ResponseEntity.ok(replyResponse)
    }

}
