/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import entities.produits;
import services.MyListener;
import services.ProductService;

/**
 * FXML Controller class
 *
 * @author ABDOU
 */
public class ProduitController implements Initializable {
    
    
    ProductService ps = new ProductService();
    @FXML
    private ImageView photo;
    @FXML
    private Label tritremusic;
    @FXML
    private Label singleid;
    
    public String idsngl;
    private produits produit;
    private MyListener myListener;
    @FXML
    private Label prixlabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(produits p, MyListener myListener) {
        this.produit = p;
        this.myListener = myListener;
        tritremusic.setText(p.getProduct_name());
        String newid=String.valueOf(p.getId_product());   
        String newprix=String.valueOf(p.getProduct_price());    
        singleid.setText(newid);
        newprix += " TND";
        prixlabel.setText(newprix);
        Image image = new Image(getClass().getResourceAsStream(p.getProduct_photo()));
        photo.setImage(image);
    }

    @FXML
    private void singleprod(MouseEvent mouseEvent) {
        idsngl=singleid.getText();
        int idsingletmp=Integer.parseInt(idsngl);
        myListener.onClickListener(produit);
    }

}
