package ru.askael.supertodolist.services;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alikin E.A. on 2020-10-01.
 */
@Service
public class RedisService {

    @Value("${redisHost}")
    private String redisHost;

    @Value("${redisPort}")
    private String redisPort;

    private static volatile RedissonClient CLIENT;

    public long incrementAndGetWithExpiration(String userName, long expirationSeconds) {
        RAtomicLong atomicLong = getClientInstance().getAtomicLong(userName);
        if (atomicLong.isExists()) {
            return atomicLong.incrementAndGet();
        }
        atomicLong.set(1);
        atomicLong.expire(expirationSeconds, TimeUnit.SECONDS);
        return 1;
    }

    private RedissonClient getClientInstance() {
        if (CLIENT == null) {
            Config config = new Config();
            config.useSingleServer()
                    .setAddress("redis://" + redisHost +":" +redisPort);
            CLIENT = Redisson.create(config);
        }

        return CLIENT;
    }

}
