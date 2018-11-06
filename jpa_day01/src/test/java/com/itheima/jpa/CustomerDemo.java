package com.itheima.jpa;

import com.itheima.jpa.domain.Customer;
import com.itheima.jpa.domain.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CustomerDemo {


    @Test
    public void testSave(){

        // 1、创建实体管理工厂
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJPA");
        // 2、创建实体管理类
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 3、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        // 4、执行
        Customer customer = new Customer();
        customer.setCustName("传智播客");
        customer.setCustIndustry("教育");
        entityManager.persist(customer);
        // 5、提交事务（回滚）
        transaction.commit();
        // 6、关闭资源
        entityManager.clear();
        entityManagerFactory.close();
    }

    @Test
    public void testMerge(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 先查询后修改
        Customer customer = entityManager.find(Customer.class, 1l);
        customer.setCustName("北京传智播客");
        customer.setCustIndustry("教育培训");
        customer.setCustAddress("北京修正");
        entityManager.merge(customer);

        transaction.commit();
        entityManager.clear();
    }

    @Test
    public void testRemove(){

        EntityManager entityManager = JpaUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 删除
        // 创建对象是无法删除的， 原因是当前对象处于游离态
//        Customer customer = new Customer();
//        customer.setCustId(1l);
        Customer customer = entityManager.find(Customer.class, 1l);
        entityManager.remove(customer);

        transaction.commit();
        entityManager.clear();

    }


    @Test
    public void testFind(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Customer customer = entityManager.find(Customer.class, 3l);
        System.out.println(customer);
        entityManager.clear();
    }

    @Test
    public void testReference(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Customer customer = entityManager.getReference(Customer.class, 3l);
        System.out.println(customer);
        entityManager.clear();
    }

}
