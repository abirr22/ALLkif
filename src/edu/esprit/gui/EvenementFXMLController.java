/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.esprit.entities.Evenement;
import edu.esprit.services.ServiceEvenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
      @FXML
    private TilePane tilePane2;



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
//                Label lblNom = new Label(ev.getNomEvenement());
//            Label lblDescription = new Label(ev.getDescriptionEvenement());
//             Label lblArtiste = new Label(ev.getArtiste());
//            Label lblDate = new Label(ev.getDateEvenement().toString());
//             String Prix= Double.toString(ev.getPrixEvenement());
//              Label lblPrix = new Label(Prix);
//              VBox vbox = new VBox();
//              vbox.getChildren().add(lblNom);
//              vbox.getChildren().add(lblDescription);
//              vbox.getChildren().add(lblArtiste);
//              vbox.getChildren().add(lblDate);
//              vbox.getChildren().add(lblPrix);
//              Pane pnDE = new Pane();
//              pnDE.getChildren().add(vbox);
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("DE.fxml"));
              // Pane pane= fxmlLoader.load();
                

           
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
        ServiceEvenement se = new ServiceEvenement();
          List<Evenement> e =   se.getAll();
          
       for (int i=0; i< e.size(); i++){
//            Label lblNom2 = new Label(e.get(i).getNomEvenement());
//            tilePane2.getChildren().add(lblNom2);
          // String Art= "Si flen";
          //String Artiste=e.get(i).getArtiste();
          
         // if(Artiste=="Si flen"){
              Label lblNom = new Label(e.get(i).getNomEvenement());
              Label lblDescription = new Label(e.get(i).getDescriptionEvenement());
              Label lblArtiste = new Label(e.get(i).getArtiste());
              Label lblDate = new Label(e.get(i).getDateEvenement().toString());
              Button btn= new Button("Voir plus de détails");
               Button btnS= new Button("Supprimer évènement");
                Button btnM= new Button("Modifier évènement");
              VBox vbox = new VBox();
              vbox.getChildren().add(lblNom);
              vbox.getChildren().add(lblDescription);
              vbox.getChildren().add(lblArtiste);
              vbox.getChildren().add(lblDate);
              vbox.getChildren().add(btn);
              vbox.getChildren().add(btnS);
              vbox.getChildren().add(btnM);
              tilePane2.getChildren().add(vbox);
              int id= e.get(i).getIdEvenement();
              Evenement ev= se.getOneById(id);
         // } 
          btnS.setOnAction((ActionEvent event1) -> {
              se.supprimer(id);
              tilePane2.getChildren().clear();
              refresh();
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement deleted !", ButtonType.OK);
                a.showAndWait();
              });
            btnM.setOnAction((ActionEvent event1) -> {
                tilePane2.getChildren().clear();
                 pn_ajouterevenement.toFront();
//                Pane pane = new Pane();
//                pane.toFront();
                  tfNomE.setText(ev.getNomEvenement());
                   tfDescriptionE.setText(ev.getDescriptionEvenement());
                   LocalDate date = ev.getDateEvenement().toLocalDate();
                DPEvenement.setValue(date);
                String Prix = Double.toString(ev.getPrixEvenement());
                tfPrixE.setText(Prix);
                tfArtisteE.setText(ev.getArtiste());
                
           
      
        
              });
       }
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
   DPEvenement.valueProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue == null) {
        // Si la date est nulle, ne pas valider
        return;
    }
    // Vérifier que la date est valide
     Date date= Date.valueOf(DPEvenement.getValue());
    if (!isValidDate(date)) {
        // Afficher un message d'erreur si la date est invalide
      //  Alert alert = new Alert(AlertType.ERROR);
        Alert alert= new Alert(Alert.AlertType.ERROR, "Date invalide !",ButtonType.OK);
//        alert.setHeaderText("Erreur de saisie");
//        alert.setContentText("La date saisie est invalide.");
        alert.showAndWait();
        // Réinitialiser la date sélectionnée à la valeur précédente
        DPEvenement.setValue(oldValue);
    }
   });
    }

    @FXML
    private void ajouterEvenement(ActionEvent event) {
        
        DPEvenement.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue == null) {
                    // Si la date est nulle, ne pas valider
                    return;
                }
                // Vérifier que la date est valide
                Date date= Date.valueOf(DPEvenement.getValue());
                if (!isValidDate(date)) {
                    // Afficher un message d'erreur si la date est invalide
                    
                    Alert alert= new Alert(Alert.AlertType.ERROR, "Date invalide !",ButtonType.OK);
                    alert.showAndWait();
                    // Réinitialiser la date sélectionnée à la valeur précédente
                    DPEvenement.setValue(oldValue);
                }
               
                 String Prix = tfPrixE.getText();
                 double PrixE= Double.parseDouble(Prix);
                 ServiceEvenement se = new ServiceEvenement();
                Evenement e = new Evenement(tfNomE.getText(), tfDescriptionE.getText(),tfArtisteE.getText(),date ,PrixE);
                se.ajouter(e);
             
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement added !", ButtonType.OK);
                a.showAndWait();
                
            }
        }); 
        
         //Date date= Date.valueOf(DPEvenement.getValue());
