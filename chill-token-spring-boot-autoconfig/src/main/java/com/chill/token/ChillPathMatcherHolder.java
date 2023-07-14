
package com.chill.token;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * 持有 PathMatcher 全局引用，方便快捷的调用 PathMatcher 相关方法
 *
 * @author chill
 * @since 1.0
 */
public class ChillPathMatcherHolder {

    private ChillPathMatcherHolder() {
    }

    /**
     * 路由匹配器
     */
    public static PathMatcher pathMatcher;

    /**
     * 获取路由匹配器
     *
     * @return 路由匹配器
     */
    public static PathMatcher getPathMatcher() {
        if (pathMatcher == null) {
            pathMatcher = new AntPathMatcher();
        }
        return pathMatcher;
    }

    /**
     * 写入路由匹配器
     *
     * @param pathMatcher 路由匹配器
     */
    public static void setPathMatcher(PathMatcher pathMatcher) {
        ChillPathMatcherHolder.pathMatcher = pathMatcher;
    }

}
