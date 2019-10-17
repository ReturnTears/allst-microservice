package com.yiya.allst.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 部门实体类
 * Dept(enity) orm mysql -> Dept (table) 类表关系映射
 *
 * lombok:
 *
 * @AllArgsConstructor 全参构造函数
 * @NoArgsConstructor 空参构造函数
 * @Data    每个字段生成get/set
 * @Accessors(chain = true) 链式构建风格
 * @SuppressWarnings("serial") ⚠警告压制
 *
 * @author YiYa
 * @since 2019-10-18 上午 12:37
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable { // 必须序列化
    /**
     * 主键
     */
    private Long deptno;
    /**
     * 部门名称
     */
    private Long dname;
    /**
     * 来自那个数据库
     * 微服务架构可以一个服务对应一个数据库, 同一个信息被存储到不同数据库
     */
    private Long db_source;


}
