
package com.chill.token.filter;

import java.util.List;

/**
 * Chill-Token 过滤器接口，为不同版本的过滤器：
 * 1、封装共同代码。
 * 2、定义统一的行为接口。
 *
 * @author chill
 * @since 1.0
 */
public interface ChillFilter {

    // ------------------------ 设置此过滤器 拦截 & 放行 的路由

    /**
     * 添加 [ 拦截路由 ]
     *
     * @param paths 路由
     * @return 对象自身
     */
    ChillFilter addInclude(String... paths);

    /**
     * 添加 [ 放行路由 ]
     *
     * @param paths 路由
     * @return 对象自身
     */
    ChillFilter addExclude(String... paths);

    /**
     * 写入 [ 拦截路由 ] 集合
     *
     * @param pathList 路由集合
     * @return 对象自身
     */
    ChillFilter setIncludeList(List<String> pathList);

    /**
     * 写入 [ 放行路由 ] 集合
     *
     * @param pathList 路由集合
     * @return 对象自身
     */
    ChillFilter setExcludeList(List<String> pathList);


    // ------------------------ 钩子函数

    /**
     * 写入[ 认证函数 ]: 每次请求执行
     *
     * @param auth see note
     * @return 对象自身
     */
    ChillFilter setAuth(ChillFilterAuthStrategy auth);

    /**
     * 写入[ 异常处理函数 ]：每次[ 认证函数 ]发生异常时执行此函数
     *
     * @param error see note
     * @return 对象自身
     */
    ChillFilter setError(ChillFilterErrorStrategy error);

    /**
     * 写入[ 前置函数 ]：在每次[ 认证函数 ]之前执行。
     * <b>注意点：前置认证函数将不受 includeList 与 excludeList 的限制，所有路由的请求都会进入 beforeAuth</b>
     *
     * @param beforeAuth /
     * @return 对象自身
     */
    ChillFilter setBeforeAuth(ChillFilterAuthStrategy beforeAuth);

}
