package com.yiya.allst.service.impl;

import com.yiya.allst.dao.DeptDao;
import com.yiya.allst.entities.Dept;
import com.yiya.allst.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YiYa
 * @since 2019-10-20 下午 05:15
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao dao;

    @Override
    public boolean add(Dept dept) {
        return dao.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Dept> list() {
        return dao.findAll();
    }
}
