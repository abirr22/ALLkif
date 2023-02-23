/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.gluonhq.impl.charm.a.b.b.s;
import java.net.URL;
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

    /**
     * Initializes the controller class.
     */
    private TilePane tilePane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for(int i=0; i<10; i++) {
            Label lblDescription = new Label( "description");
         ImageView imgView = new ImageView("../images/img1.jpg");
      imgView.setFitWidth(75);
      imgView.setFitHeight(100);

VBox vbox = new VBox();
vbox.getChildren().add(imgView);
vbox.getChildren().add(lblDescription);

tilePane.getChildren().add(vbox);
          
            
        }
        
    }    
    
}
