
package com.chill.token.strategy;

import com.chill.token.ChillManager;
import com.chill.token.annotation.*;
import com.chill.token.basic.ChillBasicUtil;
import com.chill.token.exception.basic.TokenException;
import com.chill.token.fun.strategy.*;
import com.chill.token.session.ChillSession;
import com.chill.token.stp.AuthLoginLogic;
import com.chill.token.util.ChillFoxUtil;
import com.chill.token.util.ChillTokenConstants;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Chill-Token 策略对象
 * <p>
 * 此类统一定义框架内的一些关键性逻辑算法，方便开发者进行按需重写，例：
 * </p>
 * <pre>
 * // SaStrategy全局单例，所有方法都用以下形式重写
 * ChillStrategy.instance.setCreateToken((loginId, loginType) -》 {
 * // 自定义Token生成的算法
 * return "xxxx";
 * });
 * </pre>
 *
 * @author chill
 * @since 1.0
 */
public final class ChillStrategy {

    private ChillStrategy() {
    }

    /**
     * 获取 ChillStrategy 对象的单例引用
     */
    public static final ChillStrategy INSTANCE = new ChillStrategy();


    // ----------------------- 所有策略

    /**
     * 创建 Token 的策略
     */
    public ChillCreateTokenFunction createToken = (loginId, loginType) -> {
        // 根据配置的tokenStyle生成不同风格的token
        String tokenStyle = ChillManager.getAuthLoginLogicComponent(loginType).getConfigOrGlobal().getTokenStyle();

        switch (tokenStyle) {
            // uuid
            case ChillTokenConstants.TOKEN_STYLE_UUID:
                return UUID.randomUUID().toString();

            // 简单uuid (不带下划线)
            case ChillTokenConstants.TOKEN_STYLE_SIMPLE_UUID:
                return UUID.randomUUID().toString().replaceAll("-", "");

            // 32位随机字符串
            case ChillTokenConstants.TOKEN_STYLE_RANDOM_32:
                return ChillFoxUtil.getRandomString(32);

            // 64位随机字符串
            case ChillTokenConstants.TOKEN_STYLE_RANDOM_64:
                return ChillFoxUtil.getRandomString(64);

            // 128位随机字符串
            case ChillTokenConstants.TOKEN_STYLE_RANDOM_128:
                return ChillFoxUtil.getRandomString(128);

            // tik风格 (2_14_16)
            case ChillTokenConstants.TOKEN_STYLE_TIK:
                return ChillFoxUtil.getRandomString(2) + "_" + ChillFoxUtil.getRandomString(14) + "_" + ChillFoxUtil.getRandomString(16) + "__";

            // 默认，还是uuid
            default:
                ChillManager.getLog().warn("配置的 tokenStyle 值无效：{}，仅允许以下取值: " +
                    "uuid、simple-uuid、random-32、random-64、random-128、tik", tokenStyle);
                return UUID.randomUUID().toString();
        }
    };

    /**
     * 创建 Session 的策略
     */
    public ChillCreateSessionFunction createSession = ChillSession::new;

    /**
     * 判断：集合中是否包含指定元素（模糊匹配）
     */
    public ChillHasElementFunction hasElement = (list, element) -> {

        // 空集合直接返回false
        if (list == null || list.size() == 0) {
            return false;
        }

        // 先尝试一下简单匹配，如果可以匹配成功则无需继续模糊匹配
        if (list.contains(element)) {
            return true;
        }

        // 开始模糊匹配
        for (String patt : list) {
            if (ChillFoxUtil.vagueMatch(patt, element)) {
                return true;
            }
        }

        // 走出for循环说明没有一个元素可以匹配成功
        return false;
    };

    /**
     * 对一个 [Method] 对象进行注解校验 （注解鉴权内部实现）
     */
    public ChillCheckMethodAnnotationFunction checkMethodAnnotation = (method) -> {

        // 先校验 Method 所属 Class 上的注解
        INSTANCE.checkElementAnnotation.accept(method.getDeclaringClass());

        // 再校验 Method 上的注解
        INSTANCE.checkElementAnnotation.accept(method);
    };

