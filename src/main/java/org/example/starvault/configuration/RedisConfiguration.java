package org.example.starvault.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@Configuration
public class RedisConfiguration
{
    @Autowired
    RedisConnectionFactory connection;

    @Bean
    public RedisTemplate<String, List<String>> redisTemplate()
    {
        RedisTemplate<String, List<String>> template = new RedisTemplate<>();
        StringRedisSerializer serializer = new StringRedisSerializer();
        template.setConnectionFactory(connection);
        template.setKeySerializer(serializer);
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(List.class));

        return template;
    }
}
