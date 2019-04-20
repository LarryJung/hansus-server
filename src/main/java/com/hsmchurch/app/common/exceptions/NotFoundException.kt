package com.hsmchurch.app.common.exceptions

class NotFoundException(entityName: String, id: Any) : RuntimeException("${entityName}를 찾을 수 없습니다. id: $id")
