package cn.itsource.aigou.zuul.swagger;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
       //aigou：网关前缀，user：网关路径，/v2/api-docs：swagger的路径(不需要改动)，以后增加了接口就在这配置就ok
        resources.add(swaggerResource("用户系统", "/aigou/user/v2/api-docs", "2.0"));
        resources.add(swaggerResource("商品系统", "/aigou/product/v2/api-docs", "2.0"));
        resources.add(swaggerResource("基础系统", "/aigou/common/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}