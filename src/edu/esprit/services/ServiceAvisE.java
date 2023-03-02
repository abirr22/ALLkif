/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.AvisEvenement;
import edu.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author siwar
 */
public class ServiceAvisE implements IService <AvisEvenement>{

      Connection cnx = DataSource.getInstance().getCnx();
      
    @Override
    public void ajouter(AvisEvenement e) {
          try {
              String req = "INSERT INTO `avisevenement`(`AvisE`) VALUES ('"+ e.getAvis() +"')";
              Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("Avis added!");
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          }
     
    }

    @Override
    public void supprimer(int id) {
          try {
              String req ="DELETE FROM `avisevenement` WHERE idAvisE = " + id;
              Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("Avis deleted !");
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          }
    }

    @Override
    public void modifier(AvisEvenement e) {
          try {
              String req = "UPDATE `avisevenement` SET `AvisE`='" + e.getAvis() + "' WHERE `avisevenement`.`idAvisE` = " + e.getIdAvisE();
              Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("Avis updated !");
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          }
    }

    @Override
    public List<AvisEvenement> getAll() {
         List<AvisEvenement> list = new ArrayList<>();
        try {
            String req = "Select * from avisevenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
           while (rs.next()) {
               AvisEvenement a = new AvisEvenement(rs.getInt(1), rs.getString(2) );
               list.add(a);
          }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public AvisEvenement getOneById(int id) {
           AvisEvenement a = null;
        try {
            String req = "Select * from avisevenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                a = new AvisEvenement(rs.getInt(1), rs.getInt(2), rs.getString(3) );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return a;
    }
    }
    

