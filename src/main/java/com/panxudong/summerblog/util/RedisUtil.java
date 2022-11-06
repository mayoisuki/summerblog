package com.panxudong.summerblog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author kaza
 * @create 2022-10-28 20:40
 **/
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 实现命令：TTL key，以秒为单位，返回给定key的剩余生存时间（TTL，time to live）
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 实现命令：expire 设置过期时间，单位秒
     *
     * @param key
     * @param timeout
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：KEYS pattern，查找所有符合给定模式pattern的key
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 实现命令：DEL key，删除一个key
     *
     * @param key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 根据通配符批量删除
     *
     * @param key
     */
    public void delByKeys(String key) {
        Set<String> keys = redisTemplate.keys(key + "*");
        redisTemplate.delete(keys);
    }

//    字符串


    /**
     * 实现命令：SET key value，设置一个key-value（将字符串值value关联到key）
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 实现命令：SET key value EX seconds，设置一个key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param timeout 以秒为单位
     */
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：GET key，返回key所关联的字符串值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    //哈希表

    /**
     * 实现命令：HSET key field value，将哈希表key中的域field的值设为value
     *
     * @param key
     * @param field
     * @param value
     */
    public void hSet(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 实现命令：HGET key field，返回哈希表key中的给定域field的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hGet(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * @param key
     * @param value
     */
    public void sAdd(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * @param key
     * @return
     */
    public Set<String> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 实现命令：HDEL key field [field ...] 删除哈希表key中的一个或多个指定域，不存在的域将被忽略
     *
     * @param key
     * @param fields
     */
    public void hDel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 实现命令：HGETALL key，返回哈希表key中，所有域和值
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

//  列表

    /**
     * 实现命令：LPUSH key value ，将一个值value插入到列表key的表头
     *
     * @param key
     * @param value
     * @return 执行LPUSH命令后，列表的长度
     */
    public long lPush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取列表
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 实现命令：LPOP key，移除并返回列表key的头元素
     *
     * @param key
     * @return 列表key的头元素
     */
    public String lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 实现命令：RPUSH key value ，将一个值value插入到列表key的表尾（最右边）
     *
     * @param key
     * @param value
     * @return 执行RPUSH命令后，列表的长度
     */
    public long rPush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }


}
