/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entites.PanierProduit;
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
            String req = "SELECT `totale`, `content` FROM `commande` WHERE 1";
       
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("rs" +rs.toString());
                commande c = new commande( rs.getInt(1),rs.getString(2));
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
    public void supprimer(int id_panier) {
       try {
            String req = "DELETE FROM `commande` WHERE id_panier = " + id_panier;
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
                commande c = new commande( rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getBoolean(4),rs.getInt(5),rs.getInt(6));
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
                c =new commande( rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getBoolean(4),rs.getInt(5),rs.getInt(6)); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }

    @Override
    public void creecommande(int id_panier) {
         List<PanierProduit> list = new ArrayList<>();
        try {
            String req = "SELECT  pp.quantite,\n" +
"        prod.name,\n" +
"        prod.price\n" +
" from `prod` prod,\n" +
"`panier produit` pp \n" +
"where pp.`id_pannier` = '"+id_panier+"' and prod.id_product = pp.`id_produit`\n" +
"";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            String content = "";
            int totale=0;
            while (rs.next()) {
                System.out.println("rs" +rs.toString());
                totale = rs.getInt(1)*rs.getInt(3);
                content +=rs.getString(2)+"(quantite:"+rs.getInt(1)+")";
                //commande c = new commande(totale, int id_user, String content,int price);
                //list.add(c);
            }
            commande c = new commande(totale,id_panier,content);
            System.out.println("Commande created !");
            try {
            String req1 = "INSERT INTO `commande`(`totale`,`id_user`, `content`) VALUES ('"+c.getTotale()+"','"+c.getId_user()+"','"+c.getContent()+"')";
            Statement st1 = cnx.createStatement();
            st1.executeUpdate(req1);
            System.out.println("Commande ajoute !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

   
    }

