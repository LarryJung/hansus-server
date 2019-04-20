package com.hsmchurch.app.common

interface HasOwner {

    fun isOwner(writerId: Long): Boolean

}
