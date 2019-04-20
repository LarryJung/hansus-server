package com.hsmchurch.app.common.support

object CrudStringFormat {

    private val CREATE = "등록"
    private val READ = "조회"
    private val UPDATE = "업데이트"
    private val DELETE = "삭제"
    private val CANCEL = "취소"

    private val success = { action: String -> "$action 성공!" }
    private val fail = { action: String -> "$action 실패!" }
    private val base = { entityName: String, actionResult: String -> "$entityName $actionResult, request: {}" }
    private val error = { entityName: String, actionResult: String -> "$entityName $actionResult, request: {}, error: {}" }

    val REGISTER_SUCCESS = { entityName: String -> base(entityName, success(CREATE)) }
    val REGISTER_FAIL = { entityName: String -> error(entityName, fail(CREATE)) }
    val READ_SUCCESS = { entityName: String  -> base(entityName, success(READ)) }
    val READ_FAIL = { entityName: String  -> error(entityName, fail(READ)) }
    val UPDATE_SUCCESS = { entityName: String  -> base(entityName, success(UPDATE)) }
    val UPDATE_FAIL = { entityName: String  -> error(entityName, fail(UPDATE)) }
    val DELETE_SUCCESS = { entityName: String  -> base(entityName, success(DELETE)) }
    val DELETE_FAIL = { entityName: String  -> error(entityName, fail(DELETE)) }
    val CANCEL_SUCCESS = { entityName: String  -> base(entityName, success(CANCEL)) }
    val CANCEL_FAIL = { entityName: String  -> error(entityName, fail(CANCEL)) }

}
