package com.chill.login.common.enums;


import com.chill.login.common.exception.AuthException;
import com.chill.login.config.AuthSource;


/**
 * 各平台url：授权url、获取授权token、用户信息url ...其他等
 *
 * @author chill
 */
public enum AuthDefaultSource implements AuthSource {
    /**
     * Github
     */
    GITHUB {
        @Override
        public String authorize() {
            return "https://github.com/login/oauth/authorize";
        }

        @Override
        public String accessToken() {
            return "https://github.com/login/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.github.com/user";
        }
    },
    /**
     * 新浪微博
     */
    WEIBO {
        @Override
        public String authorize() {
            return "https://api.weibo.com/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://api.weibo.com/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.weibo.com/2/users/show.json";
        }

        @Override
        public String revoke() {
            return "https://api.weibo.com/oauth2/revokeoauth2";
        }
    },
    /**
     * gitee
     */
    GITEE {
        @Override
        public String authorize() {
            return "https://gitee.com/oauth/authorize";
        }

        @Override
        public String accessToken() {
            return "https://gitee.com/oauth/token";
        }

        @Override
        public String userInfo() {
            return "https://gitee.com/api/v5/user";
        }
    },
    /**
     * 钉钉
     */
    DINGTALK {
        @Override
        public String authorize() {
            return "https://oapi.dingtalk.com/connect/qrconnect";
        }

        @Override
        public String accessToken() {
            throw new AuthException(AuthResponseStatus.UNSUPPORTED);
        }

        @Override
        public String userInfo() {
            return "https://oapi.dingtalk.com/sns/getuserinfo_bycode";
        }
    },
    /**
     * 百度
     */
    BAIDU {
        @Override
        public String authorize() {
            return "https://openapi.baidu.com/oauth/2.0/authorize";
        }

        @Override
        public String accessToken() {
            return "https://openapi.baidu.com/oauth/2.0/token";
        }

        @Override
        public String userInfo() {
            return "https://openapi.baidu.com/rest/2.0/passport/users/getInfo";
        }

        @Override
        public String revoke() {
            return "https://openapi.baidu.com/rest/2.0/passport/auth/revokeAuthorization";
        }

        @Override
        public String refresh() {
            return "https://openapi.baidu.com/oauth/2.0/token";
        }
    },
    /**
     * csdn
     */
    CSDN {
        @Override
        public String authorize() {
            return "https://api.csdn.net/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://api.csdn.net/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.csdn.net/user/getinfo";
        }
    },

