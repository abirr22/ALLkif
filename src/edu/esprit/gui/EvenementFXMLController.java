/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.esprit.entities.AvisEvenement;
import edu.esprit.entities.Evenement;
import edu.esprit.services.ServiceAvisE;
import edu.esprit.services.ServiceEvenement;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
import javafx.embed.swing.SwingFXUtils;
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


          public static void numericOnly(final TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    field.setText(newValue.replaceAll("[^\"\\\\d*(\\\\.\\\\d*)?\"]", ""));
                }
            }   
        });
    }
          
          
    private TextField tfid;
    @FXML
    private ImageView qrcodee;

    
    
    public void qrcode(Evenement e){
         
    try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String Information = "nom : "+e.getNomEvenement()+"\n"+"description de l'évènement : "+e.getDescriptionEvenement()+"\n"+"Artiste : "+e.getArtiste()+"\n"+"Date : "+e.getDateEvenement()+"\n"+"Prix : "+e.getPrixEvenement();
            int width = 300;
            int height = 300;
            
            BufferedImage bufferedImage = null; 
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            

          //  ImageView qrc = new ImageView();
            qrcodee.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            // TODO
        } catch (WriterException ex) {
            Logger.getLogger(EvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    //numericOnly( tfPrixE);
    
    
        
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
              Button btnAvis= new Button("avis");
              VBox vbox = new VBox();
              vbox.getChildren().add(lblNom);
              vbox.getChildren().add(lblDescription);
             vbox.getChildren().add(lblArtiste);
              vbox.getChildren().add(lblDate);
              vbox.getChildren().add(btn);
              vbox.getChildren().add(btnAvis);
                
               Evenement ev = se.getOneById(e.get(i).getIdEvenement());
       tilePane.getChildren().add(vbox);
       
      
      btn.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                 
                       //                    pn_detailE.toFront();
                       ServiceAvisE sa = new ServiceAvisE();
                         List<AvisEvenement> a =   sa.getAll();
                          Label lblNom = new Label(ev.getNomEvenement());
            Label lblDescription = new Label(ev.getDescriptionEvenement());
            Label lblArtiste = new Label(ev.getArtiste());
           Label lblDate = new Label(ev.getDateEvenement().toString());
             String Prix= Double.toString(ev.getPrixEvenement());
              Label lblPrix = new Label(Prix);
                VBox vbox1 = new VBox();
             vbox1.getChildren().add(lblNom);
              vbox1.getChildren().add(lblDescription);
              vbox1.getChildren().add(lblArtiste);
              vbox1.getChildren().add(lblDate);
             vbox1.getChildren().add(lblPrix);
               pn_evenements.getChildren().clear();
                  pn_evenements.getChildren().add(vbox1);
                     
               for (int i=0; i< e.size(); i++){
               Label lblAvis = new Label(a.get(i).getAvis());
                 VBox vbox2 = new VBox();
             vbox2.getChildren().add(lblAvis);
              pn_evenements.getChildren().add(vbox2);
               }
            
                     
                       
                       
                       
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
        btnAvis.setOnAction(new EventHandler<ActionEvent>() {
               @Override
              public void handle(ActionEvent event) {
               
                  ServiceAvisE sa = new ServiceAvisE();
                 TextField tfAvis = new TextField();
                 Button btnAjouterAvis = new Button("ajouter un avis");
                  
                   btnAjouterAvis.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   
                   AvisEvenement a = new AvisEvenement(tfAvis.getText());
                         
              sa.ajouter(a);
                       Alert a1 = new Alert(Alert.AlertType.INFORMATION, "avis added !", ButtonType.OK);
            a1.showAndWait();
            
          
               
                 
               }
                  });
                    VBox vbox = new VBox();
              vbox.getChildren().add(tfAvis);
              vbox.getChildren().add(btnAjouterAvis);
       tilePane.getChildren().clear();
                  tilePane.getChildren().add(vbox);
                  
                  List<AvisEvenement> a =   sa.getAll();
                   for (int i=0; i< e.size(); i++){
               Label lblAvis = new Label(a.get(i).getAvis());
                 VBox vbox2 = new VBox();
                  vbox2.getChildren().add(lblAvis);
               Button likeButton = new Button("Like");
           Button dislikeButton = new Button("Dislike");
                              vbox2.getChildren().add(likeButton);
                                   vbox2.getChildren().add(dislikeButton);
                  tilePane.getChildren().add(vbox2);

         
     likeButton.setOnAction(new EventHandler<ActionEvent>() {
          int likeCount = 0;
                   @Override
                   public void handle(ActionEvent e) {
                       likeCount++;
                       System.out.println("Likes: " + likeCount);
                   }
               });
         
          dislikeButton.setOnAction(new EventHandler<ActionEvent>() {
              int dislikeCount = 0;
                   @Override
                   public void handle(ActionEvent e) {
                       dislikeCount++;
                       System.out.println("Dislikes: " + dislikeCount);
                   }
               });






               }
                 
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
                qrcode(e.get(i));
        
               
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
               // String sid=String.valueOf(id);
               // tfid.setText(sid);
                
           
      
        
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
        
        DPEvenement.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
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
                
            
        }); 
