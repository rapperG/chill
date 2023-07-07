
package com.chill.token.basic;

import com.chill.token.ChillManager;
import com.chill.token.context.ChillHolder;
import com.chill.token.error.ChillErrorCode;
import com.chill.token.exception.NotBasicAuthException;
import com.chill.token.secure.ChillBase64Util;
import com.chill.token.util.ChillFoxUtil;

/**
 * Chill-Token Http Basic 认证模块
 *
 * @author chill
 * @since 1.0
 */
public class ChillBasicTemplate {

    /**
     * 默认的 Realm 领域名称
     */
    public static final String DEFAULT_REALM = "chill-token";

    /**
     * 在校验失败时，设置响应头，并抛出异常
     *
     * @param realm 领域
     */
    public void throwNotBasicAuthException(String realm) {
        ChillHolder.getResponse().setStatus(401).setHeader("WWW-Authenticate", "Basic Realm=" + realm);
        throw new NotBasicAuthException().setCode(ChillErrorCode.CODE_10311);
    }

    /**
     * 获取浏览器提交的 Basic 参数 （裁剪掉前缀并解码）
     *
     * @return 值
     */
    public String getAuthorizationValue() {

        // 获取前端提交的请求头 Authorization 参数
        String authorization = ChillHolder.getRequest().getHeader("Authorization");

        // 如果不是以 Basic 作为前缀，则视为无效
        if (authorization == null || !authorization.startsWith("Basic ")) {
            return null;
        }

        // 裁剪前缀并解码
        return ChillBase64Util.decode(authorization.substring(6));
    }

    /**
     * 对当前会话进行 Basic 校验（使用全局配置的账号密码），校验不通过则抛出异常
     */
    public void check() {
        check(DEFAULT_REALM, ChillManager.getConfig().getBasic());
    }

    /**
     * 对当前会话进行 Basic 校验（手动设置账号密码），校验不通过则抛出异常
     *
     * @param account 账号（格式为 user:password）
     */
    public void check(String account) {
        check(DEFAULT_REALM, account);
    }

    /**
     * 对当前会话进行 Basic 校验（手动设置 Realm 和 账号密码），校验不通过则抛出异常
     *
     * @param realm   领域
     * @param account 账号（格式为 user:password）
     */
    public void check(String realm, String account) {
        if (ChillFoxUtil.isEmpty(account)) {
            account = ChillManager.getConfig().getBasic();
        }
        String authorization = getAuthorizationValue();
        if (ChillFoxUtil.isEmpty(authorization) || !authorization.equals(account)) {
            throwNotBasicAuthException(realm);
        }
    }

}
