
package com.chill.token.annotation;

/**
 * 注解鉴权的验证模式
 *
 * @author chill
 * @since 1.10.0
 */
public enum SaMode {

    /**
     * 必须具有所有的元素
     */
    AND,

    /**
     * 只需具有其中一个元素
     */
    OR

}
