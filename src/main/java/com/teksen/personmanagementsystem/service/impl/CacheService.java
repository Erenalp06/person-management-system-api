package com.teksen.personmanagementsystem.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CacheService {

    private final CacheManager cacheManager;

    public void clearCache(String cacheName) {
        Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
    }

    public void addCache(String cacheName, Object key, Object value) {
        Objects.requireNonNull(cacheManager.getCache(cacheName)).put(key, value);
    }

    public void removeFromCache(String cacheName, Long id) {
        Objects.requireNonNull(cacheManager.getCache(cacheName)).evictIfPresent(id);
    }
}
