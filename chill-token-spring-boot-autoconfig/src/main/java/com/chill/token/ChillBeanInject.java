
package com.chill.token;

import com.chill.token.basic.ChillBasicTemplate;
import com.chill.token.basic.ChillBasicUtil;
import com.chill.token.config.ChillTokenConfig;
import com.chill.token.context.ChillTokenContext;
import com.chill.token.context.second.ChillTokenSecondContextCreator;
import com.chill.token.dao.ChillTokenDao;
import com.chill.token.json.ChillJsonTemplate;
import com.chill.token.listener.ChillTokenEventCenter;
import com.chill.token.listener.ChillTokenListener;
import com.chill.token.log.ChillLog;
import com.chill.token.same.ChillSameTemplate;
import com.chill.token.sign.ChillSignTemplate;
import com.chill.token.stp.AuthDataInterface;
import com.chill.token.stp.AuthLoginLogic;
import com.chill.token.stp.AuthLoginUtil;
import com.chill.token.temp.ChillTempInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * 注入 Chill-Token 所需要的 Bean
 *
 * @author chill
 * @since 1.0
 */
public class ChillBeanInject {

    /**
     * 组件注入
     * <p> 为确保 Log 组件正常打印，必须将 ChillLog 和 ChillTokenConfig 率先初始化 </p>
     *
     * @param log              log 对象
     * @param chillTokenConfig 配置对象
     */
    public ChillBeanInject(
        @Autowired(required = false) ChillLog log,
        @Autowired(required = false) ChillTokenConfig chillTokenConfig
    ) {
        if (log != null) {
            ChillManager.setLog(log);
        }
        if (chillTokenConfig != null) {
            ChillManager.setConfig(chillTokenConfig);
        }
    }

    /**
     * 注入持久化Bean
     *
     * @param chillTokenDao /
     */
    @Autowired(required = false)
    public void setChillTokenDao(ChillTokenDao chillTokenDao) {
        ChillManager.setChillTokenDao(chillTokenDao);
    }

    /**
     * 注入权限认证Bean
     *
     * @param authDataInterface /
     */
    @Autowired(required = false)
    public void setAuthDataInterface(AuthDataInterface authDataInterface) {
        ChillManager.setAuthDataInterface(authDataInterface);
    }

    /**
     * 注入上下文Bean
     *
     * @param chillTokenContext /
     */
    @Autowired(required = false)
    public void setChillTokenContext(ChillTokenContext chillTokenContext) {
        ChillManager.setChillTokenContext(chillTokenContext);
    }

    /**
     * 注入二级上下文Bean
     *
     * @param chillTokenSecondContextCreator 二级上下文创建器
     */
    @Autowired(required = false)
    public void setChillTokenContext(ChillTokenSecondContextCreator chillTokenSecondContextCreator) {
        ChillManager.setChillTokenSecondContext(chillTokenSecondContextCreator.create());
    }

    /**
     * 注入侦听器Bean
     *
     * @param listeners 侦听器集合
     */
    @Autowired(required = false)
    public void setChillTokenListener(List<ChillTokenListener> listeners) {
        ChillTokenEventCenter.registerListenerList(listeners);
    }

    /**
     * 注入临时令牌验证模块 Bean
     *
     * @param chillTempInterface /
     */
    @Autowired(required = false)
    public void setChillTempInterface(ChillTempInterface chillTempInterface) {
        ChillManager.setChillTempInterface(chillTempInterface);
    }

    /**
     * 注入 Same-Token 模块 Bean
     *
     * @param chillSameTemplate /
     */
    @Autowired(required = false)
    public void setChillSameTemplate(ChillSameTemplate chillSameTemplate) {
        ChillManager.setChillSameTemplate(chillSameTemplate);
    }

    /**
     * 注入 Chill-Token Http Basic 认证模块
     *
     * @param chillBasicTemplate /
     */
    @Autowired(required = false)
    public void setChillBasicTemplate(ChillBasicTemplate chillBasicTemplate) {
        ChillBasicUtil.chillBasicTemplate = chillBasicTemplate;
    }

    /**
     * 注入自定义的 JSON 转换器 Bean
     *
     * @param chillJsonTemplate JSON 转换器
     */
    @Autowired(required = false)
    public void setChillJsonTemplate(ChillJsonTemplate chillJsonTemplate) {
        ChillManager.setChillJsonTemplate(chillJsonTemplate);
    }

    /**
     * 注入自定义的 参数签名 Bean
     *
     * @param chillSignTemplate 参数签名 Bean
     */
    @Autowired(required = false)
    public void setChillSignTemplate(ChillSignTemplate chillSignTemplate) {
        ChillManager.setChillSignTemplate(chillSignTemplate);
    }

    /**
     * 注入自定义的 AuthLoginLogic
     *
     * @param authLoginLogic /
     */
    @Autowired(required = false)
    public void setAuthLoginLogic(AuthLoginLogic authLoginLogic) {
        AuthLoginUtil.setAuthLoginLogic(authLoginLogic);
    }

    /**
     * 利用自动注入特性，获取Spring框架内部使用的路由匹配器
     *
     * @param pathMatcher 要设置的 pathMatcher
     */
    @Autowired(required = false)
    @Qualifier("mvcPathMatcher")
    public void setPathMatcher(PathMatcher pathMatcher) {
        ChillPathMatcherHolder.setPathMatcher(pathMatcher);
    }

}
