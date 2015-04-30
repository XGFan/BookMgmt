package com.api;

import com.bean.book.Book;
import com.service.BookService;
import com.service.CourseService;
import com.service.SupplierService;
import com.util.GetPaginationInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import java.text.SimpleDateFormat;
import java.util.*;

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
    SupplierService supplierService;
    
    @GET
    @Path("/id={idbk}")
    @Produces("application/json;charset=UTF-8")
    public List getBookByIdbk(@PathParam("idbk") String idbk) {
        List ans = new ArrayList();
        ans.add(bookService.findById(idbk));
        return ans;
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
    public Map getBook(@PathParam("keyword") String keyword,@QueryParam("page")int page,@QueryParam("rows") int row) {
        return GetPaginationInfo.getSubMap(bookService.searchByBkname(keyword), page, row);
    }


    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public Map getAllBook(@QueryParam("page")int page,@QueryParam("rows") int row) {
        return GetPaginationInfo.getSubMap(bookService.getAll(), page, row);
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
        return bookService.update(book);
    }

    @POST
    @Path("/new")
    @Produces("application/json;charset=UTF-8")
    public String addBook(
            @FormParam("bkname") String bkname,
            @FormParam("author") String author,
            @FormParam("edition") Integer edition,
            @FormParam("isbn") String isbn,
            @FormParam("price") Double price,
            @FormParam("memo") String memo,
            @FormParam("publisher") String idsp
    ) {
        Calendar cale = Calendar.getInstance();
        Date tasktime=cale.getTime();
        SimpleDateFormat df=new SimpleDateFormat("yyMMdd");
        String result = String.format("%05d",(int)(Math.random()*10000));
        Book book = new Book();
        book.setIdbk(df.format(tasktime) + result);
        book.setBkname(bkname);
        book.setAuthor(author);
        book.setEdition(edition);
        book.setSupplier(supplierService.findById(idsp));
        book.setIsbn(isbn);
        book.setPrice(price);
        book.setMemo(memo);
        if(bookService.save(book)){
            return book.getIdbk();
        }else{
            return null;
        }
    }
}
