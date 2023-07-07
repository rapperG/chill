
package com.chill.token.router;

import com.chill.token.ChillManager;
import com.chill.token.context.ChillHolder;
import com.chill.token.exception.BackResultException;
import com.chill.token.exception.StopMatchException;
import com.chill.token.fun.ChillFunction;
import com.chill.token.fun.ChillParamFunction;
import com.chill.token.fun.ChillParamRetFunction;

import java.util.List;

/**
 * 路由匹配操作工具类
 *
 * <p> 提供了一系列的路由匹配操作方法，一般用在全局拦截器、过滤器做路由拦截鉴权。 </p>
 * <p> 简单示例： </p>
 * <pre>
 *    	// 指定一条 match 规则
 *    	ChillRouter
 *    	   	.match("/**")    // 拦截的 path 列表，可以写多个
 *   	   	.notMatch("/user/doLogin")        // 排除掉的 path 列表，可以写多个
 *   	   	.check(r->AuthLoginUtil.checkLogin());        // 要执行的校验动作，可以写完整的 lambda 表达式
 * </pre>
 *
 * @author chill
 * @since 1.0
 */
public class ChillRouter {

    private ChillRouter() {
    }

    // -------------------- 路由匹配相关 --------------------

    /**
     * 路由匹配
     *
     * @param pattern 路由匹配符
     * @param path    被匹配的路由
     * @return 是否匹配成功
     */
    public static boolean isMatch(String pattern, String path) {
        return ChillManager.getChillTokenContextOrSecond().matchPath(pattern, path);
    }

