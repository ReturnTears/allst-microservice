package com.yiya.allst.controller;

import com.yiya.allst.entities.Dept;
import com.yiya.allst.service.DeptCLientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YiYa
 * @since 2019-10-20 下午 08:56
 */
@RestController
public class DeptConsumerController {

    @Autowired
    private DeptCLientService service;

    @RequestMapping(value = "/consumer/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id) {
        return this.service.get(id);
    }

    @RequestMapping(value = "/consumer/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return this.service.list();
    }

    @RequestMapping(value = "/consumer/dept/add", method = RequestMethod.POST)
    public Object add(Dept dept) {
        return this.service.add(dept);
    }
}
