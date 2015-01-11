package com.api;

import com.service.BKDistributeService;
import com.service.BookPurchaseService;
import com.util.GetPaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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
    @Path("/p{page}/n{num}")
    @Produces("application/json;charset=UTF-8")
    public List getAllByPage(@PathParam("page")int page,@PathParam("num")int num){
        return GetPaginationInfo.getSubList(bookPurchaseService.getPurInfoBySupplier(), page, num);
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
        String fileName="NewStudentBookList.xls";
        try {
            fileName = new String(file.getName().getBytes("gbk"),"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Response.ok(file).header("Content-Disposition", "attachment;filename=" +fileName).build();
    }

    @GET
    @Path("/doc/{idcls}")
    @Produces({"application/vnd.ms-word"})
    public Response getBKDistInfoDoc(@PathParam("idcls")String idcls){
        String dateStr = bookPurchaseService.getBKPurDate();
        String yearStr = dateStr.substring(0, dateStr.indexOf("-"));
        int year = Integer.parseInt(yearStr);
        String semStr = dateStr.substring(dateStr.length() - 1);
        int sem = Integer.parseInt(semStr);
        File file = bkDistributeService.BKDistInfoQuery2Doc(year, sem, idcls);
        String fileName="temp.doc";
        try {
            fileName = new String(file.getName().getBytes("gbk"),"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Response.ok(file).header("Content-Disposition", "attachment;filename=" +fileName).build();
    }

    @GET
    @Path("/print/{idcls}")
    @Produces("application/json;charset=UTF-8")
    public String getBKDistInfoPrint(@PathParam("idcls") String idcls, @Context HttpServletRequest request, @Context
    HttpServletResponse response){
        String dateStr = bookPurchaseService.getBKPurDate();
        String yearStr = dateStr.substring(0, dateStr.indexOf("-"));
        int year = Integer.parseInt(yearStr);
        String semStr = dateStr.substring(dateStr.length() - 1);
        int sem = Integer.parseInt(semStr);
        List bkpurviews = bkDistributeService.BKDistInfoQuery(year, sem, idcls);
        request.setAttribute("bkpurviews", bkpurviews);
        RequestDispatcher rd = request.getRequestDispatcher("../../../printPage.jsp");
        try {
            rd.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
