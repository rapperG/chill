package com.chill.token;

import com.chill.token.config.ChillTokenConfig;
import com.chill.token.config.ChillTokenConfigFactory;
import com.chill.token.context.ChillTokenContext;
import com.chill.token.context.ChillTokenContextDefaultImpl;
import com.chill.token.context.second.ChillTokenSecondContext;
import com.chill.token.dao.ChillTokenDao;
import com.chill.token.dao.ChillTokenDaoDefaultImpl;
import com.chill.token.error.ChillErrorCode;
import com.chill.token.exception.basic.TokenException;
import com.chill.token.json.ChillJsonTemplate;
import com.chill.token.json.ChillJsonTemplateDefaultImpl;
import com.chill.token.listener.ChillTokenEventCenter;
import com.chill.token.log.ChillLog;
import com.chill.token.log.ChillLogForConsole;
import com.chill.token.same.ChillSameTemplate;
import com.chill.token.sign.ChillSignTemplate;
import com.chill.token.stp.AuthDataInterface;
import com.chill.token.stp.AuthDataInterfaceDefaultImpl;
import com.chill.token.stp.AuthLoginLogic;
import com.chill.token.stp.AuthLoginUtil;
import com.chill.token.strategy.ChillStrategy;
import com.chill.token.temp.ChillTempInterface;
import com.chill.token.temp.ChillTempDefaultImpl;
import com.chill.token.util.ChillFoxUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 管理 Chill-Token 所有全局组件，可通过此类快速获取、写入各种全局组件对象
 *
 * @author chill
 * @since 1.0
 */
public class ChillManager {

    /**
     * 全局配置对象
     */
    public volatile static ChillTokenConfig config;

    public static void setConfig(ChillTokenConfig config) {
        setConfigMethod(config);

        // 打印 banner
        if (config != null && config.getIsPrint()) {
            ChillFoxUtil.printBanner();
        }

        // 如果此 config 对象没有配置 isColorLog 的值，则框架为它自动判断一下
        if (config != null && config.getIsLog() != null && config.getIsLog() && config.getIsColorLog() == null) {
            config.setIsColorLog(ChillFoxUtil.isCanColorLog());
        }

        // $$ 全局事件
        ChillTokenEventCenter.doSetConfig(config);

        // 调用一次 AuthLoginUtil 中的方法，保证其可以尽早的初始化 AuthLoginLogic
        AuthLoginUtil.getLoginType();
    }

    private static void setConfigMethod(ChillTokenConfig config) {
        ChillManager.config = config;
    }

    /**
     * 获取 Chill-Token 的全局配置信息
     *
     * @return 全局配置信息
     */
    public static ChillTokenConfig getConfig() {
        if (config == null) {
            synchronized (ChillManager.class) {
                if (config == null) {
                    setConfigMethod(ChillTokenConfigFactory.createConfig());
                }
            }
        }
        return config;
    }

    /**
     * 持久化组件
     */
    private volatile static ChillTokenDao chillTokenDao;

    public static void setChillTokenDao(ChillTokenDao chillTokenDao) {
        setChillTokenDaoMethod(chillTokenDao);
        ChillTokenEventCenter.doRegisterComponent("ChillTokenDao", chillTokenDao);
    }

    private static void setChillTokenDaoMethod(ChillTokenDao chillTokenDao) {
        if ((ChillManager.chillTokenDao instanceof ChillTokenDaoDefaultImpl)) {
            ((ChillTokenDaoDefaultImpl) ChillManager.chillTokenDao).endRefreshThread();
        }
        ChillManager.chillTokenDao = chillTokenDao;
    }

    public static ChillTokenDao getChillTokenDao() {
        if (chillTokenDao == null) {
            synchronized (ChillManager.class) {
                if (chillTokenDao == null) {
                    setChillTokenDaoMethod(new ChillTokenDaoDefaultImpl());
                }
            }
        }
        return chillTokenDao;
    }

    /**
     * 权限数据源组件
     */
    private volatile static AuthDataInterface authDataInterface;

    public static void setAuthDataInterface(AuthDataInterface authDataInterface) {
        ChillManager.authDataInterface = authDataInterface;
        ChillTokenEventCenter.doRegisterComponent("AuthDataInterface", authDataInterface);
    }

