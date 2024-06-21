package org.suraj;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("products")
public class ProdResource {

    PRepository repo = new PRepository();
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Products> getPds(){

        /*Products pd1 = new Products();
        pd1.setName("Harpic");
        pd1.setPrice(34.50);

        Products pd2 = new Products();
        pd2.setName("Sugar");
        pd2.setPrice(100);

        Products pd3 = new Products();
        pd3.setName("Oil");
        pd3.setPrice(135.68);

        Products pd4 = new Products();
        pd4.setName("Oats");
        pd4.setPrice(200);*/

    /*List<Products> pd = Arrays.asList(pd1, pd2, pd3, pd4);*/

        return repo.getProds();
    }

    @GET
    @Path("product/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Products getPd(@PathParam("id") int id){

        return repo.getProd(id);
    }

    @POST
    @Path("product")
//    @Consumes(MediaType.APPLICATION_XML)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Products createProd(Products p1){

        System.out.println("Is it working");
        System.out.println(p1);
        repo.create(p1);
        return p1;
    }

    @PUT
    @Path("product")
    public Products updateProd(Products p1){
        if(p1.getId() == repo.getProd(p1.getId()).getId()) {
            System.out.println(p1);
            repo.update(p1);
            return p1;
        }
        System.out.println("Data does not exist");
        return null;
    }

    @DELETE
    @Path("product/{id}")
    public Products removeProd(@PathParam("id") int id){
        Products pd = repo.getProd(id);

        if(pd.getId() != 0)
            repo.delete(id);
        return pd;
    }

}