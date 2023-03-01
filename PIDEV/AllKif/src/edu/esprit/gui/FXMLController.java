/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.impl.charm.a.b.b.i;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import edu.esprit.entites.panier;
import edu.esprit.services.ServicePanier;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zayat
 */
public class FXMLController implements Initializable {

    private static Object itemTable;

    @FXML
    private Pane pn_boutique;
    @FXML
    private Pane pn_evenements;
    @FXML
    private Pane pn_blog;
    @FXML
    private Pane pn_ajouterproduits;
    @FXML
    private Pane pn_mesevenements;
    @FXML
    private Pane pn_mesblog;
    @FXML
    private Pane pn_ajouterblog;
    @FXML
    private Pane pn_compte;
    @FXML
    private Pane pn_mesproduits;
    @FXML
    private Pane pn_ajouterevenement;
    @FXML
    private Pane pn_panier;
    @FXML
    private TableView<panier> display;
    @FXML
    private TableColumn<panier, String> image_produit;
    @FXML
    private TableColumn<panier, String> nom_produit;
    @FXML
    private TableColumn<panier, String> description;
    @FXML
    private TableColumn<panier, Integer> prix;
    @FXML
    private TableColumn<panier, Integer> quantite;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField fx_quantite;
    @FXML
    private Button valider;
    @FXML
    private TextField fx_total;
    @FXML
    private Button valider_commande;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        
     
         
        
        image_produit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPic()));
        nom_produit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getname()));
        prix.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().getPrice()).asObject());
        //xrole.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        quantite.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().getQuantite()).asObject());
        //id_prod.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_prod()).asObject()); 
        
        
        
                 int total = 0 ;
for (panier item : display.getItems()) {
    total = total + item.getPrice();
}
      
        numericOnly(fx_quantite); 
        
         ServicePanier crud = new ServicePanier();
        // Populate the table with data
        List<panier> data;
        data = crud.SelectAll();
        display.getItems().setAll(data);
        totalCalculation();

        // TODO
    }     

    @FXML
    private void btn_boutique(ActionEvent event) {
    }

    @FXML
    private void btn_panier(ActionEvent event) {
    }

    @FXML
    private void btn_evenement(ActionEvent event) {
    }

    @FXML
    private void btn_blog(ActionEvent event) {
    }

    @FXML
    private void btn_deconnecter(ActionEvent event) {
    }

    @FXML
    private void btn_mesproduits(ActionEvent event) {
    }

    @FXML
    private void btn_ajouterporduits(ActionEvent event) {
    }

    @FXML
    private void btn_mesevenements(ActionEvent event) {
    }

    @FXML
    private void btn_ajouterevenements(ActionEvent event) {
    }

    @FXML
    private void btn_mesblog(ActionEvent event) {
    }

    @FXML
    private void btn_ajouterblog(ActionEvent event) {
    }

    @FXML
    private void btn_compte(ActionEvent event) {
    }

    @FXML
    private void supprimer(MouseEvent event) { 
        
         if (display.getSelectionModel().getSelectedItem() != null) {
            // Récupérer les données de l'événement sélectionné
            panier selectedPanier = display.getSelectionModel().getSelectedItem();
            ServicePanier sp = new ServicePanier();
            sp.supprimer(selectedPanier.getId_prod(),selectedPanier.getId_panier());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Produit Supprimée");
                alert.show();
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Voulez vous supprimer ce produit?");
                alert.show();

    }

    
    private panier PanierToUpdate;
 @FXML
    private void modifier(MouseEvent event) { 
        
        PanierToUpdate = display.getSelectionModel().getSelectedItem();
        System.out.println(PanierToUpdate.toString());
        fx_quantite.setText("" + PanierToUpdate.getQuantite());

        
    }

    @FXML
    private void valider(MouseEvent event) { 
       
        int quantite;
        
        quantite = Integer.parseInt(fx_quantite.getText());
        System.out.println("quantite "+quantite);
        // Mettre à jour l'objet event
      
        PanierToUpdate.setQuantite(Integer.parseInt(fx_quantite.getText()));

        // Mettre à jour l'événement dans la base de données
        ServicePanier sp = new ServicePanier();
        sp.modifier(PanierToUpdate);

        // Fermer la fenêtre
        //Stage stage = (Stage) modifierButton.getScene().getWindow();
        //stage.close();
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setLPanier(panier PanierToUpdate) {
        this.PanierToUpdate = PanierToUpdate;
        // Afficher les données de l'événement à modifier dans le formulaire
      
        fx_quantite.setText(String.valueOf(PanierToUpdate.getQuantite()));

    }

    

    @FXML
    private void valider_Commande(ActionEvent event) {
         try {

Parent page2 =
FXMLLoader.load(getClass().getResource("commande.fxml"));
Scene scene = new Scene(page2);
Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
stage.setScene(scene);
stage.show();
} catch (IOException ex) {


}
    }  
  
   public void totalCalculation (){

   int total = 0;
        
   total = display.getItems().stream().map(
     (item) -> item.getPrice()).reduce(total, (accumulator, _item) -> accumulator + _item);

          fx_total.setText(String.valueOf(total));
}
    
}
