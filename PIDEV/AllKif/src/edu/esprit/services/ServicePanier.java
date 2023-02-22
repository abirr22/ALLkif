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
                panier p = new panier( rs.getDouble("prix"), rs.getInt(4));
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

    }

