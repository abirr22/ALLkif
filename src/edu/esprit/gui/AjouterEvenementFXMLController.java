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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class AjouterEvenementFXMLController implements Initializable {

    @FXML
    private TextField tfNomE;
    @FXML
    private TextField tfArtisteE;
    @FXML
    private TextArea tfDescriptionE;
    @FXML
    private TextField tfPrixE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
        // TODO
    }    
    
        @FXML
  private  DatePicker DPEvenement ;
        @FXML
 private Label myLabel ;

           @FXML
 public void ajouterDate (ActionEvent event) {
  
  LocalDate maDate = DPEvenement.getValue();
  String myFormattedDate = maDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
  myLabel.setText(myFormattedDate);
 }
  

    @FXML
    private void ajouterEvenement(ActionEvent event) {
//       java.util.Date date= java.util.Date.from(DPEvenement.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
       Date date= Date.valueOf(DPEvenement.getValue());
       String Prix = tfPrixE.getText();
       double PrixE= Double.parseDouble(Prix);
   
       ServiceEvenement se = new ServiceEvenement();
               Evenement e = new Evenement(tfNomE.getText(), tfDescriptionE.getText(),tfArtisteE.getText(),date ,PrixE);
               se.ajouter(e);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement added !", ButtonType.OK);
                a.showAndWait();
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
         ServiceEvenement se = new ServiceEvenement();
         
        
    }
        
    
}
