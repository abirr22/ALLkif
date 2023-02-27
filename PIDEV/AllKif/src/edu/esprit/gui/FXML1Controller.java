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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author zayat
 */
public class FXML1Controller implements Initializable {

    @FXML
    private TableView<panier> display;
    @FXML
    private TableColumn<panier, Integer> id_prod;
    @FXML
    private TableColumn<panier, Integer> id_panier;
    @FXML
    private TableColumn<panier, Integer> prix;
    @FXML
    private TableColumn<panier, Integer> quantite;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button ajouter;
    @FXML
    private Button valider;
    @FXML
    private Button commande;
    @FXML
    private TextField fx_prix;
    @FXML
    private TextField fx_quantite;
    


    ServicePanier ps = new ServicePanier();
    
    
    
  public static void numericOnly(final TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }   
        });
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO 
        id_panier.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_panier()).asObject());
        id_prod.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_prod()).asObject());
        prix.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().getPrix()).asObject());
        quantite.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantite()).asObject());

        numericOnly(fx_prix);
        numericOnly(fx_quantite);

        //xrole.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        ServicePanier crud = new ServicePanier();
        // Populate the table with data
        List<panier> data;
        data = crud.readAll();
        display.getItems().setAll(data);
    }

    @FXML
    private void supprimer(MouseEvent event) {
        if (display.getSelectionModel().getSelectedItem() != null) {
            // Récupérer les données de l'événement sélectionné
            panier selectedPanier = display.getSelectionModel().getSelectedItem();
            ServicePanier sp = new ServicePanier();
            sp.supprimer(selectedPanier.getId_panier());
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("FXML1.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private panier PanierToUpdate;

    @FXML
    private void modifier(MouseEvent event) {
        PanierToUpdate = display.getSelectionModel().getSelectedItem();
        fx_prix.setText("" + PanierToUpdate.getPrix());
        fx_quantite.setText("" + PanierToUpdate.getQuantite());

    }

    @FXML
    private void commande(MouseEvent event) {
          try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("commande.fxml"));
       
        Scene scene = new Scene(fxmlLoader.load(), 630, 400);
        Stage stage = new Stage();
        stage.setTitle("New Window");
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.log(Level.SEVERE, "Failed to create new Window.", e);
    }

    }

    @FXML
    private void valider(MouseEvent event) {
        int prix;
        prix = Integer.parseInt(fx_prix.getText());
        int quantite;
        quantite = Integer.parseInt(fx_quantite.getText());
        // Mettre à jour l'objet event
        PanierToUpdate.setPrix(prix);
        PanierToUpdate.setQuantite(quantite);

        // Mettre à jour l'événement dans la base de données
        ServicePanier sp = new ServicePanier();
        sp.modifier(PanierToUpdate);

        // Fermer la fenêtre
        //Stage stage = (Stage) modifierButton.getScene().getWindow();
        //stage.close();
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("FXML1.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setLPanier(panier PanierToUpdate) {
        this.PanierToUpdate = PanierToUpdate;
        // Afficher les données de l'événement à modifier dans le formulaire
        fx_prix.setText(String.valueOf(PanierToUpdate.getPrix()));
        fx_quantite.setText(String.valueOf(PanierToUpdate.getQuantite()));

    }

    @FXML
    private void ajouter(MouseEvent event) {
        double prix;
        int quantite;

        if (!fx_prix.getText().isEmpty()) {
            prix = Double.parseDouble(fx_prix.getText());
            if (prix <= 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("erreur le prix est invalide");
                alert.show();
            } else if (!fx_quantite.getText().isEmpty()) {
                quantite = Integer.parseInt(fx_quantite.getText());
                if (quantite <= 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("erreur la quantite est invalide");
                    alert.show();
                } else {
                    System.out.println("prix " + prix);

                    panier p = new panier(prix, quantite);
                    ServicePanier crud = new ServicePanier();
                    crud.ajouter(p);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Panier insérée avec succés!");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("ajouter la quantite!");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("ajouter le prix!");
            alert.show();
        }

    }
}
