//package com.company.eterny.global.config;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//
///**
// * Redis 캐시 설정
// */
//@Configuration
//@EnableCaching
//public class RedisConfig {
//
//    /**
//     * RedisTemplate 설정
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//
//        // 직렬화 설정
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//        return template;
//    }
//
//    /**
//     * 캐시 매니저 설정
//     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofHours(1)) // 기본 TTL: 1시간
//                .serializeKeysWith(org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//
//        return RedisCacheManager.builder(connectionFactory)
//                .cacheDefaults(config)
//                .transactionAware()
//                // 특정 캐시별 TTL 설정
//                .withCacheConfiguration("playerRank", config.entryTtl(Duration.ofMinutes(30)))
//                .withCacheConfiguration("leaderboard", config.entryTtl(Duration.ofMinutes(15)))
//                .withCacheConfiguration("userMatches", config.entryTtl(Duration.ofMinutes(10)))
//                .withCacheConfiguration("matchDetail", config.entryTtl(Duration.ofHours(2)))
//                .withCacheConfiguration("playerDetail", config.entryTtl(Duration.ofMinutes(20)))
//                .build();
//    }
//}
