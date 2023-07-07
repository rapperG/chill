
package com.chill.token.same;

import com.chill.token.ChillManager;

/**
 * Chill Same-Token 同源系统身份认证模块 - 工具类
 *
 * <p> 解决同源系统互相调用时的身份认证校验， 例如：微服务网关请求转发鉴权、微服务RPC调用鉴权
 *
 * @author chill
 * @since 1.0
 */
public class ChillSameUtil {

    private ChillSameUtil() {
    }

    /**
     * 提交 Same-Token 时，建议使用的参数名称
     */
    public static final String SAME_TOKEN = ChillSameTemplate.SAME_TOKEN;

    // -------------------- 获取 & 校验

    /**
     * 获取当前 Same-Token, 如果不存在，则立即创建并返回
     *
     * @return /
     */
    public static String getToken() {
        return ChillManager.getChillSameTemplate().getToken();
    }

    /**
     * 判断一个 Same-Token 是否有效
     *
     * @param token /
     * @return /
     */
    public static boolean isValid(String token) {
        return ChillManager.getChillSameTemplate().isValid(token);
    }

    /**
     * 校验一个 Same-Token 是否有效 (如果无效则抛出异常)
     *
     * @param token /
     */
    public static void checkToken(String token) {
        ChillManager.getChillSameTemplate().checkToken(token);
    }

    /**
     * 校验当前 Request 上下文提供的 Same-Token 是否有效 (如果无效则抛出异常)
     */
    public static void checkCurrentRequestToken() {
        ChillManager.getChillSameTemplate().checkCurrentRequestToken();
    }

    /**
     * 刷新一次 Same-Token (注意集群环境中不要多个服务重复调用)
     *
     * @return 刷新后产生的新 Same-Token
     */
    public static String refreshToken() {
        return ChillManager.getChillSameTemplate().refreshToken();
    }


    // -------------------- 获取Token

    /**
     * 获取 Same-Token，不做任何处理
     *
     * @return /
     */
    public static String getTokenNh() {
        return ChillManager.getChillSameTemplate().getTokenNh();
    }

    /**
     * 获取 Past-Same-Token，不做任何处理
     *
     * @return /
     */
    public static String getPastTokenNh() {
        return ChillManager.getChillSameTemplate().getPastTokenNh();
    }

}
