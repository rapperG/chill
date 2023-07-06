
package com.chill.token.context.second;

/**
 * Sa-Token 二级Context - 创建器
 *
 * @author chill
 * @since 1.28.0
 */
@FunctionalInterface
public interface SaTokenSecondContextCreator {

    /**
     * 创建一个二级 Context 处理器
     *
     * @return /
     */
    SaTokenSecondContext create();

}
