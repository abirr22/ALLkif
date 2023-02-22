/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entites.panier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author zayat
 */
public class AfficherController implements Initializable { 
    
       @FXML
    private TableView<panier> table;
    // Les labels pour afficher les informations du livreur
    @FXML
    private TableColumn<panier,Integer> fx_id;
     @FXML
    private TableColumn<panier,Integer> fx_id_prod;

   
    @FXML
    private TableColumn<panier,Integer> fx_prix;
    @FXML
    private TableColumn<panier,Integer> fx_quantite;


    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
         fx_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_panier()).asObject());
         fx_id_prod.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_prod()).asObject());
        //fx_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getname()));
        //fx_description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getdescription()));
        fx_prix.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPrix()).asObject());
        //xrole.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        fx_quantite.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantite()).asObject());
        ServicePanier crud = new ServicePanier ();
        // Populate the table with data
        List<panier> data;
        data = crud.readAll();
        table.getItems().setAll(data);

    }    
    }    
    
}
