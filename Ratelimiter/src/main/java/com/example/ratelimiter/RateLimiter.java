package com.example.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter
{
    private ConcurrentHashMap<String, HitCounter> map;

    public RateLimiter()
    {
        this.map = new ConcurrentHashMap<>();
    }

    public boolean isAllow(String clientId)
    {
        HitCounter hitCounter;
        if(map.containsKey(clientId))
        {
            hitCounter = map.get(clientId);
        }
        else
        {
            hitCounter = new HitCounter(Constants.REQUEST_LIMIT);
            map.put(clientId, hitCounter);
        }
        return hitCounter.hit(System.currentTimeMillis());
    }
}
