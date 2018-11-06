package com.itheima.jpa;

import com.itheima.jpa.domain.Customer;
import com.itheima.jpa.domain.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * JPA JPQL 查询
 */
public class JPAQuery {


    @Test
    public  void  testFindAll(){
        EntityManager entityManager = JpaUtil.getEntityManager();

        String jpql = "from Customer";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        List<Customer> list = query.getResultList();
        // iter: 增强for   itli: 遍历list集合
        for (int i = 0; i < list.size(); i++) {
            Customer customer =  list.get(i);
            System.out.println(customer);
        }

        entityManager.close();
    }


    @Test
    public void testFindPage(){

        EntityManager entityManager = JpaUtil.getEntityManager();
        String jpql = "from Customer";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        query.setFirstResult(0);
        query.setMaxResults(2);

        List<Customer> resultList = query.getResultList();
        for (Customer customer : resultList) {
            System.out.println(customer);
        }

        entityManager.close();
    }


    @Test
    public void testFindPageByCondition(){

        EntityManager entityManager = JpaUtil.getEntityManager();

        String jpql1 = "from Customer where custName like ?";
        String jpql2 = "from Customer where custName like ? order by custId desc";


        TypedQuery<Customer> query = entityManager.createQuery(jpql2, Customer.class);
        query.setFirstResult(0);
        query.setMaxResults(2);

        query.setParameter(1, "传智%");

        List<Customer> resultList = query.getResultList();
        for (Customer customer : resultList) {
            System.out.println(customer);
        }

//        Customer customer = query.getSingleResult();
//        System.out.println(customer);

        entityManager.close();
    }


    @Test
    public void testCount(){

        EntityManager entityManager = JpaUtil.getEntityManager();
        String jpql = "select count(custId) from Customer";
        Query query = entityManager.createQuery(jpql);

        Object result = query.getSingleResult();
        System.out.println(result);

//        List list = query.getResultList();
//        System.out.println(list);

        entityManager.close();
    }





}