    /**
     * 支付宝
     */
    ALIPAY {
        @Override
        public String authorize() {
            return "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm";
        }

        @Override
        public String accessToken() {
            return "https://openapi.alipay.com/gateway.do";
        }

        @Override
        public String userInfo() {
            return "https://openapi.alipay.com/gateway.do";
        }
    },
    /**
     * QQ
     */
    QQ {
        @Override
        public String authorize() {
            return "https://graph.qq.com/oauth2.0/authorize";
        }

        @Override
        public String accessToken() {
            return "https://graph.qq.com/oauth2.0/token";
        }

        @Override
        public String userInfo() {
            return "https://graph.qq.com/user/get_user_info";
        }

        @Override
        public String refresh() {
            return "https://graph.qq.com/oauth2.0/token";
        }
    },
    /**
     * 微信开放平台
     */
    WECHAT_OPEN {
        @Override
        public String authorize() {
            return "https://open.weixin.qq.com/connect/qrconnect";
        }

        @Override
        public String accessToken() {
            return "https://api.weixin.qq.com/sns/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.weixin.qq.com/sns/userinfo";
        }

        @Override
        public String refresh() {
            return "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        }
    },
    /**
     * 微信公众平台
     */
    WECHAT_MP {
        @Override
        public String authorize() {
            return "https://open.weixin.qq.com/connect/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://api.weixin.qq.com/sns/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.weixin.qq.com/sns/userinfo";
        }

        @Override
        public String refresh() {
            return "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        }
    },
    /**
     * 淘宝
     */
    TAOBAO {
        @Override
        public String authorize() {
            return "https://oauth.taobao.com/authorize";
        }

        @Override
        public String accessToken() {
            return "https://oauth.taobao.com/token";
        }

        @Override
        public String userInfo() {
            throw new AuthException(AuthResponseStatus.UNSUPPORTED);
        }
    },
    /**
     * Google
     */
    GOOGLE {
        @Override
        public String authorize() {
            return "https://accounts.google.com/o/oauth2/v2/auth";
        }

        @Override
        public String accessToken() {
            return "https://www.googleapis.com/oauth2/v4/token";
        }

        @Override
        public String userInfo() {
            return "https://www.googleapis.com/oauth2/v3/userinfo";
        }
    },
    /**
     * 抖音
     */
    DOUYIN {
        @Override
        public String authorize() {
            return "https://open.douyin.com/platform/oauth/connect";
        }

        @Override
        public String accessToken() {
            return "https://open.douyin.com/oauth/access_token/";
        }

        @Override
        public String userInfo() {
            return "https://open.douyin.com/oauth/userinfo/";
        }

        @Override
        public String refresh() {
            return "https://open.douyin.com/oauth/refresh_token/";
        }
    },
    /**
     * 微软
     */
    MICROSOFT {
        @Override
        public String authorize() {
            return "https://login.microsoftonline.com/common/oauth2/v2.0/authorize";
        }

        @Override
        public String accessToken() {
            return "https://login.microsoftonline.com/common/oauth2/v2.0/token";
        }

        @Override
        public String userInfo() {
            return "https://graph.microsoft.com/v1.0/me";
        }

        @Override
        public String refresh() {
            return "https://login.microsoftonline.com/common/oauth2/v2.0/token";
        }
    },
    /**
     * 企业微信二维码登录
     *
     * @since 1.0
     */
    WECHAT_ENTERPRISE {
        @Override
        public String authorize() {
            return "https://open.work.weixin.qq.com/wwopen/sso/qrConnect";
        }

        @Override
        public String accessToken() {
            return "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        }

        @Override
        public String userInfo() {
            return "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
        }
    },

    /**
     * 企业微信网页登录
     */
    WECHAT_ENTERPRISE_WEB {
        @Override
        public String authorize() {
            return "https://open.weixin.qq.com/connect/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        }

        @Override
        public String userInfo() {
            return "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
        }
    },

    /**
     * Gitlab
     *
     * @since 1.11.0
     */
    GITLAB {
        @Override
        public String authorize() {
            return "https://gitlab.com/oauth/authorize";
        }

        @Override
        public String accessToken() {
            return "https://gitlab.com/oauth/token";
        }

        @Override
        public String userInfo() {
            return "https://gitlab.com/api/v4/user";
        }
    },

    /**
     * Twitter
     *
     * @since 1.0
     */
    TWITTER {
        @Override
        public String authorize() {
            return "https://api.twitter.com/oauth/authenticate";
        }

        @Override
        public String accessToken() {
            return "https://api.twitter.com/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.twitter.com/1.1/users/show.json";
        }
    },

    /**
     * 京东
     *
     * @since 1.15.0
     */
    JD {
        @Override
        public String authorize() {
            return "https://open-oauth.jd.com/oauth2/to_login";
        }

        @Override
        public String accessToken() {
            return "https://open-oauth.jd.com/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.jd.com/routerjson";
        }

        @Override
        public String refresh() {
            return "https://open-oauth.jd.com/oauth2/refresh_token";
        }
    },

    /**
     * 阿里云
     */
    ALIYUN {
        @Override
        public String authorize() {
            return "https://signin.aliyun.com/oauth2/v1/auth";
        }

        @Override
        public String accessToken() {
            return "https://oauth.aliyun.com/v1/token";
        }

        @Override
        public String userInfo() {
            return "https://oauth.aliyun.com/v1/userinfo";
        }

        @Override
        public String refresh() {
            return "https://oauth.aliyun.com/v1/token";
        }
    },
}
