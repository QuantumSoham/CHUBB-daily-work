package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Student;
import com.crud.repository.StudentRepo;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

	@Autowired
	StudentRepo studentRepo;
	@PostMapping("/addStudent")
	public void addStudent(@RequestBody Student student)
	{
		studentRepo.save(student);
	}
	
	@GetMapping("/fetchStudents")
	public List<Student> fetchStudents()
	{
		return studentRepo.findAll();
	}
	
	@GetMapping("/fetch/{rno}")
	public Student fetch(@PathVariable("rno") int rno) {
	    return studentRepo.findById(rno)
	            .orElse(null);
	}
	
	@PutMapping("/updateStudent/{rno}")
	public Student updateStudent(@PathVariable Integer rno, @RequestBody Student student)
	{
		Optional<Student> existing=studentRepo.findById(rno);
		if(existing.isPresent())
		{
			Student std=existing.get();
			std.setName(student.getName());
			std.setAddress(student.getAddress());
			return studentRepo.save(std);
			
		}
		else
		{
			return null;
		}
	}
	
	@DeleteMapping("/delete/{rno}")
	public String deleteStudent(@PathVariable int rno)
	{
		studentRepo.deleteById(rno);
		return "Student with roll "+rno+" deleted.";
		
	}
	
	@DeleteMapping("/deleteAll")
	public String deleteAll()
	{
		studentRepo.deleteAll();
		return "All students deleted";
	}
	


}
