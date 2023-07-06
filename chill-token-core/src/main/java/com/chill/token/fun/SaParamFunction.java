
package com.chill.token.fun;

/**
 * 单形参、无返回值的函数式接口，方便开发者进行 lambda 表达式风格调用
 *
 * @author chill
 * @since 1.27.0
 */
@FunctionalInterface
public interface SaParamFunction<T> {

    /**
     * 执行的方法
     *
     * @param r 传入的参数
     */
    void run(T r);

}
