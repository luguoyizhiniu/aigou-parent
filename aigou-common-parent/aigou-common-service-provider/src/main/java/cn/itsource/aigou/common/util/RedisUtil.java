package cn.itsource.aigou.common.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    static JedisPool jedisPool = null;

    static {
        GenericObjectPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);//最大连接数
        config.setMaxIdle(10);//最大空闲连接数
        config.setMaxWaitMillis(3*1000);//连接超时时间
        config.setTestOnBorrow(true);//从连接池获取连接时测试

        //创建连接池，设置配置信息，ip，端口，超时时间，密码
        String host = "127.0.0.1";
        int port = 6379;
        int timeout = 5 * 1000;
        String password = "123456";
        jedisPool = new JedisPool(config,host,port,timeout, password);
    }

    public static void set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public static String get(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
    }
}
