package com.yiya.allst.dao;

import com.yiya.allst.entities.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SpringBoot整合MyBatis时Dao层的注解
 * @author YiYa
 * @since 2019-10-20 下午 05:04
 */
@Mapper
public interface DeptDao {

    public boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();

}
