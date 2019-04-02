package cn.itsource.aigou.common.web.controller;

import cn.itsource.aigou.common.feign.RedisFeignClient;
import cn.itsource.aigou.common.util.RedisUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common")
public class RedisController implements RedisFeignClient {

    @Override
    @RequestMapping(value = "/redis",method = RequestMethod.POST)
    public void set(@RequestParam("key") String key,@RequestParam("value") String value){
        RedisUtil.set(key, value);
    }

    @Override
    @RequestMapping(value = "/redis/{key}",method = RequestMethod.GET)
    public String get(@PathVariable("key") String key){
        return RedisUtil.get(key);
    }
}
