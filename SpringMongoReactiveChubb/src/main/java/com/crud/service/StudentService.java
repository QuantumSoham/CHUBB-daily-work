package com.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.model.Student;
import com.crud.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService 
{
	@Autowired
	private StudentRepository studentRepo;
	
	public Flux<Student> findAll()
	{
		return studentRepo.findAll();
	}
	
	public Flux<Student> findByCourse(String course)
	{
		return studentRepo.findByCourse(course);
	}
	
	public Flux<Student> findByName(String name)
	{
		return studentRepo.findByFirstNameContainingIgnoreCase(name);
	}
	
	public Mono<Student> findById(String id) {
	    return studentRepo.findById(id);
	  }

	  public Mono<Student> create(Student student) {
	    // ensure id null for insert, or use save()
	    return studentRepo.save(student);
	  }

	  public Mono<Student> update(String id, Student student) {
	    return studentRepo.findById(id)
	      .flatMap(existing -> {
	         student.setId(id);
	         return studentRepo.save(student);
	      });
	  }

	  public Mono<Void> deleteById(String id) {
	    return studentRepo.deleteById(id);
	  }

	  public Mono<Void> deleteAll() {
	    return studentRepo.deleteAll();
	  }
	
}
