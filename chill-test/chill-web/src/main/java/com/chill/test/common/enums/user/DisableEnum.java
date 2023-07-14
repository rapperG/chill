package com.chill.test.common.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *
 * @author chill
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum DisableEnum {

    /**
     * 禁用枚举
     */
    NOT_DISABLED(0, "未禁用"),
    ALREADY_DISABLED(1, "已禁用"),

    ;


    private final int code;

    private final String message;
}
