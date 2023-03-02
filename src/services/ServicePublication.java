/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.DataSource;
import entities.Publications;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


        ;


/**
 *
 * @author khedi
 */
public class ServicePublication implements IService<Publications>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Publications p) {
        try {
            String req = "INSERT INTO `publication` (`titre_pub`, `texte_pub`, `photo_pub`) VALUES ('" + p.getTitre_pub()+ "', '" + p.getTexte_pub() +"', '" + p.getPhoto_pub() + "')";
            Statement st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Publication created !");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }        
    }
    public boolean checkIfExists(String titre) {
        //Connection cnx = DataSource.getInstance().getCnx();
        boolean result = false;
        try {
            String req = "SELECT * FROM `publication` WHERE titre_pub = ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, titre);
            ResultSet rs = st.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }       

        return result;
    }
    

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `publication` WHERE id_pub = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("publication deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
//    @Override
//    public void modifier(int id,String titre_pub,String texte_pub,String photo_pub,Date date_pub) {
//        try {
//            String req = "UPDATE `publications` SET `titre_pub`='"+titre_pub+"',`texte_pub`='"+texte_pub+"',`photo_pub`='"+photo_pub+"',`date`="+date_pub+" WHERE `id_pub` ="+id;
//            PreparedStatement st = cnx.prepareStatement(req);
//            st.executeUpdate(req);
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        } 
//    }

    @Override
    public void modifier(Publications p) {
        try {
            String req = "UPDATE `publication` SET `titre_pub` = '" + p.getTitre_pub()+ "', `texte_pub` = '" + p.getTexte_pub()+ "', `photo_pub` = '" + p.getPhoto_pub()+ "' WHERE `id_pub` = " + p.getId();
            PreparedStatement st = cnx.prepareStatement(req);
            //Statement st = cnx.createStatement(req);
            st.executeUpdate(req);
            System.out.println("Publication updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public ResultSet Selection() {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `publication`";
            PreparedStatement st = cnx.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    
    }
    
    @Override
    public ResultSet SelectionOne(int id) {
        ResultSet rs = null;
        try {
            String req = "SELECT * FROM `publication` WHERE `id_pub` ="+id+"";
            PreparedStatement st = cnx.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;    
    }
    

//    @Override
//    public List<Publications> getAll() {
//        List<Publications> list = new ArrayList<>();
//        try {
//            String req = "Select * from publication";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//                Publications p = new Publications(rs.getInt("id_pub"), rs.getString("titre_pub"), rs.getString("texte_pub"), rs.getString("photo_pub"));
//                list.add(p);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return list;
//    }
//
//    @Override
//    public Publications getOneByOne(int id) {
//        Publications p = null;
//        try {
//            String req = "Select * from publication";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req); 
//            while (rs.next()) {
//                p = new Publications(rs.getInt(""), rs.getString("titre_pub"), rs.getString("texte_pub"), rs.getString("photo_pub"));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return p;
//    }

    public ResultSet Selection(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
}
