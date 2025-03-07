package com.javalearning;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
public class RedisCacheTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testCache(){
//        redisTemplate.opsForValue().set("email","test@abc");
        log.info((String) redisTemplate.opsForValue().get("email"));
        log.info((String) redisTemplate.opsForValue().get("salary"));
    }

}
