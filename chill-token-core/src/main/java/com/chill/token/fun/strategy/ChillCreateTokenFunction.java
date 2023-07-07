
package com.chill.token.fun.strategy;

import java.util.function.BiFunction;

/**
 * 函数式接口：创建 token 的策略
 *
 * <p>  参数：账号 id、账号类型  </p>
 * <p>  返回：token 值  </p>
 *
 * @author chill
 * @since 1.0
 */
@FunctionalInterface
public interface ChillCreateTokenFunction extends BiFunction<Object, String, String> {

}