    /**
     * 对一个 [元素] 对象进行注解校验 （注解鉴权内部实现）
     */
    public ChillCheckElementAnnotationFunction checkElementAnnotation = (element) -> {

        // 校验 @ChillCheckLogin 注解
        ChillCheckLogin checkLogin = (ChillCheckLogin) ChillStrategy.INSTANCE.getAnnotation.apply(element, ChillCheckLogin.class);
        if (checkLogin != null) {
            ChillManager.getAuthLoginLogicComponent(checkLogin.type(), false).checkByAnnotation(checkLogin);
        }

        // 校验 @ChillCheckRole 注解
        ChillCheckRole checkRole = (ChillCheckRole) ChillStrategy.INSTANCE.getAnnotation.apply(element, ChillCheckRole.class);
        if (checkRole != null) {
            ChillManager.getAuthLoginLogicComponent(checkRole.type(), false).checkByAnnotation(checkRole);
        }

        // 校验 @ChillCheckPermission 注解
        ChillCheckPermission checkPermission = (ChillCheckPermission) ChillStrategy.INSTANCE.getAnnotation.apply(element, ChillCheckPermission.class);
        if (checkPermission != null) {
            ChillManager.getAuthLoginLogicComponent(checkPermission.type(), false).checkByAnnotation(checkPermission);
        }

        // 校验 @ChillCheckSafe 注解
        ChillCheckSafe checkSafe = (ChillCheckSafe) ChillStrategy.INSTANCE.getAnnotation.apply(element, ChillCheckSafe.class);
        if (checkSafe != null) {
            ChillManager.getAuthLoginLogicComponent(checkSafe.type(), false).checkByAnnotation(checkSafe);
        }

        // 校验 @ChillCheckDisable 注解
        ChillCheckDisable checkDisable = (ChillCheckDisable) ChillStrategy.INSTANCE.getAnnotation.apply(element, ChillCheckDisable.class);
        if (checkDisable != null) {
            ChillManager.getAuthLoginLogicComponent(checkDisable.type(), false).checkByAnnotation(checkDisable);
        }

        // 校验 @ChillCheckBasic 注解
        ChillCheckBasic checkBasic = (ChillCheckBasic) ChillStrategy.INSTANCE.getAnnotation.apply(element, ChillCheckBasic.class);
        if (checkBasic != null) {
            ChillBasicUtil.check(checkBasic.realm(), checkBasic.account());
        }

        // 校验 @ChillCheckOr 注解
        ChillCheckOr checkOr = (ChillCheckOr) ChillStrategy.INSTANCE.getAnnotation.apply(element, ChillCheckOr.class);
        if (checkOr != null) {
            ChillStrategy.INSTANCE.checkOrAnnotation.accept(checkOr);
        }
    };

    /**
     * 对一个 @ChillCheckOr 进行注解校验
     */
    public ChillCheckOrAnnotationFunction checkOrAnnotation = (at) -> {

        // 记录校验过程中所有的异常
        List<TokenException> errorList = new ArrayList<>();

        // 逐个开始校验 >>>

        // 1、校验注解：@ChillCheckLogin
        ChillCheckLogin[] checkLoginArray = at.login();
        for (ChillCheckLogin item : checkLoginArray) {
            try {
                ChillManager.getAuthLoginLogicComponent(item.type(), false).checkByAnnotation(item);
                return;
            } catch (TokenException e) {
                errorList.add(e);
            }
        }

        // 2、校验注解：@ChillCheckRole
        ChillCheckRole[] checkRoleArray = at.role();
        for (ChillCheckRole item : checkRoleArray) {
            try {
                ChillManager.getAuthLoginLogicComponent(item.type(), false).checkByAnnotation(item);
                return;
            } catch (TokenException e) {
                errorList.add(e);
            }
        }

        // 3、校验注解：@ChillCheckPermission
        ChillCheckPermission[] checkPermissionArray = at.permission();
        for (ChillCheckPermission item : checkPermissionArray) {
            try {
                ChillManager.getAuthLoginLogicComponent(item.type(), false).checkByAnnotation(item);
                return;
            } catch (TokenException e) {
                errorList.add(e);
            }
        }

        // 4、校验注解：@ChillCheckSafe
        ChillCheckSafe[] checkSafeArray = at.safe();
        for (ChillCheckSafe item : checkSafeArray) {
            try {
                ChillManager.getAuthLoginLogicComponent(item.type(), false).checkByAnnotation(item);
                return;
            } catch (TokenException e) {
                errorList.add(e);
            }
        }

        // 5、校验注解：@ChillCheckDisable
        ChillCheckDisable[] checkDisableArray = at.disable();
        for (ChillCheckDisable item : checkDisableArray) {
            try {
                ChillManager.getAuthLoginLogicComponent(item.type(), false).checkByAnnotation(item);
                return;
            } catch (TokenException e) {
                errorList.add(e);
            }
        }

        // 6、校验注解：@ChillCheckBasic
        ChillCheckBasic[] checkBasicArray = at.basic();
        for (ChillCheckBasic item : checkBasicArray) {
            try {
                ChillBasicUtil.check(item.realm(), item.account());
                return;
            } catch (TokenException e) {
                errorList.add(e);
            }
        }

        // 如果执行到这里，有两种可能：
        //		可能 1. ChillCheckOr 注解上不包含任何注解校验，此时 errorList 里面一个异常都没有，我们直接跳过即可
        //		可能 2. 所有注解校验都通过不了，此时 errorList 里面会有多个异常，我们随便抛出一个即可
        if (errorList.size() == 0) {
            // return;
        } else {
            throw errorList.get(0);
        }
    };

