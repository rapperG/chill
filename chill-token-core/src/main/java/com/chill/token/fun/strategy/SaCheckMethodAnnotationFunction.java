
package com.chill.token.fun.strategy;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * 函数式接口：对一个 [Method] 对象进行注解校验 （注解鉴权内部实现）
 *
 * <p>  参数：Method句柄  </p>
 * <p>  返回：无  </p>
 *
 * @author chill
 * @since 1.35.0
 */
@FunctionalInterface
public interface SaCheckMethodAnnotationFunction extends Consumer<Method> {

}
