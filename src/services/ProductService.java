/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.produits;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductService implements IPService <produits>  {
    
    Connection cn = MyConnection.getTest().getCnx();

    @Override
    public void Ajouter(produits t) {
        try {
            System.out.println(t);
            String req = "INSERT INTO `produits`(`product_name`, `product_description`, `product_photo`, `product_price`, `related_artist`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setString(1, t.getProduct_name());
            pst.setString(2, t.getProduct_description());
            pst.setString(3, t.getProduct_photo());
            pst.setFloat(4, t.getProduct_price());
            pst.setInt(5, t.getRelated_artist());
            pst.executeUpdate();
            System.out.println("Produits ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(int id) {
        try {
            String req = "DELETE FROM `produits` WHERE `id_product` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }


    @Override
    public ResultSet Selectionner(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `produits` WHERE `related_artist` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    
    }

    @Override
    public void Modifier(int id,String titre,String description,String photo,Float prix) {
        try {
            String req = "UPDATE `produits` SET `product_name`='"+titre+"',`product_description`='"+description+"',`product_photo`='"+photo+"',`product_price`="+prix+" WHERE `id_product` ="+id;
            PreparedStatement st = cn.prepareStatement(req);
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }

    @Override
    public ResultSet SelectionnerSingle(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `produits` WHERE `id_product` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    
    }
    
    public List<produits> afficher(int id) {
       List<produits> list = new ArrayList<>();

        try {
            String requete = "SELECT  `product_name`, `product_description`, `product_price` FROM `produits` WHERE `related_artist` ="+id;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new produits(rs.getString(1),rs.getString(2),rs.getInt(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
}
