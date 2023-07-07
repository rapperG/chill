
package com.chill.token.context;

import com.chill.token.context.model.ChillRequest;
import com.chill.token.context.model.ChillResponse;
import com.chill.token.context.model.ChillStorage;

/**
 * Chill-Token 上下文处理器
 *
 * <p> 上下文处理器封装了当前应用环境的底层操作，是 Chill-Token 对接不同 web 框架的关键，详细可参考在线文档 “自定义 ChillTokenContext 指南”章节 </p>
 *
 * @author chill
 * @since 1.0
 */
public interface ChillTokenContext {

    /**
     * 获取当前请求的 Request 包装对象
     *
     * @return /
     * @see ChillRequest
     */
    ChillRequest getRequest();

    /**
     * 获取当前请求的 Response 包装对象
     *
     * @return /
     * @see ChillResponse
     */
    ChillResponse getResponse();

    /**
     * 获取当前请求的 Storage 包装对象
     *
     * @return /
     * @see ChillStorage
     */
    ChillStorage getStorage();

    /**
     * 判断：指定路由匹配符是否可以匹配成功指定路径
     * <pre>
     *     判断规则由底层 web 框架决定，例如在 springboot 中：
     *     	- matchPath("/user/*", "/user/login")  返回: true
     *     	- matchPath("/user/*", "/article/edit")  返回: false
     * </pre>
     *
     * @param pattern 路由匹配符
     * @param path    需要匹配的路径
     * @return /
     */
    boolean matchPath(String pattern, String path);

    /**
     * 判断：在本次请求中，此上下文是否可用。
     * <p> 例如在部分 rpc 调用时， 一级上下文会返回 false，这时候框架就会选择使用二级上下文来处理请求 </p>
     *
     * @return /
     */
    default boolean isValid() {
        return false;
    }

}