    /**
     * 路由匹配
     *
     * @param patterns 路由匹配符集合
     * @param path     被匹配的路由
     * @return 是否匹配成功
     */
    public static boolean isMatch(List<String> patterns, String path) {
        if (patterns == null) {
            return false;
        }
        for (String pattern : patterns) {
            if (isMatch(pattern, path)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 路由匹配
     *
     * @param patterns 路由匹配符数组
     * @param path     被匹配的路由
     * @return 是否匹配成功
     */
    public static boolean isMatch(String[] patterns, String path) {
        if (patterns == null) {
            return false;
        }
        for (String pattern : patterns) {
            if (isMatch(pattern, path)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Http请求方法匹配
     *
     * @param methods      Http请求方法断言数组
     * @param methodString Http请求方法
     * @return 是否匹配成功
     */
    public static boolean isMatch(ChillHttpMethod[] methods, String methodString) {
        if (methods == null) {
            return false;
        }
        for (ChillHttpMethod method : methods) {
            if (method == ChillHttpMethod.ALL || (method != null && method.toString().equalsIgnoreCase(methodString))) {
                return true;
            }
        }
        return false;
    }

    // ------ 使用当前URI匹配

    /**
     * 路由匹配 (使用当前URI)
     *
     * @param pattern 路由匹配符
     * @return 是否匹配成功
     */
    public static boolean isMatchCurrURI(String pattern) {
        return isMatch(pattern, ChillHolder.getRequest().getRequestPath());
    }

    /**
     * 路由匹配 (使用当前URI)
     *
     * @param patterns 路由匹配符集合
     * @return 是否匹配成功
     */
    public static boolean isMatchCurrURI(List<String> patterns) {
        return isMatch(patterns, ChillHolder.getRequest().getRequestPath());
    }

    /**
     * 路由匹配 (使用当前URI)
     *
     * @param patterns 路由匹配符数组
     * @return 是否匹配成功
     */
    public static boolean isMatchCurrURI(String[] patterns) {
        return isMatch(patterns, ChillHolder.getRequest().getRequestPath());
    }

    /**
     * Http请求方法匹配 (使用当前请求方式)
     *
     * @param methods Http请求方法断言数组
     * @return 是否匹配成功
     */
    public static boolean isMatchCurrMethod(ChillHttpMethod[] methods) {
        return isMatch(methods, ChillHolder.getRequest().getMethod());
    }


    // -------------------- 开始匹配 --------------------

    /**
     * 初始化一个SaRouterStaff，开始匹配
     *
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff newMatch() {
        return new ChillRouterStaff();
    }

    // ----------------- path匹配

    /**
     * 路由匹配
     *
     * @param patterns 路由匹配符集合
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff match(String... patterns) {
        return new ChillRouterStaff().match(patterns);
    }

    /**
     * 路由匹配排除
     *
     * @param patterns 路由匹配符排除数组
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff notMatch(String... patterns) {
        return new ChillRouterStaff().notMatch(patterns);
    }

    /**
     * 路由匹配
     *
     * @param patterns 路由匹配符集合
     * @return 对象自身
     */
    public static ChillRouterStaff match(List<String> patterns) {
        return new ChillRouterStaff().match(patterns);
    }

    /**
     * 路由匹配排除
     *
     * @param patterns 路由匹配符排除集合
     * @return 对象自身
     */
    public static ChillRouterStaff notMatch(List<String> patterns) {
        return new ChillRouterStaff().notMatch(patterns);
    }

    // ----------------- Method匹配

    /**
     * Http请求方式匹配 (Enum)
     *
     * @param methods Http请求方法断言数组
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff match(ChillHttpMethod... methods) {
        return new ChillRouterStaff().match(methods);
    }

    /**
     * Http请求方法匹配排除 (Enum)
     *
     * @param methods Http请求方法断言排除数组
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff notMatch(ChillHttpMethod... methods) {
        return new ChillRouterStaff().notMatch(methods);
    }

    /**
     * Http请求方法匹配 (String)
     *
     * @param methods Http请求方法断言数组
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff matchMethod(String... methods) {
        return new ChillRouterStaff().matchMethod(methods);
    }

    /**
     * Http请求方法匹配排除 (String)
     *
     * @param methods Http请求方法断言排除数组
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff notMatchMethod(String... methods) {
        return new ChillRouterStaff().notMatchMethod(methods);
    }

    // ----------------- 条件匹配

    /**
     * 根据 boolean 值进行匹配
     *
     * @param flag boolean值
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff match(boolean flag) {
        return new ChillRouterStaff().match(flag);
    }

    /**
     * 根据 boolean 值进行匹配排除
     *
     * @param flag boolean值
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff notMatch(boolean flag) {
        return new ChillRouterStaff().notMatch(flag);
    }

    /**
     * 根据自定义方法进行匹配 (lazy)
     *
     * @param fun 自定义方法
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff match(ChillParamRetFunction<Object, Boolean> fun) {
        return new ChillRouterStaff().match(fun);
    }

    /**
     * 根据自定义方法进行匹配排除 (lazy)
     *
     * @param fun 自定义排除方法
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff notMatch(ChillParamRetFunction<Object, Boolean> fun) {
        return new ChillRouterStaff().notMatch(fun);
    }


    // -------------------- 直接指定check函数 --------------------

    /**
     * 路由匹配，如果匹配成功则执行认证函数
     *
     * @param pattern 路由匹配符
     * @param fun     要执行的校验方法
     * @return /
     */
    public static ChillRouterStaff match(String pattern, ChillFunction fun) {
        return new ChillRouterStaff().match(pattern, fun);
    }

    /**
     * 路由匹配，如果匹配成功则执行认证函数
     *
     * @param pattern 路由匹配符
     * @param fun     要执行的校验方法
     * @return /
     */
    public static ChillRouterStaff match(String pattern, ChillParamFunction<ChillRouterStaff> fun) {
        return new ChillRouterStaff().match(pattern, fun);
    }

    /**
     * 路由匹配 (并指定排除匹配符)，如果匹配成功则执行认证函数
     *
     * @param pattern        路由匹配符
     * @param excludePattern 要排除的路由匹配符
     * @param fun            要执行的方法
     * @return /
     */
    public static ChillRouterStaff match(String pattern, String excludePattern, ChillFunction fun) {
        return new ChillRouterStaff().match(pattern, excludePattern, fun);
    }

    /**
     * 路由匹配 (并指定排除匹配符)，如果匹配成功则执行认证函数
     *
     * @param pattern        路由匹配符
     * @param excludePattern 要排除的路由匹配符
     * @param fun            要执行的方法
     * @return /
     */
    public static ChillRouterStaff match(String pattern, String excludePattern, ChillParamFunction<ChillRouterStaff> fun) {
        return new ChillRouterStaff().match(pattern, excludePattern, fun);
    }


    // -------------------- 提前退出 --------------------

    /**
     * 停止匹配，跳出函数 (在多个匹配链中一次性跳出Auth函数)
     *
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff stop() {
        throw new StopMatchException();
    }

    /**
     * 停止匹配，结束执行，向前端返回结果
     *
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff back() {
        throw new BackResultException("");
    }

    /**
     * 停止匹配，结束执行，向前端返回结果
     *
     * @param result 要输出的结果
     * @return ChillRouterStaff
     */
    public static ChillRouterStaff back(Object result) {
        throw new BackResultException(result);
    }

}
