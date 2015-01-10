package com.api;

import com.bean.book.Book;
import com.service.BookService;
import com.service.CourseBookViewService;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:11:00
 * Created by Yuanyuan on 2015/1/6
 */
@RestController
@Path("/book")
public class BookApi {
    @Context
    ServletContext context;
    @Autowired
    BookService bookService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseBookViewService corbkviewService;

    @GET
    @Path("/id={idbk}")
    @Produces("application/json;charset=UTF-8")
    public Book getBookByIdbk(@PathParam("idbk") String idbk) {
        return bookService.findById(idbk);
    }



    @GET
    @Path("/isbn={isbn}")
    @Produces("application/json;charset=UTF-8")
    public List getBookByIsbn(@PathParam("isbn") String isbn) {
        return bookService.searchByISBN(isbn);
    }

    @GET
    @Path("/key={keyword}")
    @Produces("application/json;charset=UTF-8")
    public List getBook(@PathParam("keyword") String keyword) {
        return bookService.searchByBkname(keyword);
    }


    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public List<Book> getAllBook() {
        return bookService.getAll();
    }

    @DELETE
    @Path("/id={idbk}")
    @Produces("application/json;charset=UTF-8")
    public Boolean deleteBook(@PathParam("idbk") String idbk) {
        return bookService.deleteBook(idbk);
    }

    @PUT
    @Path("/{idbk}")
    @Produces("application/json;charset=UTF-8")
    public boolean editBook(
            @PathParam("idbk") String idbk,
            @FormParam("bkname") String bkname,
            @FormParam("author") String author,
            @FormParam("edition") Integer edition,
            @FormParam("idsp") String idsp,
            @FormParam("isbn") String isbn,
            @FormParam("price") Double price,
            @FormParam("memo") String memo
    ) {
        Book book = bookService.findById(idbk);
        book.setBkname(bkname);
        book.setAuthor(author);
        book.setEdition(edition);
        book.setIdsp(idsp);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setMemo(memo);
        return bookService.save(book);
    }

    @POST
    @Path("/new")
    @Produces("application/json;charset=UTF-8")
    public boolean addClass(
            @FormParam("bkname") String bkname,
            @FormParam("author") String author,
            @FormParam("edition") Integer edition,
            @FormParam("isbn") String isbn,
            @FormParam("price") Double price,
            @FormParam("memo") String memo
    ) {
        Calendar cale = Calendar.getInstance();
        Date tasktime=cale.getTime();
        SimpleDateFormat df=new SimpleDateFormat("yyMMdd");
        String result = String.format("%05d",(int)(Math.random()*10000));
        Book book = new Book();
        book.setIdbk(df.format(tasktime)+result);
        book.setBkname(bkname);
        book.setAuthor(author);
        book.setEdition(edition);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setMemo(memo);
        return bookService.save(book);
    }
}
