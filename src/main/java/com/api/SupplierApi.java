package com.api;

import com.bean.supplier.Supplier;
import com.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * DATE:2015/1/6
 * TIME:11:00
 * Created by Yuanyuan on 2015/1/6
 */
@Path("/sup")
public class SupplierApi {
    @Context
    ServletContext context;
    @Autowired
    SupplierService supplierService;


    //返回所有供应商string数组===============正常
    @GET
    @Path("/supname")
    @Produces("application/json;charset=UTF-8")
    public List getSup() {
        return supplierService.getAllSupplier();
    }

    //返回所有出版社string数组===============正常
    @GET
    @Path("/pubname")
    @Produces("application/json;charset=UTF-8")
    public List getPub() {
        return supplierService.getAllPublisher();
    }

    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public List getAll() {
        return supplierService.getAll();
    }


    //根据供应商名查找供应信息obj数组===========正常?!
    @GET
    @Path("/sup={supname}")
    @Produces("application/json;charset=UTF-8")
    public List searchBySup(@PathParam("supname") String supplier) {
        return supplierService.findBySupplier(supplier);
    }

    //根据出版社名查找供应信息obj数组===========正常
    @GET
    @Path("/pub={pubname}")
    @Produces("application/json;charset=UTF-8")
    public List findByPub(@PathParam("pubname") String publish) {
        return supplierService.findByPublish(publish);
    }


    //根据idsp查找供应信息obj======================正常
    @GET
    @Path("/id={idsp}")
    @Produces("application/json;charset=UTF-8")
    public Supplier findById(@PathParam("idsp") String idsp) {
        return (Supplier)supplierService.findById(idsp);
    }

    //删除供应信息===================200 OK TRUE
    @DELETE
    @Path("/id={idsp}")
    @Produces("application/json;charset=UTF-8")
    public boolean deletePub(@PathParam("idsp") String idsp) {
        return supplierService.delete(supplierService.findById(idsp));
    }

    //添加供应信息=====================正常
    @POST
    @Path("/new")
    @Produces("application/json;charset=UTF-8")
    public boolean addSupplier(
            @FormParam("publisher") String publisher,
            @FormParam("supplier") String supplier) {
        Supplier temp = new Supplier();
        temp.setPublisher(publisher);
        temp.setSupplier(supplier);
        return supplierService.save(temp);
    }

    //    修改供应信息
    @PUT
    @Path("/{idsp}")
    @Produces("application/json;charset=UTF-8")
    public boolean editSup(@PathParam("idsp") String idsp,
                           @FormParam("publisher") String publisher,
                           @FormParam("supplier") String supplier) {
        Supplier temp = supplierService.findById(idsp);
        temp.setPublisher(publisher);
        temp.setSupplier(supplier);
        return supplierService.update(temp);
    }
}
