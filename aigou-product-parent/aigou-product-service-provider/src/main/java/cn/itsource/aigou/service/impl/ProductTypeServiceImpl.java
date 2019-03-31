package cn.itsource.aigou.service.impl;

import cn.itsource.aigou.domain.ProductType;
import cn.itsource.aigou.mapper.ProductTypeMapper;
import cn.itsource.aigou.service.IProductTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
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

    @Override
    public List<ProductType> treeData() {
        //递归
        //return treeDataRecursion(0L);
        //循环
        return treeDataLoop();
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
}
