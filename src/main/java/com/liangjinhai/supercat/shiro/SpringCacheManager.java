package com.liangjinhai.supercat.shiro;

import com.liangjinhai.supercat.sys.entity.User;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.Collection;
import java.util.Set;


public class SpringCacheManager implements CacheManager {




    private org.springframework.cache.CacheManager cacheManger;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        org.springframework.cache.Cache springCache = cacheManger.getCache(name);
        return new SpringCacheWrapper<K, V>(springCache);
    }

    private class SpringCacheWrapper<K, V> implements Cache<K, V> {
        private org.springframework.cache.Cache springCache;

        public String prefix ="shiro_auth_cache-";
        public String prefix_user ="shiro_auth_cache-user";
        public String prefix_user_ ="shiro_auth_cache-user-";


        public SpringCacheWrapper(org.springframework.cache.Cache springCache) {
            this.springCache = springCache;

        }

        @Override
        public V get(K key) throws CacheException {
            Object value = springCache.get(getKey(key));
            if (value instanceof SimpleValueWrapper) {
                return (V) ((SimpleValueWrapper)value).get();
            }
            return (V) value;
        }

        @Override
        public V put(K key, V value) throws CacheException {
            springCache.put(getKey(key), value);
            return value;
        }

        @Override
        public V remove(K key) throws CacheException {
            Object value = get(key);
            springCache.evict(getKey(key));
            return (V) value;
        }

        @Override
        public void clear() throws CacheException {
            springCache.clear();
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");

        }

        @Override
        public Set<K> keys() {
            throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");

        }

        @Override
        public Collection<V> values() {
            throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
        }



        private Object getKey(K key){

            if(key instanceof SimplePrincipalCollection){
                return prefix_user+((User)((SimplePrincipalCollection) key).getPrimaryPrincipal()).getUsername();
            }else if(key instanceof User){
                return prefix_user_ + ((User) key).getUsername();
            }else if(key instanceof String){
                String preKey = prefix+key;
                return preKey;
            }else{
                return key;
            }

        }



    }


    public org.springframework.cache.CacheManager getCacheManger() {
        return cacheManger;
    }

    public void setCacheManger(org.springframework.cache.CacheManager cacheManger) {
        this.cacheManger = cacheManger;
    }
}
