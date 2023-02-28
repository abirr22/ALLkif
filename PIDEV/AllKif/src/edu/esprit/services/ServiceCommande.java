/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entites.commande;
import edu.esprit.entites.panier;
//import edu.esprit.entites.panier;
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
public class ServiceCommande implements IServiceCommande <commande> {
     Connection cnx = DataSource.getInstance().getCnx();

    /**
     *
     * @param c
     */
    @Override
     public void ajouter(commande c) {
        try {
            String req = "INSERT INTO commande (`totale`) VALUES ('" + c.getTotale() + "')";
              
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(commande c) {
         try {
            String req = "INSERT INTO `commande` (`totale`) VALUES (?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDouble(1, c.getTotale());
            
            ps.executeUpdate();
            System.out.println("commande Added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    
     public List<commande> SelectAll() {
        List<commande> list = new ArrayList<>();
        try {
            String req = "SELECT commande.id_commande,\n" +
"        prod.name,\n" +
"        prod.description,\n" +
"        prod.picture,\n" +
"        prod.price,\n" +
"        panier.id_pannier,\n" +
"        commande.totale,\n" +
"        commande.valide\n" +
" from `commande` commande,\n" +
"`panier` panier,\n" +
"`prod` prod \n" +
"where panier.id_user = '1' and prod.id_product = commande.id_panier = panier.id_pannier  \n" +
"ORDER BY `commande`.`id_commande` ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("rs" +rs.toString());
                commande c = new commande( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getBoolean(8));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    /**
     *
     * @param id
     */
    @Override
    public void supprimer(int id) {
       try {
            String req = "DELETE FROM `commande` WHERE id_commande = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param c
     */
    @Override
    public void modifier( commande c) {
        try {
            String req = "UPDATE `commande` SET `totale` = '" + c.getTotale()  + "' WHERE `commande`.`id_commande` = " + c.getId_commande();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   }

    @Override
    public List<commande> getAll() {
        List<commande> list = new ArrayList<>();
        try {
            String req = "Select * from commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                commande c = new commande( rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getBoolean(4));
                list.add(c);
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
    public commande getOneById(int id) {
          commande c = null;
        try {
            String req = "Select * from commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                c =new commande( rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getBoolean(4)); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }

    }

