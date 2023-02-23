/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Evenement;
import edu.esprit.services.ServiceEvenement;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class EvenementFXMLController implements Initializable {

   @FXML
    private Pane pn_boutique;
    @FXML
    private Pane pn_panier;
    @FXML
    private Pane pn_evenements;
    @FXML
    private Pane pn_blog;
    @FXML
    private Pane pn_mesproduits;
    @FXML
    private Pane pn_ajouterproduits;
    @FXML
    private Pane pn_mesevenements;
    @FXML
    private Pane pn_ajouterevenement;
    @FXML
    private Pane pn_mesblog;
    @FXML
    private Pane pn_ajouterblog;
    @FXML
    private Pane pn_compte;
    @FXML
    private TextField tfNomE;
    @FXML
    private TextField tfArtisteE;
    @FXML
    private TextField tfPrixE;
    @FXML
    private DatePicker DPEvenement;
    @FXML
    private TextArea tfDescriptionE;
    @FXML
    private Label myLabel;
      @FXML
    private TilePane tilePane;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
}

    @FXML
    private void btn_boutique(javafx.event.ActionEvent event) {
        pn_boutique.toFront();
    }

    @FXML
    private void btn_panier(javafx.event.ActionEvent event) {
        pn_panier.toFront();
    }

    @FXML
    private void btn_evenement(javafx.event.ActionEvent event) {
        pn_evenements.toFront();
        ServiceEvenement se = new ServiceEvenement();

        List<Evenement> e =   se.getAll();
       for (int i=0; i< e.size(); i++){
          
           Label lblNom = new Label(e.get(i).getNomEvenement());
            Label lblDescription = new Label(e.get(i).getDescriptionEvenement());
             Label lblArtiste = new Label(e.get(i).getArtiste());
              Label lblDate = new Label(e.get(i).getDateEvenement().toString());
              Button btn= new Button("Voir plus de détails");
              VBox vbox = new VBox();
              vbox.getChildren().add(lblNom);
              vbox.getChildren().add(lblDescription);
              vbox.getChildren().add(lblArtiste);
              vbox.getChildren().add(lblDate);
              vbox.getChildren().add(btn);
                 
               Evenement ev = se.getOneById(e.get(i).getIdEvenement());
       tilePane.getChildren().add(vbox);
       
      
      btn.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
//                    pn_detailE.toFront();
               pn_evenements.getChildren().clear();
                Label lblNom = new Label(ev.getNomEvenement());
            Label lblDescription = new Label(ev.getDescriptionEvenement());
             Label lblArtiste = new Label(ev.getArtiste());
            Label lblDate = new Label(ev.getDateEvenement().toString());
             String Prix= Double.toString(ev.getPrixEvenement());
              Label lblPrix = new Label(Prix);
              VBox vbox = new VBox();
              vbox.getChildren().add(lblNom);
              vbox.getChildren().add(lblDescription);
              vbox.getChildren().add(lblArtiste);
              vbox.getChildren().add(lblDate);
              vbox.getChildren().add(lblPrix);
              Pane pnDE = new Pane();
              pnDE.getChildren().add(vbox);
              

           
              }
           });
     }
       

    }

    @FXML
    private void btn_blog(javafx.event.ActionEvent event) {
        pn_blog.toFront();
    }

    @FXML
    private void btn_deconnecter(javafx.event.ActionEvent event) {
    }

    @FXML
    private void btn_mesproduits(javafx.event.ActionEvent event) {
        pn_mesproduits.toFront();
    }

    @FXML
    private void btn_ajouterporduits(javafx.event.ActionEvent event) {
        pn_ajouterproduits.toFront();
    }

    @FXML
    private void btn_mesevenements(javafx.event.ActionEvent event) {
        pn_mesevenements.toFront();
    }

    @FXML
    private void btn_ajouterevenements(javafx.event.ActionEvent event) {
        pn_ajouterevenement.toFront();
    }

    @FXML
    private void btn_mesblog(javafx.event.ActionEvent event) {
        pn_mesblog.toFront();
    }

    @FXML
    private void btn_ajouterblog(javafx.event.ActionEvent event) {
        pn_ajouterblog.toFront();
    }

    @FXML
    private void btn_compte(javafx.event.ActionEvent event) {
        pn_compte.toFront();
        
    }

    @FXML
    private void ajouterDate(ActionEvent event) {
        
        LocalDate maDate = DPEvenement.getValue();
  String myFormattedDate = maDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
  myLabel.setText(myFormattedDate);
    }

    @FXML
    private void ajouterEvenement(ActionEvent event) {
        
         Date date= Date.valueOf(DPEvenement.getValue());
       String Prix = tfPrixE.getText();
       double PrixE= Double.parseDouble(Prix);
   
       ServiceEvenement se = new ServiceEvenement();
               Evenement e = new Evenement(tfNomE.getText(), tfDescriptionE.getText(),tfArtisteE.getText(),date ,PrixE);
               se.ajouter(e);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement added !", ButtonType.OK);
                a.showAndWait();
    }
    
}
