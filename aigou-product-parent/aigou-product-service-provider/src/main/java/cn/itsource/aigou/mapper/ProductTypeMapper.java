package cn.itsource.aigou.mapper;

import cn.itsource.aigou.domain.ProductType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品目录 Mapper 接口
 * </p>
 *
 * @author ztg
 * @since 2019-03-31
 */
public interface ProductTypeMapper extends BaseMapper<ProductType> {

    List<ProductType> selectByPid(Long pid);
}
