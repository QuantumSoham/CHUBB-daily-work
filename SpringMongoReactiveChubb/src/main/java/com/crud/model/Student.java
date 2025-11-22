package com.crud.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Document(collection="students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student 
{
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String course;
	private Integer year;
	private Boolean active;
	
}
