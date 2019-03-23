package com.hsmchurch.app.core.support;

import java.util.function.Function;

public class CrudStringFormat {

    private static final String CREATE = "등록";
    private static final String READ = "조회";
    private static final String UPDATE = "업데이트";
    private static final String DELETE = "삭제";
    private static final String CANCEL = "취소";

    private static final Function<String, String> success = action -> String.format("%s 성공!", action);
    private static final Function<String, String> fail = action -> String.format("%s 실패!", action);
    private static final Base base = (entityName, actionResult) -> String.format("%s %s, request: {}", entityName, actionResult);
    private static final Base error = (entityName, actionResult) -> String.format("%s %s, request: {}, error: {}", entityName, actionResult);

    public static final Function<String, String> REGISTER_SUCCESS = entityName -> base.format(entityName, success.apply(CREATE));
    public static final Function<String, String> REGISTER_FAIL = entityName -> error.format(entityName, fail.apply(CREATE));
    public static final Function<String, String> READ_SUCCESS = entityName -> base.format(entityName, success.apply(READ));
    public static final Function<String, String> READ_FAIL = entityName -> error.format(entityName, fail.apply(READ));
    public static final Function<String, String> UPDATE_SUCCESS = entityName -> base.format(entityName, success.apply(UPDATE));
    public static final Function<String, String> UPDATE_FAIL = entityName -> error.format(entityName, fail.apply(UPDATE));
    public static final Function<String, String> DELETE_SUCCESS = entityName -> base.format(entityName, success.apply(DELETE));
    public static final Function<String, String> DELETE_FAIL = entityName -> error.format(entityName, fail.apply(DELETE));
    public static final Function<String, String> CANCEL_SUCCESS = entityName -> base.format(entityName, success.apply(CANCEL));
    public static final Function<String, String> CANCEL_FAIL = entityName -> error.format(entityName, fail.apply(CANCEL));

    @FunctionalInterface
    interface Base {
        String format(final String entityName,
                      final String actionResult);
    }

}
