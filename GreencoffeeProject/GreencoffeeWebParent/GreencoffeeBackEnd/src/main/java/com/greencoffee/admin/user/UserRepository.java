package com.greencoffee.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greencoffee.common.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