    // 默认使用jdk的注解处理器
    /**
     * 从元素上获取注解
     */
    public ChillGetAnnotationFunction getAnnotation = AnnotatedElement::getAnnotation;

    /**
     * 判断一个 Method 或其所属 Class 是否包含指定注解
     */
    public ChillIsAnnotationPresentFunction isAnnotationPresent = (method, annotationClass) -> INSTANCE.getAnnotation.apply(method, annotationClass) != null ||
        INSTANCE.getAnnotation.apply(method.getDeclaringClass(), annotationClass) != null;

    /**
     * 生成唯一式 token 的算法
     */
    public ChillGenerateUniqueTokenFunction generateUniqueToken = (elementName, maxTryTimes, createTokenFunction, checkTokenFunction) -> {

        // 为方便叙述，以下代码注释均假设在处理生成 token 的场景，但实际上本方法也可能被用于生成 code、ticket 等

        // 循环生成
        for (int i = 1; ; i++) {
            // 生成 token
            String token = createTokenFunction.get();

            // 如果 maxTryTimes == -1，表示不做唯一性验证，直接返回
            if (maxTryTimes == -1) {
                return token;
            }

            // 如果 token 在DB库查询不到数据，说明是个可用的全新 token，直接返回
            if (checkTokenFunction.apply(token)) {
                return token;
            }

            // 如果已经循环了 maxTryTimes 次，仍然没有创建出可用的 token，那么抛出异常
            if (i >= maxTryTimes) {
                throw new TokenException(elementName + " 生成失败，已尝试" + i + "次，生成算法过于简单或资源池已耗尽");
            }
        }
    };

    /**
     * 创建 AuthLoginLogic 的算法
     */
    public ChillCreateStpLogicFunction createStpLogic = AuthLoginLogic::new;


    // ----------------------- 重写策略 set连缀风格

    /**
     * 重写创建 Token 的策略
     *
     * @param createToken /
     * @return /
     */
    public ChillStrategy setCreateToken(ChillCreateTokenFunction createToken) {
        this.createToken = createToken;
        return this;
    }

    /**
     * 重写创建 Session 的策略
     *
     * @param createSession /
     * @return /
     */
    public ChillStrategy setCreateSession(ChillCreateSessionFunction createSession) {
        this.createSession = createSession;
        return this;
    }

    /**
     * 判断：集合中是否包含指定元素（模糊匹配）
     *
     * @param hasElement /
     * @return /
     */
    public ChillStrategy setHasElement(ChillHasElementFunction hasElement) {
        this.hasElement = hasElement;
        return this;
    }

    /**
     * 对一个 [Method] 对象进行注解校验 （注解鉴权内部实现）
     *
     * @param checkMethodAnnotation /
     * @return /
     */
    public ChillStrategy setCheckMethodAnnotation(ChillCheckMethodAnnotationFunction checkMethodAnnotation) {
        this.checkMethodAnnotation = checkMethodAnnotation;
        return this;
    }

    /**
     * 对一个 [元素] 对象进行注解校验 （注解鉴权内部实现）
     *
     * @param checkElementAnnotation /
     * @return /
     */
    public ChillStrategy setCheckElementAnnotation(ChillCheckElementAnnotationFunction checkElementAnnotation) {
        this.checkElementAnnotation = checkElementAnnotation;
        return this;
    }

    /**
     * 对一个 @ChillCheckOr 进行注解校验
     * <p> 参数 [ChillCheckOr 注解的实例]
     *
     * @param checkOrAnnotation /
     * @return /
     */
    public ChillStrategy setCheckOrAnnotation(ChillCheckOrAnnotationFunction checkOrAnnotation) {
        this.checkOrAnnotation = checkOrAnnotation;
        return this;
    }

    /**
     * 从元素上获取注解
     *
     * @param getAnnotation /
     * @return /
     */
    public ChillStrategy setGetAnnotation(ChillGetAnnotationFunction getAnnotation) {
        this.getAnnotation = getAnnotation;
        return this;
    }

    /**
     * 判断一个 Method 或其所属 Class 是否包含指定注解
     *
     * @param isAnnotationPresent /
     * @return /
     */
    public ChillStrategy setIsAnnotationPresent(ChillIsAnnotationPresentFunction isAnnotationPresent) {
        this.isAnnotationPresent = isAnnotationPresent;
        return this;
    }

    /**
     * 生成唯一式 token 的算法
     *
     * @param generateUniqueToken /
     * @return /
     */
    public ChillStrategy setGenerateUniqueToken(ChillGenerateUniqueTokenFunction generateUniqueToken) {
        this.generateUniqueToken = generateUniqueToken;
        return this;
    }

    /**
     * 创建 AuthLoginLogic 的算法
     *
     * @param createStpLogic /
     * @return /
     */
    public ChillStrategy setCreateStpLogic(ChillCreateStpLogicFunction createStpLogic) {
        this.createStpLogic = createStpLogic;
        return this;
    }
}