//       String Prix = tfPrixE.getText();
//       double PrixE= Double.parseDouble(Prix);
//   
//       ServiceEvenement se = new ServiceEvenement();
//               Evenement e = new Evenement(tfNomE.getText(), tfDescriptionE.getText(),tfArtisteE.getText(),date ,PrixE);
//               se.ajouter(e);
//                Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement added !", ButtonType.OK);
//                a.showAndWait();
    }
    
    private boolean isValidDate(Date date) {
    if (date == null) {
        // Si la date est nulle, ne pas valider
        return false;
    }
    // Vérifier que la date est valide
    Date now = Date.valueOf(LocalDate.now());
    return !date.before(now);
}
    
  private void refresh(){
      
      ServiceEvenement se = new ServiceEvenement();
          List<Evenement> e =   se.getAll();
          
       for (int i=0; i< e.size(); i++){
//            Label lblNom2 = new Label(e.get(i).getNomEvenement());
//            tilePane2.getChildren().add(lblNom2);
          // String Art= "Si flen";
          //String Artiste=e.get(i).getArtiste();
          
         // if(Artiste=="Si flen"){
              Label lblNom = new Label(e.get(i).getNomEvenement());
              Label lblDescription = new Label(e.get(i).getDescriptionEvenement());
              Label lblArtiste = new Label(e.get(i).getArtiste());
              Label lblDate = new Label(e.get(i).getDateEvenement().toString());
              Button btn= new Button("Voir plus de détails");
               Button btnS= new Button("Supprimer évènement");
                Button btnM= new Button("Modifier évènement");
              VBox vbox = new VBox();
              vbox.getChildren().add(lblNom);
              vbox.getChildren().add(lblDescription);
              vbox.getChildren().add(lblArtiste);
              vbox.getChildren().add(lblDate);
              vbox.getChildren().add(btn);
              vbox.getChildren().add(btnS);
              vbox.getChildren().add(btnM);
              tilePane2.getChildren().add(vbox);
              int id= e.get(i).getIdEvenement();
              Evenement ev= se.getOneById(id);
         // } 
          btnS.setOnAction((ActionEvent event1) -> {
              se.supprimer(id);
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement deleted !", ButtonType.OK);
                a.showAndWait();
              });
            btnM.setOnAction((ActionEvent event1) -> {
               tilePane2.getChildren().clear();
                 pn_ajouterevenement.toFront();
//                Pane pane = new Pane();
//                pane.toFront();
                  tfNomE.setText(ev.getNomEvenement());
                   tfDescriptionE.setText(ev.getDescriptionEvenement());
                   LocalDate date = ev.getDateEvenement().toLocalDate();
                DPEvenement.setValue(date);
                String Prix = Double.toString(ev.getPrixEvenement());
                tfPrixE.setText(Prix);
                tfArtisteE.setText(ev.getArtiste());
            });
       }
      
  }

    @FXML
    private void modifierE(ActionEvent event) {
        DPEvenement.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue == null) {
                    // Si la date est nulle, ne pas valider
                    return;
                }
                // Vérifier que la date est valide
                Date date= Date.valueOf(DPEvenement.getValue());
                if (!isValidDate(date)) {
                    // Afficher un message d'erreur si la date est invalide
                    
                    Alert alert= new Alert(Alert.AlertType.ERROR, "Date invalide !",ButtonType.OK);
                    alert.showAndWait();
                    // Réinitialiser la date sélectionnée à la valeur précédente
                    DPEvenement.setValue(oldValue);
                }
               
                 String Prix = tfPrixE.getText();
                 double PrixE= Double.parseDouble(Prix);
                 ServiceEvenement se = new ServiceEvenement();
                Evenement e = new Evenement(tfNomE.getText(), tfDescriptionE.getText(),tfArtisteE.getText(),date ,PrixE);
                se.modifier(e);
             
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement updated !", ButtonType.OK);
                a.showAndWait();
                
            }
        }); 
        
    }
    
}