    public static AuthDataInterface getAuthDataInterface() {
        if (authDataInterface == null) {
            synchronized (ChillManager.class) {
                if (authDataInterface == null) {
                    ChillManager.authDataInterface = new AuthDataInterfaceDefaultImpl();
                }
            }
        }
        return authDataInterface;
    }

    /**
     * 一级上下文 SaTokenContextContext
     */
    private volatile static ChillTokenContext chillTokenContext;

    public static void setChillTokenContext(ChillTokenContext chillTokenContext) {
        ChillManager.chillTokenContext = chillTokenContext;
        ChillTokenEventCenter.doRegisterComponent("ChillTokenContext", chillTokenContext);
    }

    public static ChillTokenContext getChillTokenContext() {
        return chillTokenContext;
    }

    /**
     * 二级上下文 ChillTokenSecondContext
     */
    private volatile static ChillTokenSecondContext chillTokenSecondContext;

    public static void setChillTokenSecondContext(ChillTokenSecondContext chillTokenSecondContext) {
        ChillManager.chillTokenSecondContext = chillTokenSecondContext;
        ChillTokenEventCenter.doRegisterComponent("ChillTokenSecondContext", chillTokenSecondContext);
    }

    public static ChillTokenSecondContext getChillTokenSecondContext() {
        return chillTokenSecondContext;
    }

    /**
     * 获取一个可用的 ChillTokenContext （按照一级上下文、二级上下文、默认上下文的顺序来判断）
     *
     * @return /
     */
    public static ChillTokenContext getChillTokenContextOrSecond() {

        // s1. 一级Context可用时返回一级Context
        if (chillTokenContext != null) {
            if (chillTokenSecondContext == null || chillTokenContext.isValid()) {
                // 因为 isValid 是一个耗时操作，所以此处假定：二级Context为null的情况下无需验证一级Context有效性
                // 这样可以提升6倍左右的上下文获取速度
                return chillTokenContext;
            }
        }

        // s2. 一级Context不可用时判断二级Context是否可用
        if (chillTokenSecondContext != null && chillTokenSecondContext.isValid()) {
            return chillTokenSecondContext;
        }

        // s3. 都不行，就返回默认的 Context
        return ChillTokenContextDefaultImpl.defaultContext;
    }

    /**
     * 临时 token 认证模块
     */
    private volatile static ChillTempInterface chillTempInterface;

    public static void setChillTempInterface(ChillTempInterface chillTempInterface) {
        ChillManager.chillTempInterface = chillTempInterface;
        ChillTokenEventCenter.doRegisterComponent("ChillTempInterface", chillTempInterface);
    }

    public static ChillTempInterface getChillTempInterface() {
        if (chillTempInterface == null) {
            synchronized (ChillManager.class) {
                if (chillTempInterface == null) {
                    ChillManager.chillTempInterface = new ChillTempDefaultImpl();
                }
            }
        }
        return chillTempInterface;
    }

    /**
     * JSON 转换器
     */
    private volatile static ChillJsonTemplate chillJsonTemplate;

    public static void setChillJsonTemplate(ChillJsonTemplate chillJsonTemplate) {
        ChillManager.chillJsonTemplate = chillJsonTemplate;
        ChillTokenEventCenter.doRegisterComponent("ChillJsonTemplate", chillJsonTemplate);
    }

    public static ChillJsonTemplate getChillJsonTemplate() {
        if (chillJsonTemplate == null) {
            synchronized (ChillManager.class) {
                if (chillJsonTemplate == null) {
                    ChillManager.chillJsonTemplate = new ChillJsonTemplateDefaultImpl();
                }
            }
        }
        return chillJsonTemplate;
    }

    /**
     * API 参数签名
     */
    private volatile static ChillSignTemplate chillSignTemplate;

    public static void setChillSignTemplate(ChillSignTemplate chillSignTemplate) {
        ChillManager.chillSignTemplate = chillSignTemplate;
        ChillTokenEventCenter.doRegisterComponent("ChillSignTemplate", chillSignTemplate);
    }

