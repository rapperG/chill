
package com.chill.token.fun.strategy;

import java.lang.reflect.AnnotatedElement;
import java.util.function.Consumer;

/**
 * 函数式接口：对一个 [元素] 对象进行注解校验 （注解鉴权内部实现）
 *
 * <p>  参数：element元素  </p>
 * <p>  返回：无  </p>
 *
 * @author chill
 * @since 1.0
 */
@FunctionalInterface
public interface ChillCheckElementAnnotationFunction extends Consumer<AnnotatedElement> {

}
