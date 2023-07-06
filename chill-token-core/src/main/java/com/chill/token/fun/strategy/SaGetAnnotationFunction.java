
package com.chill.token.fun.strategy;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.function.BiFunction;

/**
 * 函数式接口：从元素上获取注解
 *
 * <p>  参数：element元素，要获取的注解类型  </p>
 * <p>  返回：注解对象  </p>
 *
 * @author chill
 * @since 1.35.0
 */
@FunctionalInterface
public interface SaGetAnnotationFunction extends BiFunction<AnnotatedElement, Class<? extends Annotation>, Annotation> {

}
