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

    @GET
    @Path("/id={idbk}")
    @Produces("application/json;charset=UTF-8")
    public JSONObject getBookByIdbk(@PathParam("idbk") String idbk) {
        JsonConfig config = new JsonConfig();
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        config.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
        String[] excludes = {"bkpurchases","coursebks","books"};
        config.setExcludes(excludes);
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
        return JSONArray.fromObject(bookService.searchByISBN(isbn));
    }

    @GET
    @Path("/key={keyword}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getBook(@PathParam("keyword") String keyword) {
        return JSONArray.fromObject(bookService.searchByBkname(keyword));
    }


    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllBook() {
        return JSONArray.fromObject(bookService.getAll());
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
        Book book = new Book();
        book.setBkname(bkname);
        book.setAuthor(author);
        book.setEdition(edition);
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setMemo(memo);
        return bookService.save(book);
    }


}
