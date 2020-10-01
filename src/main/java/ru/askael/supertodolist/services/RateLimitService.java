package ru.askael.supertodolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Alikin E.A. on 2020-10-01.
 */
@Service
public class RateLimitService {

    private static final int LIMIT = 10;//todo make annotation
    private static final int EXPIRATION_TIME_IN_SECONDS = 20;


    @Autowired
    private RedisService redisService;

    public long tryConsumeAndReturnRemaining(String userName) {
        long bucketSize = redisService.incrementAndGetWithExpiration(userName, EXPIRATION_TIME_IN_SECONDS);
        long remaining =  LIMIT - bucketSize;

        if (remaining < 0) {
            return 0;
        }

        return remaining;
    }

}
