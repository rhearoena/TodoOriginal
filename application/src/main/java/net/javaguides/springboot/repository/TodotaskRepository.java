package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.entity.Todotask;



public interface TodotaskRepository extends JpaRepository<Todotask, Long> {
	  List<Todotask> findByTitleContaining(String title);

}