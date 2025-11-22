package com.crud.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.crud.model.Student;

import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String>
{
	Flux<Student> findByCourse(String course);
	Flux<Student> findByFirstNameContainingIgnoreCase(String name);
}