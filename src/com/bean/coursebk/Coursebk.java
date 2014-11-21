package com.bean.coursebk;

import com.bean.book.Book;
import com.bean.course.Course;


/**
 * 将科目course与教材book联系起来
 * 可以称之为课程
 */
public class Coursebk implements java.io.Serializable {

	private Integer id
    /*教程*/;
	private Book book;
    /*科目*/
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