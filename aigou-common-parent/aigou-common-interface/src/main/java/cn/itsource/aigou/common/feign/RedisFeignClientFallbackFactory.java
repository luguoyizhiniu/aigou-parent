package cn.itsource.aigou.common.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisFeignClientFallbackFactory implements FallbackFactory<RedisFeignClient> {
    @Override
    public RedisFeignClient create(Throwable throwable) {
        return new RedisFeignClient() {
            @Override
            public void set(String key, String value) {

            }

            @Override
            public String get(String key) {
                return null;
            }
        };
    }
}
