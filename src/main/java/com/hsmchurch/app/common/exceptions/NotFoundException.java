package com.hsmchurch.app.common.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(final String entityName,
                             final Object id) {
        super(String.format("%s를 찾을 수 없습니다. id: %s", entityName, id.toString()));
    }

}
