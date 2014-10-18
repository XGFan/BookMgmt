package com.bean.coursebk;

import com.bean.book.Book;
import com.bean.course.Course;

/**
 * Coursebk entity. @author MyEclipse Persistence Tools
 */

public class Coursebk implements java.io.Serializable {

	// Fields

	private Integer id;
	private Book book;
	private Course course;

	// Constructors

	/** default constructor */
	public Coursebk() {
	}

	/** full constructor */
	public Coursebk(Book book, Course course) {
		this.book = book;
		this.course = course;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}