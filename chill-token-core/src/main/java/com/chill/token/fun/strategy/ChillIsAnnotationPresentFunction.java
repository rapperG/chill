
package com.chill.token.fun.strategy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.BiFunction;

/**
 * 函数式接口：判断一个 Method 或其所属 Class 是否包含指定注解
 *
 * <p>  参数：Method、注解  </p>
 * <p>  返回：是否包含  </p>
 *
 * @author chill
 * @since 1.0
 */
@FunctionalInterface
public interface ChillIsAnnotationPresentFunction extends BiFunction<Method, Class<? extends Annotation>, Boolean> {

}
