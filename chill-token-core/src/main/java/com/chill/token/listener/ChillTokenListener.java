
package com.chill.token.listener;

import com.chill.token.config.ChillTokenConfig;
import com.chill.token.stp.ChillLoginModel;
import com.chill.token.stp.AuthLoginLogic;

/**
 * Chill-Token 侦听器
 *
 * <p> 你可以通过实现此接口在用户登录、退出等关键性操作时进行一些AOP切面操作 </p>
 *
 * @author chill
 * @since 1.0
 */
public interface ChillTokenListener {

    /**
     * 每次登录时触发
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue 本次登录产生的 token 值
     * @param loginModel 登录参数
     */
    void doLogin(String loginType, Object loginId, String tokenValue, ChillLoginModel loginModel);

    /**
     * 每次注销时触发
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    void doLogout(String loginType, Object loginId, String tokenValue);

    /**
     * 每次被踢下线时触发
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    void doKickout(String loginType, Object loginId, String tokenValue);

    /**
     * 每次被顶下线时触发
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    void doReplaced(String loginType, Object loginId, String tokenValue);

    /**
     * 每次被封禁时触发
     *
     * @param loginType   账号类别
     * @param loginId     账号id
     * @param service     指定服务
     * @param level       封禁等级
     * @param disableTime 封禁时长，单位: 秒
     */
    void doDisable(String loginType, Object loginId, String service, int level, long disableTime);

    /**
     * 每次被解封时触发
     *
     * @param loginType 账号类别
     * @param loginId   账号id
     * @param service   指定服务
     */
    void doUntieDisable(String loginType, Object loginId, String service);

    /**
     * 每次打开二级认证时触发
     *
     * @param loginType  账号类别
     * @param tokenValue token值
     * @param service    指定服务
     * @param safeTime   认证时间，单位：秒
     */
    void doOpenSafe(String loginType, String tokenValue, String service, long safeTime);

    /**
     * 每次关闭二级认证时触发
     *
     * @param loginType  账号类别
     * @param tokenValue token值
     * @param service    指定服务
     */
    void doCloseSafe(String loginType, String tokenValue, String service);

    /**
     * 每次创建 ChillSession 时触发
     *
     * @param id SessionId
     */
    void doCreateSession(String id);

    /**
     * 每次注销 ChillSession 时触发
     *
     * @param id SessionId
     */
    void doLogoutSession(String id);

    /**
     * 每次 Token 续期时触发（注意：是 timeout 续期，而不是 active-timeout 续期）
     *
     * @param tokenValue token 值
     * @param loginId    账号id
     * @param timeout    续期时间
     */
    void doRenewTimeout(String tokenValue, Object loginId, long timeout);

    /**
     * 全局组件载入
     *
     * @param compName 组件名称
     * @param compObj  组件对象
     */
    default void doRegisterComponent(String compName, Object compObj) {
    }

    /**
     * AuthLoginLogic 对象替换
     *
     * @param stpLogic /
     */
    default void doSetStpLogic(AuthLoginLogic stpLogic) {
    }

    /**
     * 载入全局配置
     *
     * @param config /
     */
    default void doSetConfig(ChillTokenConfig config) {
    }

}
