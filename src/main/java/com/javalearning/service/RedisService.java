package com.javalearning.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class RedisService {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    public <T> T get(String key , Class<T> tClass){
        try{
            String str = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            assert str != null;
            return objectMapper.readValue(str.getBytes(StandardCharsets.UTF_8), tClass);
        }catch (Exception e ) {
            log.error("Exception in getting key", e);
            throw new RuntimeException("Exception in getting key");
        }
    }

    public void put(){

    }

}
