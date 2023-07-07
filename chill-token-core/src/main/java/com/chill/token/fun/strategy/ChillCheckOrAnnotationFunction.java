
package com.chill.token.fun.strategy;

import com.chill.token.annotation.ChillCheckOr;

import java.util.function.Consumer;

/**
 * 函数式接口：对一个 @ChillCheckOr 进行注解校验
 *
 * <p>  参数：ChillCheckOr 注解的实例  </p>
 * <p>  返回：无  </p>
 *
 * @author chill
 * @since 1.0
 */
@FunctionalInterface
public interface ChillCheckOrAnnotationFunction extends Consumer<ChillCheckOr> {

}
