
package com.chill.token.listener;

import com.chill.token.stp.ChillLoginModel;

/**
 * Chill-Token 侦听器，默认空实现
 *
 * <p> 对所有事件方法提供空实现，方便开发者通过继承此类快速实现一个可用的侦听器 </p>
 *
 * @author chill
 * @since 1.0
 */
public class ChillTokenListenerForSimple implements ChillTokenListener {

    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, ChillLoginModel loginModel) {

    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {

    }

    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {

    }

    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {

    }

    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {

    }

    @Override
    public void doCreateSession(String id) {

    }

    @Override
    public void doLogoutSession(String id) {

    }

    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {

    }


}
