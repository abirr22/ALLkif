///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gui;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import entities.produits;
//import entities.sale;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.paint.Color;
//import services.MyListener;
//import services.ProductService;
//import services.SaleService;
//
///**
// * FXML Controller class
// *
// * @author ABDOU
// */
//public class PuController implements Initializable {
//    
//    SaleService ss= new SaleService();
//    ProductService ps = new ProductService();
//    @FXML
//    private ImageView photo;
//    @FXML
//    private Label tritremusic;
//    @FXML
//    private Label singleid;
//    
//    public String idsngl;
//    private produits produit;
//    private MyListener myListener;
//    @FXML
//    private Label prixlabel;
//    @FXML
//    private Label solde;
//    
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//    public void setData(produits p, MyListener myListener) {
//        this.produit = p;
//        this.myListener = myListener;
//        tritremusic.setText(p.getProduct_name());
//        String newid=String.valueOf(p.getId_product());  
//        ObservableList<sale>  l3 = FXCollections.observableArrayList();
//        ResultSet resultSet3 =ss.SelectionnerSingle(p.getId_product());
//                            try {
//                                while (resultSet3.next()){
//                                    l3.add(new  sale(
//                                            resultSet3.getInt("id_solde"),
//                                            resultSet3.getInt("taux"),
//                                            resultSet3.getInt("id_produit")));  
//                                }
//                            } catch (SQLException ex) {
//                                Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                            if(l3.size()>0){
//                                sale s =l3.get(0);
//                                String newprix=String.valueOf(p.getProduct_price()-(p.getProduct_price()*s.getTaux())/100);    
//                                singleid.setText(newid);
//                                newprix += " TND";
//                                prixlabel.setTextFill(Color.color(1, 0, 0));
//                                prixlabel.setText(newprix);
//                                String soldetxt=String.valueOf(s.getTaux());    
//                                solde.setText(soldetxt+"% OFF");
//                            }else{
//                                String newprix=String.valueOf(p.getProduct_price());    
//                                singleid.setText(newid);
//                                newprix += " TND";
//                                prixlabel.setText(newprix);
//                            }
//        Image image = new Image(getClass().getResourceAsStream(p.getProduct_photo()));
//        photo.setImage(image);
//    }
//
//    @FXML
//    private void singleprod(MouseEvent mouseEvent) {
//        idsngl=singleid.getText();
//        int idsingletmp=Integer.parseInt(idsngl);
//        myListener.onClickListener(produit);
//    }
//
//}
