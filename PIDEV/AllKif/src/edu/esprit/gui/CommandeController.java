/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entites.commande;
import edu.esprit.entites.panier;
import edu.esprit.services.ServiceCommande;
import edu.esprit.services.ServicePanier;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zayat
 */
public class CommandeController implements Initializable {

    @FXML
    private Button fx_cc;
    @FXML
    private Button fx_AC;
    @FXML
    private TableView<commande> total;
    @FXML
    private TableColumn<commande, Integer> commande;
    ServiceCommande pc = new ServiceCommande(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        commande.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().getTotale()).asObject()); 
         ServiceCommande crud = new ServiceCommande();
        // Populate the table with data
        List<commande> data;
        data = crud.getAll();
        total.getItems().setAll(data);
    }    

    @FXML
    private void confirmer_commande(MouseEvent event) { 
         try {

Parent page2 =
FXMLLoader.load(getClass().getResource("confc.fxml"));
Scene scene = new Scene(page2);
Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
stage.setScene(scene);
stage.show();
} catch (IOException ex) {


}
        
    }

    @FXML
    private void annuler_commande(MouseEvent event) { 
         if (total.getSelectionModel().getSelectedItem() != null) {
            // Récupérer les données de l'événement sélectionné
            commande selectedCommande = total.getSelectionModel().getSelectedItem();
            ServiceCommande sc = new ServiceCommande();
            sc.supprimer(selectedCommande.getId_commande());
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("Commande.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void facture(ActionEvent event) {
    }
    
}
