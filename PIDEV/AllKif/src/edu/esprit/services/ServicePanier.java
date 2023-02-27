/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entites.panier;
import edu.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zayat
 */
public class ServicePanier implements IService <panier> {
     Connection cnx = DataSource.getInstance().getCnx();

    /**
     *
     * @param p
     */
    @Override
     public void ajouter(panier p) {
        try {
            String req = "INSERT INTO panier (`prix`,`quantite`) VALUES ('" + p.getPrix() + "', '" + p.getQuantite() + "')";
              
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("panier created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(panier p) {
         try {
            String req = "INSERT INTO `panier` (`prix`, `quantite`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDouble(1, p.getPrix());
            ps.setInt(2, p.getQuantite());
            ps.executeUpdate();
            System.out.println("panier Added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void supprimer(int id) {
       try {
            String req = "DELETE FROM `panier` WHERE id_pannier = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("panier deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param p
     */
    @Override
    public void modifier(panier p) {
        try {
            String req = "UPDATE `panier` SET `prix` = '" + p.getPrix() + "', `quantite` = '" + p.getQuantite() + "' WHERE `panier`.`id_pannier` = " + p.getId_panier();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Panier updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   }

    @Override
    public List<panier> getAll() {
        List<panier> list = new ArrayList<>();
        try {
            String req = "Select * from panier";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("rs" +rs.toString());
                panier p = new panier( rs.getInt(1),rs.getInt(2),rs.getInt("prix"), rs.getInt(4));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    } 
    
    
    public List<panier> SelectAll() {
        List<panier> list = new ArrayList<>();
        try {
            String req = "SELECT p.id_pannier,\n" +
"       p.prix,\n" +
"       p.quantite,\n" +
"       pr.name,\n" +
"       pr.description,\n" +
"       pr.picture,\n" +
"       pr.price,\n" +
"       pp.id_produit\n" +
"       from `panier` p,\n" +
"       `prod` pr,\n" +
"       `panier produit` pp\n" +
"       where p.id_user = '1' and pp.id_pannier = p.id_pannier and pr.id_product = pp.id_produit  \n" +
"ORDER BY `p`.`id_pannier` ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("rs" +rs.toString());
                panier p = new panier( rs.getInt(1),rs.getDouble("prix"), rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public panier getOneById(int id) {
          panier p = null;
        try {
            String req = "Select * from panier";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p =new panier( rs.getDouble("prix"), rs.getInt(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

    public List<panier> readAll() {
       //return new ArrayList<panier>();
       return getAll();
       
    
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

   // public void insert(panier p) {
       // return ajouter(panier p); //To change body of generated methods, choose Tools | Templates.

    

   // }

