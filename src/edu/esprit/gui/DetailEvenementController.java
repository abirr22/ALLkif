/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Evenement;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class DetailEvenementController implements Initializable {

    @FXML
    private Label nomE;
    @FXML
    private Label descripE;
    @FXML
    private Label prixE;
    @FXML
    private Label dateE;
    @FXML
    private Label nbrePMax;
    private Evenement evenement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void detailE(Evenement e){
        this.evenement=e;
        nomE.setText(e.getNomEvenement());
         String newid=String.valueOf(e.getIdEvenement()); 
        
         
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

       descripE.setText(e.getDescriptionEvenement());
     //  tfArtisteE.setText(e.getArtiste());
//       Date maDate = e.getDateEvenement();
//        String myFormattedDate = maDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
//  myLabel.setText(myFormattedDate);
//       tfDateE.setText(e.getDateEvenement());
         
       Date date= e.getDateEvenement();
       DateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd");
       String strDate= dateFormat.format(date);
       dateE.setText(strDate);
       
       String prix= Double.toString(e.getPrixEvenement());
       prixE.setText(prix);
        
        
        
    }
    
}
