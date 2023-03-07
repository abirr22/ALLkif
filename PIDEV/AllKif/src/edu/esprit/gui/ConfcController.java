/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zayat
 */
public class ConfcController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back_btn(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("commande.fxml"));
            Parent page2
                   = loader.load();
            CommandeController commandeController= loader.getController();
            
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {

        }
    }

    @FXML
    private void payer(ActionEvent event) { 
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("paiment.fxml"));
            Parent page2
                   = loader.load();
            CommandeController commandeController= loader.getController();
            
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {

        }
    }
    
}
