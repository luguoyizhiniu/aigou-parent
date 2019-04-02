package cn.itsource.aigou.common.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VelocityFeignClientFallbackFactory implements FallbackFactory<VelocityFeignClient> {

    @Override
    public VelocityFeignClient create(Throwable throwable) {
        return new VelocityFeignClient() {
            @Override
            public void createStaticPage(Map<String, Object> params) {

            }
        };
    }
}
