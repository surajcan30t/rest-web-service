package org.suraj;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PRepository {


    Connection con = null;
    public PRepository(){
        String url = "jdbc:mysql://localhost:3306/stud";
        String user = "*****";
        String pw = "******";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pw);
        }catch (Exception error){
            System.out.println(error);
        }
    }
    public List<Products> getProds(){
        List <Products> products = new ArrayList<>();
        String sql = "select * from prod";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Products pd = new Products();
                pd.setId(rs.getInt(1));
                pd.setName(rs.getString(2));
                pd.setPrice(rs.getDouble(3));

                products.add(pd);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return products;
    }

    public Products getProd(int id){
        String sql = "select * from prod where id="+id;
        Products pd = new Products();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){

                pd.setId(rs.getInt(1));
                pd.setName(rs.getString(2));
                pd.setPrice(rs.getDouble(3));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return pd;
    }

    public void create(Products p1) {
        String sql = "insert into prod values(?,?,?)";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, p1.getId());
            st.setString(2,p1.getName());
            st.setDouble(3, p1.getPrice());
            st.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void update(Products p1) {
        String sql = "update prod set name = ?, price = ? where id = ?;";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,p1.getName());
            st.setDouble(2, p1.getPrice());
            st.setInt(3, p1.getId());
            st.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void delete(int id) {
        String sql = " delete from prod where id = ?;";

        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
