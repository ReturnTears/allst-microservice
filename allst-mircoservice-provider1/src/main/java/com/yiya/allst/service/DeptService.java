package com.yiya.allst.service;

import com.yiya.allst.entities.Dept;

import java.util.List;

/**
 * @author YiYa
 * @since 2019-10-20 下午 05:14
 */
public interface DeptService {

    public boolean add(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();

}
