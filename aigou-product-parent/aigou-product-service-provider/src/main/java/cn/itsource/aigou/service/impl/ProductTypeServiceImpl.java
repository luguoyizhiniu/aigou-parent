package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.base.Constant;
import cn.itsource.aigou.common.feign.RedisFeignClient;
import cn.itsource.aigou.common.feign.VelocityFeignClient;
import cn.itsource.aigou.domain.ProductType;
import cn.itsource.aigou.mapper.ProductTypeMapper;
import cn.itsource.aigou.service.IProductTypeService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author ztg
 * @since 2019-03-31
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private RedisFeignClient redisFeignClient;

    @Autowired
    private VelocityFeignClient velocityFeignClient;

    @Override
    public List<ProductType> treeData() {
        //递归
        //return treeDataRecursion(0L);
        //循环
        //return treeDataLoop();

        //判断redis中有没有缓存
        if (StringUtils.isEmpty(redisFeignClient.get(Constant.PRODUCT_TYPE))){
            //没有就添加
            redisFeignClient.set(Constant.PRODUCT_TYPE,JSON.toJSONString(treeDataLoop()));
            System.out.println("开始缓存");
        }
        List<ProductType> parse = (List<ProductType>)JSON.parse(redisFeignClient.get(Constant.PRODUCT_TYPE));
        System.out.println("已有缓存");
        return parse;
    }

    //循环
    private List<ProductType> treeDataLoop() {
        //准备一个装结果的集合
        List<ProductType> result = new ArrayList<>();
        //先把所有数据查出来
        List<ProductType> productTypes = productTypeMapper.selectList(null);
        //做一个map映射，方便装数据
        Map<Long,ProductType> map = new HashMap<>();
        for (ProductType productType : productTypes) {
            map.put(productType.getId(),productType);
        }
        //装入数据
        for (ProductType productType : productTypes) {
            if (productType.getPid()==0){
                //装入一级菜单
                result.add(productType);
            }else {
                //如果不是一级菜单，则找到其父菜单并装入
                ProductType parentType = map.get(productType.getPid());
                parentType.getChildren().add(productType);
            }
        }
        return result;
    }

    //递归
    private List<ProductType> treeDataRecursion(long pid) {
        //根据传入的pid查找该级菜单
        List<ProductType> productTypes = productTypeMapper.selectByPid(pid);
        if (productTypes==null || productTypes.size()==0){
            return null;
        }
        for (ProductType productType : productTypes) {
            //把该级菜单的id作为再次查找的pid传入，继续往下查
            List<ProductType> childTypes = treeDataRecursion(productType.getId());
            //将子菜单添加到父菜单中
            productType.setChildren(childTypes);
        }
        return productTypes;
    }

    @Override
    public boolean updateById(ProductType entity) {
        boolean b = super.updateById(entity);
        Map<String,Object> productTypeMap = new HashMap<>();
//        model, templateFilePathAndName, targetFilePathAndName
        productTypeMap.put(Constant.PAGE_MODEL,treeDataLoop());
        productTypeMap.put(Constant.TEMPLATE_FILE_PATH_AND_NAME,"D:\\software\\IntelliJ IDEA\\IdeaProjects\\aigou-parent\\aigou-common-parent\\aigou-common-service-provider\\src\\main\\resources\\template\\product.type.vm");
        productTypeMap.put(Constant.TARGET_FILE_PATH_AND_NAME,"D:\\software\\IntelliJ IDEA\\IdeaProjects\\aigou-parent\\aigou-common-parent\\aigou-common-service-provider\\src\\main\\resources\\template\\product.type.vm.html");
        velocityFeignClient.createStaticPage(productTypeMap);
        return b;
    }
}
