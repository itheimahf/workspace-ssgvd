package com.itheima.jpa.dao;

import com.itheima.jpa.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long>{


}
