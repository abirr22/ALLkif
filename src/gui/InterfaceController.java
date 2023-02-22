/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.produits;
import services.ProductService;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.geometry.Insets;

/**
 * FXML Controller class
 *
 * @author ABDOU
 */
public class InterfaceController implements Initializable {
    
    
    ProductService sp = new ProductService();
    produits  tmpp = new produits();
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
    private TextField tftitre;
    @FXML
    private TextField tfphoto;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfprix;
    @FXML
    private TableView<produits> display;
    @FXML
    private TableColumn<produits,String> titre;
    @FXML
    private TableColumn<produits,String> Description;
    @FXML
    private TableColumn<produits,String> photo;
    @FXML
    private TableColumn<produits,String> prix;
    @FXML
    private TableColumn<produits,String> option;
    @FXML
    private Pane pn_modifierproduits;
    @FXML
    private TextField tftitre1;
    @FXML
    private TextField tfphoto1;
    @FXML
    private TextField tfdescription1;
    @FXML
    private TextField tfprix1;


    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        display ();
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
    private void addbtn(javafx.event.ActionEvent event) {
        String titre = tftitre.getText();
        String description = tfdescription.getText();
        String photo = tfphoto.getText();
        String tmpprix = tfprix.getText();
        int prix = Integer.parseInt(tmpprix);
        produits p = new produits(titre,description,photo,prix,1);
        sp.Ajouter(p);
        tftitre.clear();
        tfdescription.clear();
        tfphoto.clear();
        tfprix.clear();
        pn_mesproduits.toFront();
        display();
    }

    @FXML
    private void btnrefresh(javafx.event.ActionEvent event) {
        display ();
    }
    private void display () {
        display.getItems().clear();
        ObservableList<produits>  l = FXCollections.observableArrayList();
        ResultSet resultSet = sp.Selectionner(1);
        l.clear();   
        try {
            while (resultSet.next()){
                l.add(new  produits(
                        resultSet.getInt("id_product"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_description"),
                        resultSet.getString("product_photo"),
                        resultSet.getInt("product_price")));
                display.setItems(l);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        titre.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        Description.setCellValueFactory(new PropertyValueFactory<>("product_description"));
        photo.setCellValueFactory(new PropertyValueFactory<>("product_photo"));
        prix.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        //setCellValueFactory(new PropertyValueFactory<>("id_product"));

        
        /////////////////////////////////////////////////////////////////////////
        //add cell of button edit 
         Callback<TableColumn<produits, String>, TableCell<produits, String>> cellFoctory = (TableColumn<produits, String> param) -> {
            // make cell containing buttons
            final TableCell<produits, String> cell = new TableCell<produits, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView deditIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);


                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill:white;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill:white;"
                        );
                        deditIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill:white;"
                        );
                        deleteIcon.setOnMouseClicked((event) -> {
                            tmpp = display.getSelectionModel().getSelectedItem();
                            sp.Supprimer( tmpp.getId_product());
                            display ();
                        });
                        
                        
                        editIcon.setOnMouseClicked((event) -> {
                            tmpp = display.getSelectionModel().getSelectedItem();
                            int id= tmpp.getId_product();
                            ResultSet resultSet1 =sp.SelectionnerSingle(id);
                            try {
                                while (resultSet.next()){
                                    tmpp =new  produits(
                                            resultSet.getInt("id_product"),
                                            resultSet.getString("product_name"),
                                            resultSet.getString("product_description"),
                                            resultSet.getString("product_photo"),
                                            resultSet.getInt("product_price"));  
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            pn_modifierproduits.toFront();
                            tftitre1.setText(tmpp.getProduct_name());
                            tfdescription1.setText(tmpp.getProduct_description());
                            tfphoto1.setText(tmpp.getProduct_photo());
                            Float tmpprixx=tmpp.getProduct_price();
                            String prixx = String.valueOf(tmpprixx);  
                            tfprix1.setText(prixx);
                        });
                        deditIcon.setOnMouseClicked((event) -> {
                            tmpp = display.getSelectionModel().getSelectedItem();

                        });
                        
                        HBox managebtn = new HBox(deditIcon,deleteIcon,editIcon);
                        managebtn.setStyle("-fx-alignment:center");                
                        HBox.setMargin(deleteIcon, new Insets(6, 6, 0, 7));
                        HBox.setMargin(editIcon, new Insets(6, 6, 0, 7));
                        HBox.setMargin(deditIcon, new Insets(6, 6, 0, 7));

                        setGraphic(managebtn);
                        setText(null);

                    }
                }

            };

            return cell;
        };
        option.setCellFactory(cellFoctory);
    }

    @FXML
    private void updatebtn(javafx.event.ActionEvent event) {
        String titre = tftitre1.getText();
        String description = tfdescription1.getText();
        String photo = tfphoto1.getText();
        String tmpprix = tfprix1.getText();
        Float prix = Float.parseFloat(tmpprix);
        sp.Modifier(tmpp.getId_product(),titre,description,photo,prix);
        tftitre1.clear();
        tfdescription1.clear();
        tfphoto1.clear();
        tfprix1.clear();
        pn_mesproduits.toFront();
        display();
    }
}
