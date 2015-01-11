package com.api;

import com.bean.bookpurchase.Bookpurchase;
import com.service.BKDistributeService;
import com.service.BookPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * DATE:2015/1/11
 * TIME:14:40
 * Created by guofan on 2015/1/11
 */
@RestController
@Path("/bkp")
public class BookPurchaseApi {
    @Autowired
    BookPurchaseService bookPurchaseService;
    @Autowired
    BKDistributeService bkDistributeService;
    @Context
    ServletContext context;

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public List getAllBookPurchase(){
        return bookPurchaseService.getPurInfoBySupplier();
    }

    @GET
    @Path("/pub={pub}")
    @Produces("application/json;charset=UTF-8")
    public List getAllBookPurchaseBuyPub(@PathParam("pub")String pubName ){
        return bookPurchaseService.getPurInfoBySupplier(pubName);
    }

    @GET
    @Path("/booklist/{year}/{sem}/all")
    @Produces({"application/vnd.ms-excel"})
    public Response getAllBooklist(@PathParam("year")String year,@PathParam("sem")String sem){
        File file = bookPurchaseService.generateStudentBookList(year, sem);
        System.out.println(file.getPath());
        String filename="AllStudentBookList.xls";
        try {
            filename = new String(file.getName().getBytes("gbk"),"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Response.ok(file).header("Content-Disposition", "attachment;filename=" +filename).build();
    }

    @GET
    @Path("/booklist/{year}/{sem}/newStudent")
    @Produces({"application/vnd.ms-excel"})
    public Response getNewStudentBooklist(@PathParam("year")String year,@PathParam("sem")String sem){
        File file = bookPurchaseService.generateNewStudentBookList(year,sem,year);
        System.out.println(file.getName());
        String filename="NewStudentBookList.xls";
        try {
            filename = new String(file.getName().getBytes("gbk"),"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Response.ok(file).header("Content-Disposition", "attachment;filename=" +filename).build();
    }

}
