package com.greencoffee.admin.producer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greencoffee.common.entity.Producer;


@Repository
public interface ProducerRepository extends CrudRepository<Producer, Integer> {
	
	@Query("SELECT u FROM Producer u WHERE u.email = :email")
	public Producer getProducerByEmail(@Param("email") String email);
    
	public Long countById(Integer id);
}

