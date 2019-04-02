package cn.itsource.aigou.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RequestMapping("/common")
@FeignClient(value = "COMMON-PROVIDER",fallbackFactory = VelocityFeignClientFallbackFactory.class)
public interface VelocityFeignClient {

    @RequestMapping(value = "/staticPage",method = RequestMethod.POST)
    void createStaticPage(@RequestBody Map<String, Object> params);
}