//        
//         Date date= Date.valueOf(DPEvenement.getValue());
//       String Prix = tfPrixE.getText();
//       double PrixE= Double.parseDouble(Prix);
//   
//       ServiceEvenement se = new ServiceEvenement();
//               Evenement e = new Evenement(tfNomE.getText(), tfDescriptionE.getText(),tfArtisteE.getText(),date ,PrixE);
//              se.ajouter(e);
//                Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement added !", ButtonType.OK);
//               a.showAndWait();
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
               // String sid=String.valueOf(id);
               // tfid.setText(sid);
                
           
      
        
              });
       }
      
  }
  private void refresh2(){
      pn_mesevenements.toFront();
       TextArea Avis = new TextArea();
        Button ajouterAvis = new Button("Ajouter un avis");
        
          ajouterAvis.setOnAction((ActionEvent event1) -> {
          
               ServiceAvisE sa = new ServiceAvisE();
               AvisEvenement a= new AvisEvenement(Avis.getText());
                   });
  }
  private void refresh3(){
        pn_evenements.toFront();
        tilePane.getChildren().clear();
        ServiceEvenement se = new ServiceEvenement();

        List<Evenement> e =   se.getAll();
       for (int i=0; i< e.size(); i++){
          
           Label lblNom = new Label(e.get(i).getNomEvenement());
            Label lblDescription = new Label(e.get(i).getDescriptionEvenement());
             Label lblArtiste = new Label(e.get(i).getArtiste());
              Label lblDate = new Label(e.get(i).getDateEvenement().toString());
              Button btn= new Button("Voir plus de détails");
              Button btnAvis= new Button("avis");
              VBox vbox = new VBox();
              vbox.getChildren().add(lblNom);
              vbox.getChildren().add(lblDescription);
              vbox.getChildren().add(lblArtiste);
              vbox.getChildren().add(lblDate);
              vbox.getChildren().add(btn);
              vbox.getChildren().add(btnAvis);
                 
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
        btnAvis.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   pn_evenements.getChildren().clear();
                  refresh2();
               }
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
               // int id= Integer.parseInt(tfid.getText());
                Evenement e = new Evenement(tfNomE.getText(), tfDescriptionE.getText(),tfArtisteE.getText(),date ,PrixE);
             //   System.out.println(id);
                se.modifier(e);
//                Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement updated !", ButtonType.OK);
//                a.showAndWait();

                 Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Modification réussie");
alert.setHeaderText(null);
alert.setContentText("Les données ont été modifiées avec succès");
alert.showAndWait();
                
            }
            
        }); 
       
         
        
    }
    
    

    
}
