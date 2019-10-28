package com.yiya.allst.controller;

import com.yiya.allst.entities.Dept;
import com.yiya.allst.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YiYa
 * @since 2019-10-20 下午 05:18
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService service;

    // 服务发现
    //@Qualifier("client")
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/add", method= RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return service.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method= RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = "/dept/list", method= RequestMethod.GET)
    public List<Dept> list() {
        return service.list();
    }

    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = client.getServices();
        System.out.println(">>" + list);

        List<ServiceInstance> instances = client.getInstances("ALLST-MICROSERVICE-DEPT");
        for (ServiceInstance ele : instances) {
            System.out.println(ele.getServiceId() + "\t" + ele.getHost() + "\t" + ele.getPort());
        }
        return this.client;
    }

}
