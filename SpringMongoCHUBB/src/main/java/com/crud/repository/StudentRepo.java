package com.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crud.model.Student;

public interface StudentRepo extends  MongoRepository<Student,Integer>
{
	
}