    public static ChillSignTemplate getChillSignTemplate() {
        if (chillSignTemplate == null) {
            synchronized (ChillManager.class) {
                if (chillSignTemplate == null) {
                    ChillManager.chillSignTemplate = new ChillSignTemplate();
                }
            }
        }
        return chillSignTemplate;
    }

    /**
     * Same-Token 同源系统认证模块
     */
    private volatile static ChillSameTemplate chillSameTemplate;

    public static void setChillSameTemplate(ChillSameTemplate chillSameTemplate) {
        ChillManager.chillSameTemplate = chillSameTemplate;
        ChillTokenEventCenter.doRegisterComponent("ChillSameTemplate", chillSameTemplate);
    }

    public static ChillSameTemplate getChillSameTemplate() {
        if (chillSameTemplate == null) {
            synchronized (ChillManager.class) {
                if (chillSameTemplate == null) {
                    ChillManager.chillSameTemplate = new ChillSameTemplate();
                }
            }
        }
        return chillSameTemplate;
    }

    /**
     * 日志输出器
     */
    public volatile static ChillLog log = new ChillLogForConsole();

    public static void setLog(ChillLog log) {
        ChillManager.log = log;
        ChillTokenEventCenter.doRegisterComponent("ChillLog", log);
    }

    public static ChillLog getLog() {
        return ChillManager.log;
    }

    /**
     * AuthLoginLogic 集合, 记录框架所有成功初始化的 AuthLoginLogic
     */
    public static Map<String, AuthLoginLogic> authLoginLogicMapping = new LinkedHashMap<>();

    /**
     * 向全局集合中 put 一个 AuthLoginLogic
     *
     * @param authLoginLogic AuthLoginLogic
     */
    public static void putAuthLoginLogicComponent(AuthLoginLogic authLoginLogic) {
        authLoginLogicMapping.put(authLoginLogic.getLoginType(), authLoginLogic);
    }

    /**
     * 在全局集合中 移除 一个 AuthLoginLogic
     */
    public static void removeAuthLoginLogicComponent(String loginType) {
        authLoginLogicMapping.remove(loginType);
    }

    /**
     * 根据 LoginType 获取对应的StpLogic，如果不存在则新建并返回
     *
     * @param loginType 对应的账号类型
     * @return 对应的StpLogic
     */
    public static AuthLoginLogic getAuthLoginLogicComponent(String loginType) {
        return getAuthLoginLogicComponent(loginType, true);
    }

    /**
     * 根据 LoginType 获取对应的StpLogic，如果不存在，isCreate = 是否自动创建并返回
     *
     * @param loginType 对应的账号类型
     * @param isCreate  在 AuthLoginLogic 不存在时，true=新建并返回，false=抛出异常
     * @return 对应的StpLogic
     */
    public static AuthLoginLogic getAuthLoginLogicComponent(String loginType, boolean isCreate) {
        // 如果type为空则返回框架默认内置的
        if (loginType == null || loginType.isEmpty()) {
            return AuthLoginUtil.authLoginLogic;
        }

        // 从集合中获取
        AuthLoginLogic authLoginLogic = authLoginLogicMapping.get(loginType);
        if (authLoginLogic == null) {

            // isCreate=true时，自创建模式：自动创建并返回
            if (isCreate) {
                synchronized (ChillManager.class) {
                    authLoginLogic = authLoginLogicMapping.get(loginType);
                    if (authLoginLogic == null) {
                        authLoginLogic = ChillStrategy.INSTANCE.createStpLogic.apply(loginType);
                    }
                }
            }
            // isCreate=false时，严格校验模式：抛出异常
            else {
                /*
                 * 此时有两种情况会造成 AuthLoginLogic == null
                 * 1. loginType拼写错误，请改正 （建议使用常量）
                 * 2. 自定义StpUtil尚未初始化（静态类中的属性至少一次调用后才会初始化），解决方法两种
                 * 		(1) 从main方法里调用一次
                 * 		(2) 在自定义StpUtil类加上类似 @Component 的注解让容器启动时扫描到自动初始化
                 */
                throw new TokenException("未能获取对应StpLogic，type=" + loginType).setCode(ChillErrorCode.CODE_10002);
            }
        }

        // 返回
        return authLoginLogic;
    }

}
