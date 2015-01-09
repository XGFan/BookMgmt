package com.api;

import com.bean.supplier.Supplier;
import com.service.SupplierService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

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
    public JSONArray getSup() {
        return JSONArray.fromObject(supplierService.getAllSupplier());
    }

    //返回所有出版社string数组===============正常
    @GET
    @Path("/pubname")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getPub() {
        return JSONArray.fromObject(supplierService.findAllPub());
    }

    //所有供应信息obj数组===============正常
    @GET
    @Path("/all")
    @Produces("application/json;charset=UTF-8")
    public JSONArray getAllSup() {
        return JSONArray.fromObject(supplierService.getAll());
    }

    //根据供应商名查找供应信息obj数组===========正常
    @GET
    @Path("/sup={supname}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray searchBySup(@PathParam("supname") String supplier) {
        return JSONArray.fromObject((Object) supplierService.findByPublish(supplier));
    }

    //根据出版社名查找供应信息obj数组===========正常
    @GET
    @Path("/pub={pubname}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray findByPub(@PathParam("pubname") String publish) {
        return JSONArray.fromObject(supplierService.findByPublish(publish));
    }


    //根据idsp查找供应信息obj======================正常
    @GET
    @Path("/id={idsp}")
    @Produces("application/json;charset=UTF-8")
    public JSONArray findById(@PathParam("idsp") String idsp) {
        Object temp = supplierService.findByIdsp(idsp).toArray();
        return JSONArray.fromObject(temp);
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
            @FormParam("idsp") String idsp,
            @FormParam("publisher") String publisher,
            @FormParam("supplier") String supplier) {
        Supplier temp = new Supplier();
        temp.setIdsp(idsp);
        temp.setPublisher(publisher);
        temp.setSupplier(supplier);
        return supplierService.addSup(supplier);
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
