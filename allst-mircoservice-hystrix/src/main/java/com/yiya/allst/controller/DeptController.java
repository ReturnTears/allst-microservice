package com.yiya.allst.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yiya.allst.entities.Dept;
import com.yiya.allst.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Hystrix控制器
 * @author YiYa
 * @since 2019-10-20 下午 05:18
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService service;

    /**
     * 通过id查询记录
     * 注解HystrixCommand: 一旦调用服务方法失败并抛出了错误信息后, 会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
     * @param id    参数
     * @return      结果
     */
    @RequestMapping(value = "/dept/get/{id}", method= RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = this.service.get(id);
        if (null == dept) {
            throw new RuntimeException("该ID: " + id + " 没有对应的信息!");
        }
        return dept;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id) {
        System.out.println("该ID: " + id + " 没有对应的信息! null => @HystrixCommand");
        return new Dept();
                /*.setDeptno(id)
                .setDname("该ID: " + id + " 没有对应的信息! null => @HystrixCommand")
                .setDb_source("no this database in mysql");*/
    }

    @RequestMapping(value = "/dept/list", method= RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public List<Dept> list() {
        return service.list();
    }
}
