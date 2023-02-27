/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;


import edu.esprit.entites.PanierProduit;
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
public class ServicePanierProduit implements IService <PanierProduit> { 
Connection cnx = DataSource.getInstance().getCnx();
    

   
    @Override
     public void ajouter(PanierProduit pp) {
        try {
            String req = "INSERT INTO panier produit (`id_produit`,`id_panier`) VALUES ('" + pp.getId_produit() + "', '" + pp.getId_panier() + "')";
              
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("panier produit created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(PanierProduit pp) {
         try {
            String req = "INSERT INTO `panier produit` (`id_panier`, `id_produit`) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDouble(1, pp.getPrix());
            ps.setInt(2, pp.getQuantite());
            ps.executeUpdate();
            System.out.println("panier produit Added !");
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
            String req = "DELETE FROM `panier produit` WHERE id_pannier = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("panier produit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param p
     */
    @Override
    public void modifier(PanierProduit pp) {
        try {
            String req = "UPDATE `panier produit` SET `Id_produit` = '" + pp.getId_produit() + "', `Id_panier` = '" + pp.getId_panier() + "' WHERE `panier`.`id_pannier` = " + pp.getId_panier();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("PanierProduit updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   }

    @Override
    public List<PanierProduit> getAll() {
        List<PanierProduit> list = new ArrayList<>();
        try {
            String req = "Select * from panier produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                System.out.println("rs" +rs.toString());
                 PanierProduit pp = new PanierProduit( rs.getInt(1),rs.getInt(2));
                list.add(pp);
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
    public PanierProduit getOneById(int id) {
          PanierProduit pp = null;
        try {
            String req = "Select * from panier produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                pp =new PanierProduit( rs.getInt(1),rs.getInt(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return pp;
    } 
}
