
package com.chill.token.context.second;

/**
 * Chill-Token 二级Context - 创建器
 *
 * @author chill
 * @since 1.0
 */
@FunctionalInterface
public interface ChillTokenSecondContextCreator {

    /**
     * 创建一个二级 Context 处理器
     *
     * @return /
     */
    ChillTokenSecondContext create();

}
