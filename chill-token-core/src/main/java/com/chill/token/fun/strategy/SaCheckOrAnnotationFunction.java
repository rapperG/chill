
package com.chill.token.fun.strategy;

import com.chill.token.annotation.SaCheckOr;

import java.util.function.Consumer;

/**
 * 函数式接口：对一个 @SaCheckOr 进行注解校验
 *
 * <p>  参数：SaCheckOr 注解的实例  </p>
 * <p>  返回：无  </p>
 *
 * @author chill
 * @since 1.35.0
 */
@FunctionalInterface
public interface SaCheckOrAnnotationFunction extends Consumer<SaCheckOr> {

}
