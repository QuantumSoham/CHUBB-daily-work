package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.crud.model.Student;
import com.crud.service.StudentService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping("/students")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Student> getAllStudents(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String course) {
    if (name != null) return studentService.findByName(name);
    if (course != null) return studentService.findByCourse(course);
    return studentService.findAll();
  }

  @GetMapping("/students/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Student> getStudentById(@PathVariable("id") String id) {
    return studentService.findById(id);
  }

  @PostMapping("/students")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Student> createStudent(@RequestBody Student student) {
    // you could validate fields here
    return studentService.create(student);
  }

  @PutMapping("/students/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Student> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
    return studentService.update(id, student);
  }

  @DeleteMapping("/students/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteStudent(@PathVariable("id") String id) {
    return studentService.deleteById(id);
  }

  @DeleteMapping("/students")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteAllStudents() {
    return studentService.deleteAll();
  }
}
