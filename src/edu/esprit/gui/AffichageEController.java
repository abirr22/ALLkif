/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.gluonhq.impl.charm.a.b.b.s;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.esprit.entities.Evenement;
import edu.esprit.services.ServiceEvenement;
import edu.esprit.gui.EvenementFXMLController;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class AffichageEController implements Initializable {

    @FXML
    private Label tfNomE;
    @FXML
    private Label tfDateE;
    @FXML
    private Label tfDescriptionE;
    @FXML
    private Label tfArtisteE;
    private Evenement evenement;
        private Evenement evenementD;


    /**
     * Initializes the controller class.
     */
      ServiceEvenement se= new ServiceEvenement();
    @FXML
    private Button btnDetail;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }   
    
     public void setData(Evenement e) {
         
         this.evenement=e;
          tfNomE.setText(e.getNomEvenement());
        String newid=String.valueOf(e.getIdEvenement()); 
        List<Evenement> ev =   se.getAll();
         
//        if(ev.size()>0){
//                                Evenement event =ev.get(0);  
//                                singleid.setText(newid);
//                               // newprix += " TND";
//                              //  prixlabel.setTextFill(Color.color(1, 0, 0));
//                               // prixlabel.setText(newprix);
//                                
//                               
//                            }else{
//                                String newprix=String.valueOf(p.getProduct_price());    
//                                singleid.setText(newid);
//                                newprix += " TND";
//                                prixlabel.setText(newprix);
//    

       tfDescriptionE.setText(e.getDescriptionEvenement());
       tfArtisteE.setText(e.getArtiste());
//       Date maDate = e.getDateEvenement();
//        String myFormattedDate = maDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
//  myLabel.setText(myFormattedDate);
//       tfDateE.setText(e.getDateEvenement());
         
       Date date= e.getDateEvenement();
       DateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd");
       String strDate= dateFormat.format(date);
       tfDateE.setText(strDate);
       
}
public void Detail(Evenement ev){
    this.evenementD=ev;
     btnDetail.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
               
                    Label lblNom = new Label(ev.getNomEvenement());
            Label lblDescription = new Label(ev.getDescriptionEvenement());
            Label lblArtiste = new Label(ev.getArtiste());
           Label lblDate = new Label(ev.getDateEvenement().toString());
             String Prix= Double.toString(ev.getPrixEvenement());
              Label lblPrix = new Label(Prix);
              String NbrePMax = Integer.toString(ev.getNbreParticipantMax());
               Label lblNbrePMax = new Label(NbrePMax);
                VBox vbox1 = new VBox();
             vbox1.getChildren().add(lblNom);
              vbox1.getChildren().add(lblDescription);
              vbox1.getChildren().add(lblArtiste);
              vbox1.getChildren().add(lblDate);
             vbox1.getChildren().add(lblPrix);
              vbox1.getChildren().add(lblNbrePMax);
            
      }
           });
}


    @FXML
    private void btnAvis(ActionEvent event) {
        
        
       
         try {
        
            Parent page1 = FXMLLoader.load(getClass().getResource("EvenementFXML.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


            } catch (IOException ex) {
                Logger.getLogger(EvenementFXMLController.class.getName()).log(Level.SEVERE,null, ex);


            }
    }
    
    
}
