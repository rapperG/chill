
package com.chill.token.stp;

import java.util.ArrayList;
import java.util.List;

/**
 * 对 {@link AuthDataInterface} 接口默认的实现类
 * <p>
 * 如果开发者没有实现 AuthDataInterface 接口，则框架会使用此默认实现类，所有方法都返回空集合，即：用户不具有任何权限和角色。
 *
 * @author chill
 * @since 1.0
 */
public class AuthDataInterfaceDefaultImpl implements AuthDataInterface {

    @Override
    public List<String> permissions(Object loginId, String loginType) {
        return new ArrayList<>();
    }

    @Override
    public List<String> roles(Object loginId, String loginType) {
        return new ArrayList<>();
    }

}
