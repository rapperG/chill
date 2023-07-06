
package com.chill.token.filter;

/**
 * Sa-Token 全局过滤器 - 认证策略封装，方便 lambda 表达式风格调用
 *
 * @author chill
 * @since 1.17.0
 */
@FunctionalInterface
public interface SaFilterAuthStrategy {

    /**
     * 执行方法
     *
     * @param obj 无含义参数，留作扩展
     */
    void run(Object obj);

}
