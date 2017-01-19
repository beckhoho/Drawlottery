package com.hudongwx.drawlottery.mobile.shiro;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 开发公司：hudongwx.com<br/>
 * 版权：294786949@qq.com<br/>
 * <p>
 *
 * @author origin
 * @version 1.0, 2017/1/19 0019 <br/>
 * @desc <p>
 * <p>
 * 创建　origin　2017/1/19 0019　<br/>
 * <p>
 * *******
 * <p>
 * @email 294786949@qq.com
 */
public class CustomerEnterpriseCacheSessionDAO extends EnterpriseCacheSessionDAO {
    net.sf.ehcache.CacheManager cacheManager;
    Ehcache mEhcache;

    @Override
    protected Cache<Serializable, Session> createActiveSessionsCache() {
        if(mEhcache == null){
            EhCacheManager cacheManager = (EhCacheManager) getCacheManager();
            mEhcache = cacheManager.getCacheManager().getEhcache(getActiveSessionsCacheName());
        }
        return super.createActiveSessionsCache();
    }


    @Override
    protected void cache(Session session, Serializable sessionId, Cache<Serializable, Session> cache) {
        super.cache(session, sessionId, cache);
        flush(); //刷新到缓存确保index文件更新,避免断电,重启之后数据丢失
    }

    @Override
    protected Session getCachedSession(Serializable sessionId) {
        Session cachedSession = super.getCachedSession(sessionId);
        flush();//刷新到缓存确保index文件更新,避免断电,重启之后数据丢失
        return cachedSession;
    }

    private void flush(){
        mEhcache.flush();
    }

}
