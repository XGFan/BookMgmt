package com.api;

import com.bean.book.Book;
import com.service.BookService;
import com.service.CourseBookViewService;
import com.service.CourseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DATE:2015/1/6
 * TIME:11:00
 * Created by Yuanyuan on 2015/1/6
 */
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

    JsonConfig config;

    public BookApi() {
        this.config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        this.config.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        this.config.setExcludes(new String[]{"bkpurchases","coursebks","books"});
    }

    @GET
    @Path("/id={idbk}")
    @Produces("application/json;charset=UTF-8")
    public JSONObject getBookByIdbk(@PathParam("idbk") String idbk) {
        return JSONObject.fromObject(bookService.findById(idbk),config);
    }


    @GET
    @Path("/{bkname}/{author}/{idsp}/{isbn}/{memo}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getBook(@PathParam("bkname") String bkname,
                             @PathParam("author") String author,
                             @PathParam("idsp") String idsp,
                             @PathParam("isbn") String isbn,
                             @PathParam("memo") String memo) {
        return JSONArray.fromObject(bookService);
    }

    @GET
    @Path("/isbn={isbn}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getBookByIsbn(@PathParam("isbn") String isbn) {
        return JSONArray.fromObject(bookService.searchByISBN(isbn),config);
    }

    @GET
    @Path("/key={keyword}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getBook(@PathParam("keyword") String keyword) {
        return JSONArray.fromObject(bookService.searchByBkname(keyword),config);
    }


    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllBook() {
        return JSONArray.fromObject(bookService.getAll(),config);
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
        String result="";
        for(int i=0;i<5;i++){
            int intValue=(int)(Math.random()*10);
            result=result+Integer.toString(intValue);
        }
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
