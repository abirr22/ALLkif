/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Evenement;
import edu.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author siwar
 */
public class ServiceEvenement implements IService <Evenement> {

     Connection cnx = DataSource.getInstance().getCnx();
     
    @Override
//    public void ajouter(Evenement e) {
//         try {
//            String req = "INSERT INTO `evenement`(`nomEvenement`, `descriptionEvenement`, `dateEvenement`, `prixEvenement`) VALUES ('"+ e.getNomEvenement() +"','"+ e.getDescriptionEvenement() +"','"+ e.getDateEvenement() +"','"+ e.getPrixEvenement() +"')";
//            Statement st = cnx.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Evenement created !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
     public void ajouter (Evenement e) {
        try {
            String req = "INSERT INTO `evenement` (`nomEvenement`, `descriptionEvenement`, `Artiste`,`dateEvenement`, `prixEvenement`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, e.getNomEvenement());
            ps.setString(2, e.getDescriptionEvenement());
             ps.setString(3, e.getArtiste());
           ps.setDate(4, (Date) e.getDateEvenement()); 
            ps.setDouble(5, e.getPrixEvenement());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `evenement` WHERE idEvenement = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Evenement e) {
        try {
            String req = "UPDATE `evenement` SET `nomEvenement`='" + e.getNomEvenement() + "',`descriptionEvenement`='" + e.getDescriptionEvenement() + "',`Artiste`='" + e.getArtiste() + "',`dateEvenement`='" + e.getDateEvenement() + "',`prixEvenement`='" + e.getPrixEvenement() + "' WHERE `evenement`.`idEvenement` = " + e.getIdEvenement();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Evenement> getAll() {
         List<Evenement> list = new ArrayList<>();
        try {
            String req = "Select * from evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
           while (rs.next()) {
               Evenement e = new Evenement(rs.getInt(1), rs.getString("nomEvenement"), rs.getString("descriptionEvenement"), rs.getString("Artiste"), rs.getDate("dateEvenement"), rs.getFloat("prixEvenement") );
               list.add(e);
          }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Evenement getOneById(int id) {
         Evenement e = null;
        try {
            String req = "Select * from evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                e = new Evenement(rs.getInt(1), rs.getString("nomEvenement"), rs.getString("descriptionEvenement"), rs.getString("Artiste"), rs.getDate("dateEvenement"), rs.getDouble("prixEvenement"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return e;
    }
    }
    

