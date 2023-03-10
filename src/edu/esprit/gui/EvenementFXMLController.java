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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import edu.esprit.entities.AvisEvenement;
import edu.esprit.entities.Evenement;
import edu.esprit.ressources.ImgurUploader;
import edu.esprit.ressources.SMSSender;
import static edu.esprit.ressources.SMSSender.ACCOUNT_SID;
import static edu.esprit.ressources.SMSSender.AUTH_TOKEN;
import edu.esprit.services.ServiceAvisE;
import edu.esprit.services.ServiceEvenement;
import edu.esprit.utils.DataSource;
//import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javafx.scene.paint.Color;



/**
 * FXML Controller class
 *
 * @author siwar
 */
public class EvenementFXMLController implements Initializable {
String URLImage = null;
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
    private TilePane tilePane;
    @FXML
    private TilePane tilePane2;
 Connection cnx = DataSource.getInstance().getCnx();

 public static final String ACCOUNT_SID = "ACcdf24cdc4abbf3d6d3b29419fa108968";
    public static final String AUTH_TOKEN = "8f8903e15c4556454482f63458515330";
          
    @FXML
    private ImageView qrcodee;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView fileImageView;
    @FXML
    private TextField tfnbrePMax;
    @FXML
    private TextArea tfajoutavis;
    @FXML
    private VBox vboxavis;

    
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
    @FXML
    private Pane pn_ajoutAvie;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Pane pn_static;
    @FXML
    private StackPane chartContainer;
    @FXML
    private Label labelErrorNom;
    @FXML
    private Label labelErrorPrix;
    @FXML
    private Label labelErrornbrePM;
   
    
    
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
            graphics.setBackground(java.awt.Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(java.awt.Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
           
            qrcodee.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

          //  ImageView qrc = new ImageView();
           
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
    
     private List<Evenement> event;

    @FXML
    private void btn_evenement(javafx.event.ActionEvent event) {
        pn_evenements.toFront();
     
        ServiceEvenement se = new ServiceEvenement();
         List<Evenement> e =   se.getAll();
            afficherE(e);
            
            
            
            
//         int column=0;
//         int row=0;
//         for (int i=0; i< e.size(); i++){
//               FXMLLoader fxmlloader = new FXMLLoader();
//                fxmlloader.setLocation(getClass().getResource("../gui/AffichageE.fxml"));
//            try {
//              
//                AnchorPane anchorPane= fxmlloader.load();
//                
//                AffichageEController AffichageEController= fxmlloader.getController();
//                AffichageEController.setData(e.get(i));
//
//                if (column== 2){
//                    column=0;
//                    row++;
//                }
//                                                   
//           column++;
//                 //set grid width
//                            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                            grid.setMaxWidth(Region.USE_PREF_SIZE);
//                            //set grid height
//                            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                            grid.setMaxHeight(Region.USE_PREF_SIZE);
//                            
//                            grid.add(anchorPane, column,row );
//                                GridPane.setMargin(anchorPane,new Insets(20));
//                      
//      
//            }
//            
//            catch (IOException ex) {
//                Logger.getLogger(EvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//                          int id= e.get(i).getIdEvenement();
//                          Evenement ev= se.getOneById(id);
//                      
  //  }

            
//       for (int i=0; i< e.size(); i++){
//         
//          Label lblNom = new Label(e.get(i).getNomEvenement());
//            Label lblDescription = new Label(e.get(i).getDescriptionEvenement());
//             Label lblArtiste = new Label(e.get(i).getArtiste());
//              Label lblDate = new Label(e.get(i).getDateEvenement().toString());
//              Button btn= new Button("Voir plus de détails");
//              Button btnAvis= new Button("avis");
//              VBox vbox = new VBox();
//              vbox.getChildren().add(lblNom);
//              vbox.getChildren().add(lblDescription);
//             vbox.getChildren().add(lblArtiste);
//              vbox.getChildren().add(lblDate);
//              vbox.getChildren().add(btn);
//              vbox.getChildren().add(btnAvis);
//                
//               Evenement ev = se.getOneById(e.get(i).getIdEvenement());
//       tilePane.getChildren().add(vbox);
//
//      
//      btn.setOnAction(new EventHandler<ActionEvent>() {
//               @Override
//               public void handle(ActionEvent event) {
//                 
//                       //                    pn_detailE.toFront();
//                       ServiceAvisE sa = new ServiceAvisE();
//                         List<AvisEvenement> a =   sa.getAll();
//                          Label lblNom = new Label(ev.getNomEvenement());
//            Label lblDescription = new Label(ev.getDescriptionEvenement());
//            Label lblArtiste = new Label(ev.getArtiste());
//           Label lblDate = new Label(ev.getDateEvenement().toString());
//             String Prix= Double.toString(ev.getPrixEvenement());
//              Label lblPrix = new Label(Prix);
//                VBox vbox1 = new VBox();
//             vbox1.getChildren().add(lblNom);
//              vbox1.getChildren().add(lblDescription);
//              vbox1.getChildren().add(lblArtiste);
//              vbox1.getChildren().add(lblDate);
//             vbox1.getChildren().add(lblPrix);
//               pn_evenements.getChildren().clear();
//                  pn_evenements.getChildren().add(vbox1);
//                     
//               for (int i=0; i< e.size(); i++){
//               Label lblAvis = new Label(a.get(i).getAvis());
//                 VBox vbox2 = new VBox();
//             vbox2.getChildren().add(lblAvis);
//              pn_evenements.getChildren().add(vbox2);
//               }
//
//
//
//
//                       
////                Label lblNom = new Label(ev.getNomEvenement());
////            Label lblDescription = new Label(ev.getDescriptionEvenement());
////             Label lblArtiste = new Label(ev.getArtiste());
////            Label lblDate = new Label(ev.getDateEvenement().toString());
////             String Prix= Double.toString(ev.getPrixEvenement());
////              Label lblPrix = new Label(Prix);
////              VBox vbox = new VBox();
////              vbox.getChildren().add(lblNom);
////              vbox.getChildren().add(lblDescription);
////              vbox.getChildren().add(lblArtiste);
////              vbox.getChildren().add(lblDate);
////              vbox.getChildren().add(lblPrix);
////              Pane pnDE = new Pane();
////              pnDE.getChildren().add(vbox);
////                FXMLLoader fxmlLoader = new FXMLLoader();
////                fxmlLoader.setLocation(getClass().getResource("DE.fxml"));
//              // Pane pane= fxmlLoader.load();
//
//
//
//
//           
//                   }
//           });
//        btnAvis.setOnAction(new EventHandler<ActionEvent>() {
//               @Override
//              public void handle(ActionEvent event) {
//               
//                  ServiceAvisE sa = new ServiceAvisE();
//                 TextField tfAvis = new TextField();
//                 Button btnAjouterAvis = new Button("ajouter un avis");
//                  
//                   btnAjouterAvis.setOnAction(new EventHandler<ActionEvent>() {
//               @Override
//               public void handle(ActionEvent event) {
//                   
//                   AvisEvenement a = new AvisEvenement(tfAvis.getText());
//                         
//              sa.ajouter(a);
//                       Alert a1 = new Alert(Alert.AlertType.INFORMATION, "avis added !", ButtonType.OK);
//            a1.showAndWait();
//
//
//
//                 
//               }
//                  });
//                    VBox vbox = new VBox();
//              vbox.getChildren().add(tfAvis);
//              vbox.getChildren().add(btnAjouterAvis);
//       tilePane.getChildren().clear();
//                  tilePane.getChildren().add(vbox);
//                  
//                  List<AvisEvenement> a =   sa.getAll();
//                   for (int i=0; i< e.size(); i++){
//               Label lblAvis = new Label(a.get(i).getAvis());
//                 VBox vbox2 = new VBox();
//                  vbox2.getChildren().add(lblAvis);
//               Button likeButton = new Button("Like");
//           Button dislikeButton = new Button("Dislike");
//                              vbox2.getChildren().add(likeButton);
//                                   vbox2.getChildren().add(dislikeButton);
//                  tilePane.getChildren().add(vbox2);
//
//         
//     likeButton.setOnAction(new EventHandler<ActionEvent>() {
//          int likeCount = 0;
//                   @Override
//                   public void handle(ActionEvent e) {
//                       likeCount++;
//                       System.out.println("Likes: " + likeCount);
//                   }
//               });
//         
//          dislikeButton.setOnAction(new EventHandler<ActionEvent>() {
//              int dislikeCount = 0;
//                   @Override
//                   public void handle(ActionEvent e) {
//                       dislikeCount++;
//                       System.out.println("Dislikes: " + dislikeCount);
//                   }
//               });
//
//
//
//
//
//
//               }
//                 
//               }
//       });
//      }
            
             
             
  
         
         
         
         
         
       
       

    }

    @FXML
    private void btn_blog(javafx.event.ActionEvent event) {
        pn_blog.toFront();
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
                       tilePane2.getChildren().clear();
                       qrcodee.setImage(null);
                       qrcodee.toBack();
                       pn_static.toBack();
                        tilePane2.toFront();

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
                 Button btnP= new Button("Participer");
//                qrcode(e.get(i));
          lblNom.setTextFill(Color.WHITE);
                    lblDescription.setTextFill(Color.WHITE);
          lblArtiste.setTextFill(Color.WHITE);
          lblDate.setTextFill(Color.WHITE);

               
              VBox vbox = new VBox();
              vbox.getChildren().add(lblNom);
              vbox.getChildren().add(lblDescription);
              vbox.getChildren().add(lblArtiste);
              vbox.getChildren().add(lblDate);
              vbox.getChildren().add(btn);
              vbox.getChildren().add(btnS);
              vbox.getChildren().add(btnM);
              vbox.getChildren().add(btnP);
                
              tilePane2.getChildren().add(vbox);
           
              int id= e.get(i).getIdEvenement();
              Evenement ev= se.getOneById(id);
         // } 
         
          
         
           btn.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
               
//                          Label lblNom = new Label(ev.getNomEvenement());
//            Label lblDescription = new Label(ev.getDescriptionEvenement());
//            Label lblArtiste = new Label(ev.getArtiste());
//           Label lblDate = new Label(ev.getDateEvenement().toString());
//             String Prix= Double.toString(ev.getPrixEvenement());
//              Label lblPrix = new Label(Prix);
//              String NbrePMax = Integer.toString(ev.getNbreParticipantMax());
//               Label lblNbrePMax = new Label(NbrePMax);
//                VBox vbox1 = new VBox();
//             vbox1.getChildren().add(lblNom);
//              vbox1.getChildren().add(lblDescription);
//              vbox1.getChildren().add(lblArtiste);
//              vbox1.getChildren().add(lblDate);
//             vbox1.getChildren().add(lblPrix);
//              vbox1.getChildren().add(lblNbrePMax);
//               tilePane2.getChildren().clear();
//                  tilePane2.getChildren().add(vbox1);
       tilePane2.getChildren().clear();
       tilePane2.toBack();
      qrcodee.toFront();
                qrcode(ev);
        

                     
             




                       
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
          btnS.setOnAction((ActionEvent event1) -> {
              se.supprimer(id);
              tilePane2.getChildren().clear();
              refresh();
               Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement deleted !", ButtonType.OK);
                a.showAndWait();
              });
            btnM.setOnAction((ActionEvent event1) -> {
                                    System.out.println("test3");

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
                String nbrePMax = Integer.toString(ev.getNbreParticipantMax());
                tfnbrePMax.setText(nbrePMax);
               // String sid=String.valueOf(id);
               // tfid.setText(sid);
              });
            btnP.setOnAction((ActionEvent event1) -> {
                int nbreP=ev.getNbreParticipant();
                nbreP++;
                ev.setNbreParticipant(nbreP);
                  try {
               String req = " UPDATE `evenement` SET `nbreParticipant`='" + ev.getNbreParticipant() + "'  WHERE `evenement`.`idEvenement` = " + ev.getIdEvenement();
            Statement st = cnx.createStatement();
             st.executeUpdate(req);
            System.out.println("Votre demande est en cours de traitement !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+21690001536"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "merci pour votre participation , votre demande est prise en charge et en cours de traitement.")
                .create();
              SMSSender.sendSMS("votre demande de participation a été approuvée");
               Alert a = new Alert(Alert.AlertType.INFORMATION, "participation enregistrée !", ButtonType.OK);
                a.showAndWait();
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
            ServiceEvenement se = new ServiceEvenement();
            String nbreMax = tfnbrePMax.getText();
            int nbrePMax= Integer.parseInt(nbreMax);
            String Prix = tfPrixE.getText();
           try{
                           double PrixE= Double.parseDouble(Prix);
                                       Evenement e = new Evenement(tfNomE.getText(), tfDescriptionE.getText(),tfArtisteE.getText(),date ,PrixE, nbrePMax);
                     se.ajouter(e);
                          
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Evenement added !", ButtonType.OK);
            a.showAndWait();
                


           } catch (NumberFormatException e){
               
               labelErrorPrix.setText("Saisir des chiffres seulement!");
               
           }
    
            
       
            
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
                    System.out.println("test1");

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
                                   System.out.println("test2");

                String Prix = tfPrixE.getText();
                 double PrixE= Double.parseDouble(Prix);
                 ServiceEvenement se = new ServiceEvenement();
               // int id= Integer.parseInt(tfid.getText());
               String nbreMax = tfnbrePMax.getText();
            int nbrePMax= Integer.parseInt(nbreMax);
 Evenement e = new Evenement(tfNomE.getText(), tfDescriptionE.getText(),tfArtisteE.getText(),date ,PrixE, nbrePMax); 
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
       
       System.out.println("test4");   
        
    }

    @FXML
    private void FileBox(MouseEvent event) {
        
         URLImage = ImgurUploader.chooseImage();
        File file = new File("../images/img1.jpg");
        Image img = new Image(file.toURI().toString());
        fileImageView.setImage(img);
    }

    @FXML
    private void ajouterAvis(ActionEvent event) {
        ServiceAvisE sa= new ServiceAvisE();
         AvisEvenement a = new AvisEvenement( tfajoutavis.getText());
            sa.ajouter(a);
            
            Alert alertavis = new Alert(Alert.AlertType.INFORMATION, "avis added !", ButtonType.OK);
            alertavis.showAndWait();
           
          
          List<AvisEvenement> av =   sa.getAll();
          
       for (int i=0; i< av.size(); i++){
//            Label lblNom2 = new Label(e.get(i).getNomEvenement());
//            tilePane2.getChildren().add(lblNom2);
          // String Art= "Si flen";
          //String Artiste=e.get(i).getArtiste();
     
              Label lblAvis = new Label(av.get(i).getAvis());
               Button btnS= new Button("Supprimer évènement");       
              VBox vbox = new VBox();
              vbox.getChildren().add(lblAvis);
              vbox.getChildren().add(btnS);
              vboxavis.getChildren().add(vbox);
           
    
         
          int id= av.get(i).getIdAvisE();
             
         
          btnS.setOnAction((ActionEvent event1) -> {
              sa.supprimer(id);
              tilePane2.getChildren().clear();
              refresh();
               Alert a2 = new Alert(Alert.AlertType.INFORMATION, "Avis deleted !", ButtonType.OK);
                a2.showAndWait();
              });

        
    }

    
    

    }  

    @FXML
    private void gofb(ActionEvent event) {
                 String url = "https://www.facebook.com/events/945770306578494/?acontext=%7B%22event_action_history%22%3A[%7B%22extra_data%22%3A%22%22%2C%22mechanism%22%3A%22left_rail%22%2C%22surface%22%3A%22bookmark%22%7D%2C%7B%22extra_data%22%3A%22%22%2C%22mechanism%22%3A%22surface%22%2C%22surface%22%3A%22create_dialog%22%7D]%2C%22ref_notif_type%22%3Anull%7D";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
   private ChartPanel weightChartPanel;

    @FXML
    private void stat(ActionEvent event) {
      tilePane2.getChildren().clear();
        pn_static.toFront();
       
      ServiceEvenement se= new ServiceEvenement();
        
         List<Evenement> e = se.getAll();
        // Compute statistics for weight and material
        Map<String, Float> weightStats = e.stream()
                .collect(Collectors.groupingBy(Evenement::getNomEvenement, Collectors.summingInt(Evenement::getNbreParticipant)))
                .entrySet().stream().collect(Collectors.toMap(ev -> ev.getKey(), ev -> ev.getValue().floatValue()));
        // Create dataset for weight chart
        DefaultCategoryDataset weightDataset = new DefaultCategoryDataset();
        weightStats.forEach((material, weight) -> weightDataset.setValue(weight, "nbreParticipant", material));
        // Create chart for weight statistics
        JFreeChart weightChart = ChartFactory.createBarChart("Evenements Statistics", "nomEvenement", "nbreParticipant", weightDataset);
        weightChartPanel = new ChartPanel(weightChart);
        // Create chart panel and add to container
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(weightChartPanel);
       chartContainer.getChildren().add(swingNode);    }

    @FXML
    private void btn_deconnecter(ActionEvent event) {
    }
    
    
      public void afficherE(List<Evenement> evenements) {
       

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
     vBox.setBackground(new Background(new BackgroundFill(Color.rgb(3, 27, 3), CornerRadii.EMPTY, Insets.EMPTY)));    

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(100);//
        //hBox.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: black; -fx-border-width: 2px;");

        int count = 0;
        for (Evenement e : evenements) {
            VBox box = createEBox(e);
            box.setPrefWidth(300);
            box.setPrefHeight(300);
            hBox.getChildren().add(box);
            count++;

            if (count == 3) {
                vBox.getChildren().add(hBox);
                hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(100); // 
                // hBox.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: black; -fx-border-width: 2px;");
                count = 0;
            }
        }

        if (count > 0) {
            vBox.getChildren().add(hBox);
        }

        scroll.setContent(vBox);
        scroll.setFitToWidth(true);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    private VBox createEBox(Evenement e) {
        
        ServiceEvenement se = new ServiceEvenement();
        VBox box = new VBox();
        box.setStyle("-fx-background-color: #BB0000; -fx-background-radius: 20;");

        box.setMaxSize(500, 500);
        box.setSpacing(50);
        box.setAlignment(Pos.CENTER);

        Insets I = new Insets(20, 0, 20, 0);
        box.setPadding(I);
        box.setOpacity(0.7);
        Glow glow = new Glow();
        DropShadow shadow = new DropShadow();
        box.setEffect(shadow);
        box.setUserData(e.getIdEvenement()); // set the ID as the user data for the VBox

        Label nom = new Label(e.getNomEvenement());
      
        Label descr = new Label(e.getDescriptionEvenement());
       
        Label prix = new Label(Double.toString(e.getPrixEvenement()) + " TND");

        nom.setStyle("-fx-text-fill: #acaaad;");
        nom.setWrapText(true);

        nom.setAlignment(Pos.CENTER);
        descr.setTextFill(Color.WHITE);
        descr.setFont(Font.font("Arial", FontWeight.BOLD, 19));
        prix.setFont(Font.font("Arial", FontWeight.BOLD, 19));
        prix.setTextFill(Color.WHITE);
        nom.setFont(Font.font("Arial", FontWeight.BOLD, 24));
     
        box.getChildren().addAll(nom, descr, prix);

//        box.setOnMouseClicked(event -> { // Mouse click
//            idEvenement = e.getIdEvenement();
//            pn_evenements.getChildren().clear();
//            FXMLLoader loadOffre = new FXMLLoader(getClass().getResource("/OffresGUI/OffrePage.fxml"));
//
//            try {
//
//                pn_evenements.getChildren().add(loadOffre.load());
//            } catch (IOException ex) {
//                ex.getMessage();
//            }
//
//        });

        box.setOnMouseEntered(event -> {
              box.setStyle("-fx-background-color: #acaaad; -fx-background-radius: 40;");
            box.setOpacity(0.78);
            nom.setStyle("-fx-text-fill: #BB0000;");
            Color c = new Color(0, 0, 0, 1);
            descr.setTextFill(c);
            prix.setTextFill(c);
            box.setEffect(glow);
        });

        box.setOnMouseExited(event -> {
                  box.setStyle("-fx-background-color: #BB0000; -fx-background-radius: 20;");
            box.setOpacity(0.7);
            nom.setStyle("-fx-text-fill: #acaaad;");
            descr.setTextFill(Color.WHITE);
            prix.setTextFill(Color.WHITE);
            box.setEffect(shadow);
        });

        return box;
    }
    public void participer(){
        String req="SELECT COUNT(*) FROM evenement WHERE dateEvenement BETWEEN DATE_SUB('2023-03-10', INTERVAL 4 DAY) AND '2023-03-10'";
    }
}
