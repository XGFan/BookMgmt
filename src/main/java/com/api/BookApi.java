package com.api;

import com.bean.book.Book;
import com.bean.cls.ClassInfo;
import com.service.BookService;
import com.service.ClassService;
import com.service.CollegeService;
import com.service.CourseBookViewService;
import com.service.CourseService;
import com.service.SupplierService;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import static com.util.GetPaginationInfo.getSubList;

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
    @Autowired
    SupplierService supSer;

    //根据教材名和出版社名查找教材===================正常
//    @GET
//    @Path("/{bkname}/{pub}")
//  //  @Path("/{bkname}/{author}/{idsp}/{isbn}/{memo}")
//    @Produces("application/json;charset=UTF-8")
//    public JSONArray getBook(@PathParam("bkname") String bkname, @PathParam("pub") String pub) {
//        return JSONArray.fromObject(bookService.searchByBookPub(bkname, pub));
//    }
    
    @GET
   // @Path("/{bkname}/{pub}")
    @Path("/{bkname}/{author}/{idsp}/{isbn}/{memo}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getBook(@PathParam("bkname") String bkname, 
    						 @PathParam("author") String author,
    						 @PathParam("idsp") String idsp, 
    						 @PathParam("isbn") String isbn,
    						 @PathParam("memo") String memo) {
        return JSONArray.fromObject(bookService);
    }
    
    //根据isbn查找教材=====================404
    @GET
    @Path("/{isbn}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getBookByIsbn(@PathParam("isbn") String isbn) {
        return JSONArray.fromObject(bookService.searchByISBN(isbn));
    }
//
//    @GET
//    @Path("/p{page}/n{num}")
//    @Produces("application/json;charset=UTF-8")
//    public JSONArray getAllClass(@PathParam("page") int page, @PathParam("num") int num) {
//        return getSubList(getAllClass(), page, num);
//    }
//
    //根据教材名，进行模糊查找====================200 OK 返回【】
    @GET
    @Path("/key={keyword}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getBook(@PathParam("bkname") String bkname) {
        return JSONArray.fromObject(bookService.searchByBkname(bkname));
    }

   
    //返回所有教材=======================正常
    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllBook() {
        return JSONArray.fromObject(bookService.initBook());
    }
   
    //添加教材
 //   @POST
 //   @Path("book")
   // @Produces("application/json;charset=UTF-8")
  //  @Consumes("application/x-www-form-urlencoded;text/html;charset=utf-8")
//    @Produces("text/html")
//    @Consumes("application/json")
  //  public boolean addBook(
   // 		@FormParam("book") Book book
  //  		) {
 //   	return bookService.addBook(book);
 //   }
    
//    //添加教材
//    @POST
//    @Path("/new")
//    @Produces("application/json;charset=UTF-8")
//    public boolean addBook(@FormParam("book") Book book ) {
//        return bookService.addBook(book);
//    }
    
//
//
    //根据idbk进行删除===============正常
    @DELETE
    @Path("/id/{idbk}")
    @Produces("application/json;charset=UTF-8")
    public Boolean deleteBook(@PathParam("idbk") String idbk) {
        return bookService.deleteBook(idbk);
    }
//
//
//    @POST
//    @Path("/new")
////    @Consumes("application/x-www-form-urlencoded;text/html;charset=utf-8")
//    @Produces("application/json;charset=UTF-8")
//    public boolean addClass(
//            @FormParam("campus") String campus,
//            @FormParam("col") String col,
//            @FormParam("major") String major,
//            @FormParam("grade") String grade,
//            @FormParam("clsnum") int clsnum
//    ) {
//        return classServie.addClasses(campus, grade, clsnum, collegeService.getCollege(col, major));
//    }
//
    //修改
//    @PUT
//    @Path("/{idbk}")
//    @Produces("application/json;charset=UTF-8")
//    public boolean editBook(@PathParam("idbk") String idbk) {
//        Book temp = new Book();
//        return bookService.updateBook(temp);
//    }


}
