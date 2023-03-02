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
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

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

    /**
     * Initializes the controller class.
     */
      ServiceEvenement se= new ServiceEvenement();
      
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
}
