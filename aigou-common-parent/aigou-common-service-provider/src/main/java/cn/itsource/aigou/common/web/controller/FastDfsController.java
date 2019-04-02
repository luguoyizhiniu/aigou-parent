package cn.itsource.aigou.common.web.controller;

import cn.itsource.aigou.common.util.FastDfsApiOpr;
import cn.itsource.aigou.util.AjaxResult;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/common")
public class FastDfsController {

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/fastdsf",method = RequestMethod.POST)
    public AjaxResult upload(@RequestBody MultipartFile file){
        //获取文件的原始名字
        String originalFilename = file.getOriginalFilename();
        //获取文件后缀名
        String extName = FilenameUtils.getExtension(originalFilename);
        try {
            //获取传递文件的字节流
            byte[] bytes = file.getBytes();
            String filePath = FastDfsApiOpr.upload(bytes, extName);
            return AjaxResult.me().setMessage("上传成功").setObject(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败");
        }
    }

    @RequestMapping(value = "/fastdsf",method = RequestMethod.GET)
    public byte[] download(@RequestParam String filePath){
        //去除字符串第一个斜杠
        String substring = filePath.substring(1);
        //获取组名
        String groupName = substring.substring(0, substring.indexOf("/"));
        //获取文件名
        String fileName = substring.substring(substring.indexOf("/") + 1);
        return FastDfsApiOpr.download(groupName, fileName);
    }

    @RequestMapping(value = "/fastdsf",method = RequestMethod.DELETE)
    public AjaxResult delete(@RequestParam String filePath){
        //去除字符串第一个斜杠
        String substring = filePath.substring(1);
        //获取组名
        String groupName = substring.substring(0, substring.indexOf("/"));
        //获取文件名
        String fileName = substring.substring(substring.indexOf("/") + 1);
         int status = FastDfsApiOpr.delete(groupName, fileName);
         if (status == 0){
             return AjaxResult.me().setMessage("删除成功");
         }else {
             return AjaxResult.me().setSuccess(false).setMessage("删除失败");
         }
    }
}
