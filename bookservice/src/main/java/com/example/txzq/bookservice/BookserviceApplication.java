package com.example.txzq.bookservice;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@SpringBootApplication
public class BookserviceApplication {
	
	@Bean
	CommandLineRunner runner(ReservationRepo rr)
	{
		return args -> 
		{
			Arrays.asList("Dr will, Josh, Thomas, Windy, Jeff".split(","))
			.forEach( x -> rr.save(new Book(x.toString())));
			rr.findAll().forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BookserviceApplication.class, args);
	}
}

@RepositoryRestResource
interface ReservationRepo extends JpaRepository<Book, Long> 
{
	@RestResource(path = "by-name")
	Collection<Book> findByName(@Param("rn") String rn);
}

@Entity
class Book
{
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Book(String name)
	{
		this.name = name;
	}
	
	public Book()
	{
		
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + "]";
	}
	
	
}
