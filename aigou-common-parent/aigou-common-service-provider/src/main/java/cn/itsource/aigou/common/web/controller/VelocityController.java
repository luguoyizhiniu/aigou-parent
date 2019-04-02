package cn.itsource.aigou.common.web.controller;

import cn.itsource.aigou.base.Constant;
import cn.itsource.aigou.common.feign.VelocityFeignClient;
import cn.itsource.aigou.common.util.VelocityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/common")
public class VelocityController implements VelocityFeignClient {

    @Override
    @RequestMapping(value = "/staticPage",method = RequestMethod.POST)
    public void createStaticPage(@RequestBody Map<String, Object> params) {
//        model, templateFilePathAndName, targetFilePathAndName
        Object model = params.get(Constant.PAGE_MODEL);
        String template_file = (String)params.get(Constant.TEMPLATE_FILE_PATH_AND_NAME);
        String target_file = (String)params.get(Constant.TARGET_FILE_PATH_AND_NAME);
        VelocityUtils.staticByTemplate(model,template_file,target_file);
    }
}
