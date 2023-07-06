
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
 * @since 1.35.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SaCheckOr {

    /**
     * 设定 @SaCheckLogin，参考 {@link SaCheckLogin}
     *
     * @return /
     */
    SaCheckLogin[] login() default {};

    /**
     * 设定 @SaCheckPermission，参考 {@link SaCheckPermission}
     *
     * @return /
     */
    SaCheckPermission[] permission() default {};

    /**
     * 设定 @SaCheckRole，参考 {@link SaCheckRole}
     *
     * @return /
     */
    SaCheckRole[] role() default {};

    /**
     * 设定 @SaCheckSafe，参考 {@link SaCheckSafe}
     *
     * @return /
     */
    SaCheckSafe[] safe() default {};

    /**
     * 设定 @SaCheckBasic，参考 {@link SaCheckBasic}
     *
     * @return /
     */
    SaCheckBasic[] basic() default {};

    /**
     * 设定 @SaCheckDisable，参考 {@link SaCheckDisable}
     *
     * @return /
     */
    SaCheckDisable[] disable() default {};

}
