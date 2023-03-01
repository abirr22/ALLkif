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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author zayat
 */
public class CommandeController implements Initializable {

    @FXML
    private TableView<commande> table;
    @FXML
    private TableColumn<commande, String> img;
    @FXML
    private TableColumn<commande, String> nom;
    @FXML
    private TableColumn<commande, String> desc;
    @FXML
    private TableColumn<commande, Integer> price;
    @FXML
    private TableColumn<commande, Integer> quan;
    @FXML
    private Button fx_cc;
    @FXML
    private Button fx_AC;
    
    ServicePanier ps = new ServicePanier(); 
    ServiceCommande commande = new ServiceCommande(); 

    /**
     * Initializes the controller class.
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) {  
        
        img.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPic()));
        nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        price.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().getPrice()).asObject());
        //xrole.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        desc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
       
        //id_prod.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_prod()).asObject()); 
         ServiceCommande crud = new ServiceCommande();
         ServicePanier khouloud = new ServicePanier();
        // Populate the table with data
        List<commande> data;
        List<panier> panier;

        data = crud.SelectAll();
     
        table.getItems().setAll(data);
        
        // TODO
    }    

    @FXML
    private void confirmer_commande(MouseEvent event) {
    }

    @FXML
    private void annuler_commande(MouseEvent event) {
    }

    @FXML
    private void facture(ActionEvent event) {
    }
    
}
