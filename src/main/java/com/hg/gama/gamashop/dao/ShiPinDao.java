/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 25414
 * @create 2019/5/12
 * @since 1.0.0
 */
package com.hg.gama.gamashop.dao;

import com.hg.gama.gamashop.entity.Shipin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShiPinDao {

    //插入
    @Insert({"insert into shipins (name,lujing,url) values (#{name},#{lujing},#{url})"})
    public int insertUrl(@Param("name")String name,@Param("lujing")String lujing,@Param("url")String url);

    //查询
    @Select("select * from shipins")
    public List<Shipin> selectShipin();
}