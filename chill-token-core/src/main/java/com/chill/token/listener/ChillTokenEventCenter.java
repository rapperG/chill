
package com.chill.token.listener;

import com.chill.token.config.ChillTokenConfig;
import com.chill.token.error.ChillErrorCode;
import com.chill.token.exception.basic.TokenException;
import com.chill.token.stp.AuthLoginLogic;
import com.chill.token.stp.ChillLoginModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Chill-Token 事件中心 事件发布器
 *
 * <p> 提供侦听器注册、事件发布能力 </p>
 *
 * @author chill
 * @since 1.0
 */
public class ChillTokenEventCenter {

    // --------- 注册侦听器

    private static List<ChillTokenListener> tokenListeners = new ArrayList<>();

    static {
        // 默认添加控制台日志侦听器
        tokenListeners.add(new ChillTokenListenerForLog());
    }

    /**
     * 获取已注册的所有侦听器
     *
     * @return /
     */
    public static List<ChillTokenListener> getTokenListeners() {
        return tokenListeners;
    }

    /**
     * 重置侦听器集合
     *
     * @param tokenListeners /
     */
    public static void setTokenListeners(List<ChillTokenListener> tokenListeners) {
        if (tokenListeners == null) {
            throw new TokenException("重置的侦听器集合不可以为空").setCode(ChillErrorCode.CODE_10031);
        }
        ChillTokenEventCenter.tokenListeners = tokenListeners;
    }

    /**
     * 注册一个侦听器
     *
     * @param listener /
     */
    public static void registerListener(ChillTokenListener listener) {
        if (listener == null) {
            throw new TokenException("注册的侦听器不可以为空").setCode(ChillErrorCode.CODE_10032);
        }
        tokenListeners.add(listener);
    }

    /**
     * 注册一组侦听器
     *
     * @param listenerList /
     */
    public static void registerListenerList(List<ChillTokenListener> listenerList) {
        if (listenerList == null) {
            throw new TokenException("注册的侦听器集合不可以为空").setCode(ChillErrorCode.CODE_10031);
        }
        for (ChillTokenListener listener : listenerList) {
            if (listener == null) {
                throw new TokenException("注册的侦听器不可以为空").setCode(ChillErrorCode.CODE_10032);
            }
        }
        ChillTokenEventCenter.tokenListeners.addAll(listenerList);
    }

    /**
     * 移除一个侦听器
     *
     * @param listener /
     */
    public static void removeListener(ChillTokenListener listener) {
        tokenListeners.remove(listener);
    }

    /**
     * 移除指定类型的所有侦听器
     *
     * @param cls /
     */
    public static void removeListener(Class<? extends ChillTokenListener> cls) {
        ArrayList<ChillTokenListener> listenerListCopy = new ArrayList<>(tokenListeners);
        for (ChillTokenListener listener : listenerListCopy) {
            if (cls.isAssignableFrom(listener.getClass())) {
                tokenListeners.remove(listener);
            }
        }
    }

    /**
     * 清空所有已注册的侦听器
     */
    public static void clearListener() {
        tokenListeners.clear();
    }

    /**
     * 判断是否已经注册了指定侦听器
     *
     * @param listener /
     * @return /
     */
    public static boolean hasListener(ChillTokenListener listener) {
        return tokenListeners.contains(listener);
    }

    /**
     * 判断是否已经注册了指定类型的侦听器
     *
     * @param cls /
     * @return /
     */
    public static boolean hasListener(Class<? extends ChillTokenListener> cls) {
        for (ChillTokenListener listener : tokenListeners) {
            if (cls.isAssignableFrom(listener.getClass())) {
                return true;
            }
        }
        return false;
    }


    // --------- 事件发布

    /**
     * 事件发布：xx 账号登录
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue 本次登录产生的 token 值
     * @param loginModel 登录参数
     */
    public static void doLogin(String loginType, Object loginId, String tokenValue, ChillLoginModel loginModel) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doLogin(loginType, loginId, tokenValue, loginModel);
        }
    }

    /**
     * 事件发布：xx 账号注销
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    public static void doLogout(String loginType, Object loginId, String tokenValue) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doLogout(loginType, loginId, tokenValue);
        }
    }

    /**
     * 事件发布：xx 账号被踢下线
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    public static void doKickout(String loginType, Object loginId, String tokenValue) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doKickout(loginType, loginId, tokenValue);
        }
    }

    /**
     * 事件发布：xx 账号被顶下线
     *
     * @param loginType  账号类别
     * @param loginId    账号id
     * @param tokenValue token值
     */
    public static void doReplaced(String loginType, Object loginId, String tokenValue) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doReplaced(loginType, loginId, tokenValue);
        }
    }

    /**
     * 事件发布：xx 账号被封禁
     *
     * @param loginType   账号类别
     * @param loginId     账号id
     * @param service     指定服务
     * @param level       封禁等级
     * @param disableTime 封禁时长，单位: 秒
     */
    public static void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doDisable(loginType, loginId, service, level, disableTime);
        }
    }

    /**
     * 事件发布：xx 账号被解封
     *
     * @param loginType 账号类别
     * @param loginId   账号id
     * @param service   指定服务
     */
    public static void doUntieDisable(String loginType, Object loginId, String service) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doUntieDisable(loginType, loginId, service);
        }
    }

    /**
     * 事件发布：xx 账号完成二级认证
     *
     * @param loginType  账号类别
     * @param tokenValue token值
     * @param service    指定服务
     * @param safeTime   认证时间，单位：秒
     */
    public static void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doOpenSafe(loginType, tokenValue, service, safeTime);
        }
    }

    /**
     * 事件发布：xx 账号关闭二级认证
     *
     * @param loginType  账号类别
     * @param service    指定服务
     * @param tokenValue token值
     */
    public static void doCloseSafe(String loginType, String tokenValue, String service) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doCloseSafe(loginType, tokenValue, service);
        }
    }

    /**
     * 事件发布：创建了一个新的 ChillSession
     *
     * @param id SessionId
     */
    public static void doCreateSession(String id) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doCreateSession(id);
        }
    }

    /**
     * 事件发布：一个 ChillSession 注销了
     *
     * @param id SessionId
     */
    public static void doLogoutSession(String id) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doLogoutSession(id);
        }
    }

    /**
     * 事件发布：指定 Token 续期成功
     *
     * @param tokenValue token 值
     * @param loginId    账号id
     * @param timeout    续期时间
     */
    public static void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doRenewTimeout(tokenValue, loginId, timeout);
        }
    }

    /**
     * 事件发布：有新的全局组件载入到框架中
     *
     * @param compName 组件名称
     * @param compObj  组件对象
     */
    public static void doRegisterComponent(String compName, Object compObj) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doRegisterComponent(compName, compObj);
        }
    }

    /**
     * 事件发布：有新的 AuthLoginLogic 载入到框架中
     *
     * @param stpLogic /
     */
    public static void doSetStpLogic(AuthLoginLogic stpLogic) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doSetStpLogic(stpLogic);
        }
    }

    /**
     * 事件发布：有新的全局配置载入到框架中
     *
     * @param config /
     */
    public static void doSetConfig(ChillTokenConfig config) {
        for (ChillTokenListener listener : tokenListeners) {
            listener.doSetConfig(config);
        }
    }

}
