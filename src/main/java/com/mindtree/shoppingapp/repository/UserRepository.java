package com.mindtree.shoppingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
