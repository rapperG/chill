
package com.chill.token.session;

import com.chill.token.ChillManager;
import com.chill.token.strategy.ChillStrategy;
import com.chill.token.util.ChillTokenConstants;

/**
 * 自定义 ChillSession 工具类，快捷的读取、操作自定义 ChillSession
 *
 * <p>样例：
 * <pre>
 * 		// 在一处代码写入数据
 * 		ChillSession session = ChillSessionCustomUtil.getSessionById("role-" + 1001);
 * 		session.set("count", 1);
 *
 * 		// 在另一处代码获取数据
 * 		ChillSession session = ChillSessionCustomUtil.getSessionById("role-" + 1001);
 * 		int count = session.getInt("count");
 * 		System.out.println("count=" + count);
 * </pre>
 *
 * @author chill
 * @since 1.0
 */
public class ChillSessionCustomUtil {

    private ChillSessionCustomUtil() {
    }

    /**
     * 添加上指定前缀，防止恶意伪造数据
     */
    public static String sessionKey = "custom";

    /**
     * 拼接Key: 在存储自定义 ChillSession 时应该使用的 key
     *
     * @param sessionId 会话id
     * @return sessionId
     */
    public static String splicingSessionKey(String sessionId) {
        return ChillManager.getConfig().getTokenName() + ":" + sessionKey + ":session:" + sessionId;
    }

    /**
     * 判断：指定 key 的 ChillSession 是否存在
     *
     * @param sessionId ChillSession 的 id
     * @return 是否存在
     */
    public static boolean isExists(String sessionId) {
        return ChillManager.getChillTokenDao().getSession(splicingSessionKey(sessionId)) != null;
    }

    /**
     * 获取指定 key 的 ChillSession 对象, 如果此 ChillSession 尚未在 DB 创建，isCreate 参数代表是否则新建并返回
     *
     * @param sessionId ChillSession 的 id
     * @param isCreate  如果此 ChillSession 尚未在 DB 创建，是否新建并返回
     * @return ChillSession 对象
     */
    public static ChillSession getSessionById(String sessionId, boolean isCreate) {
        ChillSession session = ChillManager.getChillTokenDao().getSession(splicingSessionKey(sessionId));
        if (session == null && isCreate) {
            session = ChillStrategy.INSTANCE.createSession.apply(splicingSessionKey(sessionId));
            session.setType(ChillTokenConstants.SESSION_TYPE__CUSTOM);
            ChillManager.getChillTokenDao().setSession(session, ChillManager.getConfig().getTimeout());
        }
        return session;
    }

    /**
     * 获取指定 key 的 ChillSession, 如果此 ChillSession 尚未在 DB 创建，则新建并返回
     *
     * @param sessionId ChillSession 的 id
     * @return ChillSession 对象
     */
    public static ChillSession getSessionById(String sessionId) {
        return getSessionById(sessionId, true);
    }

    /**
     * 删除指定 key 的 ChillSession
     *
     * @param sessionId ChillSession 的 id
     */
    public static void deleteSessionById(String sessionId) {
        ChillManager.getChillTokenDao().deleteSession(splicingSessionKey(sessionId));
    }

}
