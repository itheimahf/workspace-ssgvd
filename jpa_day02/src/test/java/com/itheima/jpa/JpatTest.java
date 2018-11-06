package com.itheima.jpa;

import com.itheima.jpa.dao.CustomerDao;
import com.itheima.jpa.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpatTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(3l);
        System.out.println(customer);
    }

    /**
     * 保存客户：调用save(obj)方法
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("传智播客");
        customerDao.save(customer);
    }

    /**
     * 修改客户：调用save(obj)方法
     *      对于save方法的解释：如果执行此方法是对象中存在id属性，即为更新操作会先根据id查询，再更新
     *                      如果执行此方法中对象中不存在id属性，即为保存操作
     *
     */
    @Test
    public void testUpdate() {
        //根据id查询id为1的客户
        Customer customer = customerDao.findOne(7l);
        //修改客户名称
        customer.setCustName("传智播客顺义校区");
        //更新
        customerDao.save(customer);
    }
    /**
     * 根据id删除：调用delete(id)方法
     * 先查询后删除
     */
    @Test
    public void testDelete() {
        customerDao.delete(7l);
    }


    @Test
    public void findAllCustomer(){
        List<Customer> list = customerDao.findAllCustomer();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Test
    public void findCustomer(){
        List<Customer> customer = customerDao.findCustomer("传智播客");
        System.out.println(customer);

    }

    /**
     * 在执行update|delete时 必须要加上事务的管理并且要设置回滚为false不自动提交
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testUpdateCustomer(){
        customerDao.updateCustomer("黑马程序员",4l);
    }

    @Test
    public void testfindSql(){
        List<Object[]> list = customerDao.findSql();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    //--------------------------
    @Test
    public void testfindByCustName(){
        List<Customer> list = customerDao.findByCustName("传智播客");
        for (Customer customer : list) {
            System.out.println(customer);
        }

    }

    @Test
    public void testfindByCustNameLike(){
        List<Customer> list = customerDao.findByCustNameLike("传智%");
        for (Customer customer : list) {
            System.out.println(customer);
        }

    }@Test
    public void testfindByCustNameLikeAndCustIndustry(){
        List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("传智%","教育");
        for (Customer customer : list) {
            System.out.println(customer);
        }

    }


}
