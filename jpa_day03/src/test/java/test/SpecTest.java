package test;

import com.itheima.jpa.dao.CustomerDao;
import com.itheima.jpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;


    @Test
    public void testSpec1(){

        // 构建查询条件
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");


                Predicate predicate = cb.equal(custName, "黑马程序员");
                Predicate predicate1 = cb.equal(custIndustry, "教育");

                return  cb.and(predicate,predicate1);
            }

        };

        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);


    }

    @Test
    public void testSpec2(){

        // 构建查询条件
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");


                Predicate predicate = cb.like(custName.as(String.class), "传智%");

                return  cb.and(predicate);
            }

        };

        List<Customer> customers = customerDao.findAll(specification);
        for (Customer customer : customers) {
            System.out.println(customer);
        }

    }

    @Test
    public void testSpec3(){

        // 构建查询条件
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "传智%");

                return  cb.and(predicate);
            }

        };

        // 构建分页对象  page是从0开始
        Pageable pageable = new PageRequest(0, 1);


        Page<Customer> page = customerDao.findAll(specification,pageable);

        System.out.println("总记录数："+page.getTotalElements());
        System.out.println("总页数："+page.getTotalPages());
        System.out.println("结果集："+page.getContent());

    }

}
