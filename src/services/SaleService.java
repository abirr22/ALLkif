/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.sale;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SaleService implements ISService <sale>  {
    
    Connection cn = MyConnection.getTest().getCnx();

    @Override
    public void Ajouter(sale t) {
        try {
            String req = "INSERT INTO `solde`(`taux`, `id_produit`) VALUES (?,?)";
            PreparedStatement pst = cn.prepareStatement(req);
            pst.setInt(1, t.getTaux());
            pst.setInt(2, t.getId_produit());
            pst.executeUpdate();
            System.out.println("solde ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(int id) {
        try {
            String req = "DELETE FROM `solde` WHERE `id_produit` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }     }

    @Override
    public void Modifier(int taux,int id) {
        try {
            String req = "UPDATE `solde` SET `taux`="+taux+" WHERE `id_produit` ="+id;
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
            String req = "SELECT * FROM `solde` WHERE `id_produit` ="+id+"";
            PreparedStatement st = cn.prepareStatement(req);
            rs = st.executeQuery(req);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;        
    }
    
}
