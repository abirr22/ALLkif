/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entites.facture;

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
public class ServiceFacture implements IService <facture> {
     Connection cnx = DataSource.getInstance().getCnx();

    /**
     *
     * @param f
     */
    @Override
     public void ajouter(facture f) {
        try {
            String req = "INSERT INTO facture (`flag_facture`) VALUES ('" + f.isFlag()+ "')";
              
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("facture created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ajouter2(facture f) {
         try {
            String req = "INSERT INTO `facture` (`flag_facture`) VALUES (?,?)";
            
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setBoolean(1, f.isFlag());
            ps.executeUpdate();
            System.out.println("facture Added !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param id
     */
    public void supprimer(int id) {
       try {
            String req = "DELETE FROM `facture` WHERE id_facture = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("facture deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param f
     */
    @Override
    public void modifier(facture f) {
        try {
            String req = "UPDATE `facture` SET `flag_facture` = '" + f.isFlag()  + "' WHERE `facture`.`id_facture` = " + f.getId_facture();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Facture updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   }

    @Override
    public List<facture> getAll() {
        List<facture> list = new ArrayList<>();
        try {
            String req = "Select * from facture";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                facture f = new facture( rs.getBoolean("flag_facture"));
                list.add(f);
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
    public facture getOneById(int id) {
          facture f = null;
        try {
            String req = "Select * from facture";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                f =new facture( rs.getBoolean("flag_facture"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return f;
    }

    @Override
    public void supprimer(int id, int id_panier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }

