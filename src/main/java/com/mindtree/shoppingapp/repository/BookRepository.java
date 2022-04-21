package com.mindtree.shoppingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.shoppingapp.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
