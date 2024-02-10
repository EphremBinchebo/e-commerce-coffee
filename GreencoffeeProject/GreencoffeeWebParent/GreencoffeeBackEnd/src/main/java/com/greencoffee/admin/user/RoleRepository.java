package com.greencoffee.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greencoffee.common.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
