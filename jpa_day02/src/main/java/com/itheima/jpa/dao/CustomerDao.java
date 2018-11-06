package com.itheima.jpa.dao;

import com.itheima.jpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
    /**
     * JpaRepository<Customer,Long>： 封装了对数据库表简单的 CRUD 操作
     * JpaSpecificationExecutor：封装了对数据的复杂查询（条件查询集合和当个对象，条件分页查询结果结合）
     */

    // 自定义接口
    @Query("from Customer")
    public List<Customer> findAllCustomer();

    //@Query 使用jpql的方式查询。?1代表参数的占位符，其中1对应方法中的参数索引
    @Query("from Customer where custName = ?1")
    public List<Customer> findCustomer(String custName);



    @Query("update Customer set custName = ?1 where custId = ?2")
    @Modifying
    public void updateCustomer(String custName,Long custId);

    /**
     * nativeQuery : 使用本地sql的方式查询
     */
    @Query(value="select * from cst_customer",nativeQuery=true)
    public List<Object []> findSql();


    /**
     * 方法规范查询
     *  findBy + "pojo实体属性名称" + "查询方式（like|isNull....）"
     */
    public List<Customer> findByCustName(String name);

    /**
     * 方法规范查询
     *  findBy + "pojo实体属性名称" + "查询方式（like|isNull....）"
     */
    public List<Customer> findByCustNameLike(String name);


    /**
     * 方法规范查询 多条件查询
     *  findBy + "pojo实体属性名称" + "查询方式（like|isNull....）" + "条件（and|or）" +
     *      "pojo实体属性名称" + "查询方式（like|isNull....）"
     */
    public List<Customer> findByCustNameLikeAndCustIndustry(String name,String industry);


}
