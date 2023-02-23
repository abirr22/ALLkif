/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Publications;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import java.awt.Button;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import jdk.nashorn.internal.runtime.options.Options;
import services.ServicePublication;



/**
 * FXML Controller class
 *
 * @author khedi
 */
public class InterfacePubController implements Initializable {
    ServicePublication sp = new ServicePublication();
    Publications tmp = new Publications();
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
    private TextField tf_TitrePub;
    @FXML
    private TextArea ta_DescPub;
    @FXML
    private TextField tf_PhotoPub;
    @FXML
    private TableView<Publications> display_blog;
    @FXML
    private TableColumn<Publications,String> datte_pub;
    @FXML
    private TableColumn<Publications,String> titre_pub;
    @FXML
    private TableColumn<Publications,String> desc_pub;
    @FXML
    private TableColumn<Publications,String> photo_pub;
    @FXML
    private TableColumn<Publications,String> option_pub;
    @FXML
    private Pane pn_modifierblog;
    @FXML
    private TextField tf_TitrePub1;
    @FXML
    private TextArea ta_DescPub1;
    @FXML
    private TextField tf_PhotoPub1;
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display_blog();
    
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
    private void btn_AjoutPub(javafx.event.ActionEvent event) {
        String titre_pub = tf_TitrePub.getText();
        String texte_pub = ta_DescPub.getText();
        String photo_pub = tf_PhotoPub.getText();
        Publications p = new Publications(titre_pub, texte_pub, photo_pub);
        sp.ajouter(p);
        tf_TitrePub.clear();
        ta_DescPub.clear();
        tf_PhotoPub.clear();
        pn_mesblog.toFront();
        display_blog();
    }

    private void display_blog() {
        display_blog.getItems().clear();
        ObservableList<Publications>  l = FXCollections.observableArrayList();
        ResultSet resultSet = sp.Selection();
        l.clear();   
        try {
            while (resultSet.next()){
                l.add(new  Publications(
                        resultSet.getInt("id_pub"),
                        resultSet.getString("titre_pub"),
                        resultSet.getString("texte_pub"),
                        resultSet.getString("photo_pub"),
                        resultSet.getDate("date_pub")));
                display_blog.setItems(l);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfacePubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        titre_pub.setCellValueFactory(new PropertyValueFactory<>("titre_pub"));
        desc_pub.setCellValueFactory(new PropertyValueFactory<>("texte_pub"));
        photo_pub.setCellValueFactory(new PropertyValueFactory<>("photo_pub"));
        datte_pub.setCellValueFactory(new PropertyValueFactory<>("date_pub"));

        //setCellValueFactory(new PropertyValueFactory<>("id_product"));
        
    
    //add cell of button edit 
    Callback<TableColumn<Publications, String>, TableCell<Publications, String>> cellFoctory = (TableColumn<Publications, String> param) -> {
        // make cell containing buttons
            final TableCell<Publications, String> cell = new TableCell<Publications, String>() {
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
                                + "-fx-fill:black;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill:black;"
                        );
                        deditIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:20px;"
                                + "-fx-fill:black;"
                        );
                        deleteIcon.setOnMouseClicked((event) -> {
                            tmp = display_blog.getSelectionModel().getSelectedItem();
                            sp.supprimer(tmp.getId());
                            display_blog();
                        });
                        
                        editIcon.setOnMouseClicked((event) -> {
                            tmp = display_blog.getSelectionModel().getSelectedItem();
                            int id= tmp.getId();
                            ResultSet resultSet1 =sp.SelectionOne(id);
                            try {
                                while (resultSet.next()){
                                    tmp =new  Publications(
                                            resultSet.getInt("id_pub"),
                                            resultSet.getString("titre_pub"),
                                            resultSet.getString("texte_pub"),
                                            resultSet.getString("photo_pub"),
                                            resultSet.getDate("date_pub"));
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(InterfacePubController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            pn_modifierblog.toFront();
                            tf_TitrePub1.setText(tmp.getTitre_pub());
                            ta_DescPub1.setText(tmp.getTexte_pub());
                            tf_PhotoPub1.setText(tmp.getPhoto_pub());  
                        });
                        deditIcon.setOnMouseClicked((event) -> {
                            tmp = display_blog.getSelectionModel().getSelectedItem();
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
        option_pub.setCellFactory(cellFoctory);    
    }
    
    @FXML
        private void btn_ModifPub(javafx.event.ActionEvent event) {
        String titre_pub = tf_TitrePub1.getText();
        String texte_pub = ta_DescPub1.getText();
        String photo_pub = tf_PhotoPub1.getText();
        tmp.setTitre_pub(tf_TitrePub1.getText());
        tmp.setTexte_pub(ta_DescPub1.getText());
        tmp.setPhoto_pub(tf_PhotoPub1.getText());
        
        sp.modifier(tmp);
        tf_TitrePub1.clear();
        ta_DescPub1.clear();
        tf_PhotoPub1.clear();
        pn_mesblog.toFront();
        display_blog();
        }
    

}
        

    
    
    
    
    
    
    
    
    

    
    
