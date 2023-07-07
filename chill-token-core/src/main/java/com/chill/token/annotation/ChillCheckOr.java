
package com.chill.token.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 批量注解鉴权：只要满足其中一个注解即可通过验证
 *
 * <p> 可标注在方法、类上（效果等同于标注在此类的所有方法上）
 *
 * @author chill
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ChillCheckOr {

    /**
     * 设定 @ChillCheckLogin，参考 {@link ChillCheckLogin}
     *
     * @return /
     */
    ChillCheckLogin[] login() default {};

    /**
     * 设定 @ChillCheckPermission，参考 {@link ChillCheckPermission}
     *
     * @return /
     */
    ChillCheckPermission[] permission() default {};

    /**
     * 设定 @ChillCheckRole，参考 {@link ChillCheckRole}
     *
     * @return /
     */
    ChillCheckRole[] role() default {};

    /**
     * 设定 @ChillCheckSafe，参考 {@link ChillCheckSafe}
     *
     * @return /
     */
    ChillCheckSafe[] safe() default {};

    /**
     * 设定 @ChillCheckBasic，参考 {@link ChillCheckBasic}
     *
     * @return /
     */
    ChillCheckBasic[] basic() default {};

    /**
     * 设定 @ChillCheckDisable，参考 {@link ChillCheckDisable}
     *
     * @return /
     */
    ChillCheckDisable[] disable() default {};

}
