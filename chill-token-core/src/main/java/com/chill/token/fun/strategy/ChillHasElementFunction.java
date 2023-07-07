
package com.chill.token.fun.strategy;

import java.util.List;
import java.util.function.BiFunction;

/**
 * 函数式接口：判断集合中是否包含指定元素（模糊匹配）
 *
 * <p>  参数：集合、元素  </p>
 * <p>  返回：是否包含  </p>
 *
 * @author chill
 * @since 1.0
 */
@FunctionalInterface
public interface ChillHasElementFunction extends BiFunction<List<String>, String, Boolean> {

}
