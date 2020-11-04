package com.stacksimplify.restservices.springbootbuildingblocks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.springbootbuildingblocks.entities.User;

//Repository
@Repository
public interface UserRepository extends JpaRepository<User,Long>  {

	User findByUsername(String username);
	
}
