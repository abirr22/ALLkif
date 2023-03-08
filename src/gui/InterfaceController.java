/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.produits;
import entities.sale;
import services.ProductService;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.control.ScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import gui.ProduitController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import services.MyListener;
import test.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import services.SaleService;
import javax.sound.sampled.*;
import Utils.SendMail;
import static com.sun.javafx.animation.TickCalculation.sub;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static java.lang.ProcessBuilder.Redirect.to;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.AuthentificationService;
import API.MailerAPI;
import Entities.User;
import Utils.UserSession;
import com.gluonhq.charm.glisten.control.Icon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import services.ServiceUser;
import utils.MyConnection;
//import javafx.event.ActionEvent;
//import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ABDOU
 */
public class InterfaceController implements Initializable {
    
    
    ProductService sp = new ProductService();
    ServiceUser su = new ServiceUser();
    SaleService ss = new SaleService();
    produits  tmpp = new produits();
    AuthentificationService authentificationService =new AuthentificationService() ;
    private Image image;
    private UserSession userSession;
    public static final String ACCOUNT_SID = "AC652f43806e3fbc03f53fccd5fdaa9212";
    public static final String AUTH_TOKEN = "2b9eb9158e11e5cbaede12228616354b";
    private MyListener myListener;
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
    @FXML
    private GridPane grid;
    @FXML
    public Pane pn_singleprod;
    @FXML
    private ImageView imgsingle;
    @FXML
    private Label titresingle;
    @FXML
    private TextArea descriptionsingle;
    @FXML
    private Label prixsingle;
    @FXML
    private Label titresingle1;
    @FXML
    private TextField tf_recherche;
    @FXML
    private AnchorPane main;
    @FXML
    private Pane pn_signup;
    @FXML
    private TextField input_firstName;
    @FXML
    private TextField input_lastName;
    @FXML
    private TextField input_username;
    @FXML
    private TextField input_email;
    @FXML
    private TextField input_phone_number;
    @FXML
    private TextField input_gender;
    @FXML
    private PasswordField input_password;
    @FXML
    private javafx.scene.control.Button btn_signup;
    @FXML
    private javafx.scene.control.Button signup;
    @FXML
    private TextField txtuname;
    @FXML
    private PasswordField txtpass;
    @FXML
    private javafx.scene.control.Button btnok;
    @FXML
    private javafx.scene.control.Button ForgetPassword;
    @FXML
    private Pane pn_login;
    @FXML
    private javafx.scene.control.Button btn_signup1;
    @FXML
    private Pane pn_captcha;
    @FXML
    private Label captchahere;
    @FXML
    private Label messagebtn;
    @FXML
    private TextField textarea;
    @FXML
    private javafx.scene.control.Button checkbtn;
    @FXML
    private javafx.scene.control.Button newcaptchabtn;
    @FXML
    private javafx.scene.control.Button btnLogin;
    @FXML
    private Label llname;
    @FXML
    private Label lrole;
    @FXML
    private Label loption;
    @FXML
    private javafx.scene.control.Button btn_mesproduit;
    @FXML
    private javafx.scene.control.Button btn_ajouterproduit;
    @FXML
    private javafx.scene.control.Button btn_mesevent;
    @FXML
    private javafx.scene.control.Button btn_ajouterevent;
    @FXML
    private javafx.scene.control.Button btn_mesblog;
    @FXML
    private javafx.scene.control.Button btn_ajouterblog;
    @FXML
    private javafx.scene.control.Button btn_mescommandes;
    @FXML
    private javafx.scene.control.Button btn_listuser;
    @FXML
    private Pane pn_listuser;
    @FXML
    private TableView<User> afficher;
    @FXML
    private TableColumn<User, String> id_userColumn;
    @FXML
    private TableColumn<User, String> first_nameColumn;
    @FXML
    private TableColumn<User, String> last_nameColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> phone_numberColumn;
    @FXML
    private TableColumn<User, String> genderColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    @FXML
    private TextField lfname;
    @FXML
    private TextField llname1;
    @FXML
    private TextField lusername;
    @FXML
    private TextField lemail;
    @FXML
    private TextField lphone_number;
    @FXML
    private TextField lgender;
    @FXML
    private TextField lrole1;
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {   
                //User u=su.afficher(6);
                //User u=su.getALL();
                //User u=su.afficher("abir",);
                ServiceUser userService = new ServiceUser();
                //changer_id.setPromptText("changer type utilisateur");
                id_userColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));
                first_nameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
                last_nameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
                usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
                passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
                emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
                phone_numberColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
                genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
                roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
                // récupère les données des utilisateurs depuis la base de données
                List<User> userList = userService.getAll();
                // affiche les données dans le tableau
                afficher.getItems().setAll(userList);
                display ();
                display2();
                recherche_avance();
                pn_login.toFront();
                /*
        try {
            player();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
                */
    }

    @FXML
    private void btn_boutique(javafx.event.ActionEvent event) {
        display2();
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
        this.userSession.cleanUserSession();
        txtpass.clear();
        txtuname.clear();
        pn_login.toFront();
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
        if (tftitre.getText().isEmpty() || tfdescription.getText().isEmpty() || tfphoto.getText().isEmpty() || 
            tfprix.getText().isEmpty()  ) {
        // Afficher un message d'alerte
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs manquants");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return;
        }
        String emailRegex = "^[1-9][0-9]?$|^100$";
        if (!tfprix.getText().matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Format prix incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un prix numerique !");
            alert.showAndWait();
            return;
        }
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
                            ObservableList<sale>  l3 = FXCollections.observableArrayList();
                            ResultSet resultSet3 =ss.SelectionnerSingle(tmpp.getId_product());
                            try {
                                while (resultSet3.next()){
                                    l3.add(new  sale(
                                            resultSet3.getInt("id_solde"),
                                            resultSet3.getInt("taux"),
                                            resultSet3.getInt("id_produit")));  
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            String taux="pas de solde";
                            if(l3.size()>0){
                                sale tmpss = l3.get(0);
                                int tauxint = tmpss.getTaux();
                                taux=String.valueOf(tauxint);
                            }
                            TextInputDialog td = new TextInputDialog("");
                            td.setTitle("Ajouter promotion");
                            td.setContentText("Entrez le taux de promotion");
                            td.setHeaderText("promotion actuel: "+taux);
                            Optional<String> result = td.showAndWait();
                            result.ifPresent(reponse -> {
                                String emailRegex = "^[0-9][0-9]?$|^100$";
                                if (!reponse.matches(emailRegex)) {
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setTitle("Format prix incorrect");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Veuillez saisir un taux numerique entre 0 et 100 !");
                                    alert.showAndWait();
                                    return;
                                }
                                if(l3.size()==0){
                                        sale tmpsale = new sale(Integer.parseInt(reponse),tmpp.getId_product());
                                        ss.Ajouter(tmpsale);
                                        System.out.println(reponse);
                                }else{
                                    if(Integer.parseInt(reponse)!=0){
                                        ss.Modifier(Integer.parseInt(reponse),tmpp.getId_product());
                                        System.out.println(reponse);
                                    }else{
                                        ss.Supprimer(tmpp.getId_product());
                                    }
                                }
                            });
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
        if (tftitre1.getText().isEmpty() || tfdescription1.getText().isEmpty() || tfphoto1.getText().isEmpty() || 
            tfprix1.getText().isEmpty()  ) {
        // Afficher un message d'alerte
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs manquants");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return;
        }
        String emailRegex = "^[1-9][0-9]?$|^100$";
        if (!tfprix1.getText().matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Format prix incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un prix numerique !");
            alert.showAndWait();
            return;
        }
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
    private Node getNodeFromGridPane(GridPane gridPane) {
    for (Node node : gridPane.getChildren()) {
        System.out.println(GridPane.getColumnIndex(node));
        System.out.println(GridPane.getRowIndex(node));
        /*
        if (GridPane.getColumnIndex(node) ==  && GridPane.getRowIndex(node) == row) {
            return node;
        }
        */
        return node;
    }
    return null;
    }
    
    private void setChosenprod(produits produit) {
        titresingle.setText(produit.getProduct_name());
        String newprix=String.valueOf(produit.getProduct_price());    
        newprix += " TND";
        prixsingle.setText(newprix);
        descriptionsingle.setText(produit.getProduct_description());
        Image imageee = new Image(getClass().getResourceAsStream(produit.getProduct_photo()));
        imgsingle.setImage(imageee);   
        pn_singleprod.toFront();
    }
    
    private void display2(){
        ///////////////////////////////////////////////////////////////
                ObservableList<produits>  l2 = FXCollections.observableArrayList();
                ResultSet resultSet2 = sp.Getall();
                l2.clear(); 
                produits pppp = new produits("","","/img/Capture.PNG",1);
                l2.add(pppp);
                int column =0;
                int row =2;
                if (l2.size() > 0) {
                setChosenprod(l2.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(produits produit) {
                        setChosenprod(produit);
                    }
                };
                }
                try {
                    while (resultSet2.next()){
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/gui/produit.fxml"));
                        try {
                            AnchorPane anchorPane = fxmlLoader.load();
                            ProduitController itemController = fxmlLoader.getController();
                            String titre =resultSet2.getString("product_name");
                            String photo =resultSet2.getString("product_photo");  
                            String id =resultSet2.getString("id_product");
                            String prix =resultSet2.getString("product_price");
                            String desc =resultSet2.getString("product_description");
                            float newprix=Integer.parseInt(prix);
                            int newid=Integer.parseInt(id);
                            produits ppppp = new produits(newid,titre,desc,photo,newprix);
                            itemController.setData(ppppp,myListener);
                            if (column == 5) {
                                column = 0;
                                row++;
                            }
                            grid.add(anchorPane, column++, row); //(child,column,row)
                            //set grid width
                            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                            grid.setMaxWidth(Region.USE_PREF_SIZE);
                            //set grid height
                            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            grid.setMaxHeight(Region.USE_PREF_SIZE);
                            GridPane.setMargin(anchorPane, new Insets(10));
                        } catch (IOException ex) {
                            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }   
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    
    
    public void recherche_avance() {
        try {
            ObservableList<produits>  listL = FXCollections.observableArrayList();
            ResultSet resultSet = sp.Selectionner(1);   
            listL.clear();
            while (resultSet.next()){
                listL.add(new  produits(
                        resultSet.getInt("id_product"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_description"),
                        resultSet.getString("product_photo"),
                        resultSet.getInt("product_price")));
                System.out.println("*****************");
                FilteredList<produits> filtereddata = new FilteredList<>(listL, b -> true);
                System.out.println(tf_recherche.getText());
                tf_recherche.textProperty().addListener((observable, oldvalue, newValue) -> {
                    filtereddata.setPredicate((produits produits) -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowercasefilter = newValue.toLowerCase();
                        if (produits.getProduct_name().toLowerCase().indexOf(lowercasefilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }
                        
                    });
                    
                });
                //System.out.println(filtereddata);
                SortedList<produits> sorteddata = new SortedList<>(filtereddata);
                sorteddata.comparatorProperty().bind(display.comparatorProperty());
                display.setItems(filtereddata);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cherch(InputMethodEvent event) {
        recherche_avance();
    }

    private void look(javafx.event.ActionEvent event) {
        recherche_avance();
    }

    @FXML
    private void pdf(javafx.event.ActionEvent event) {
        try {
            genererPDF(sp, main.getScene().getWindow());
        } catch (DocumentException ex) {
            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void genererPDF(ProductService st, Window parentWindow) throws DocumentException, FileNotFoundException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Enregistrer les données");
    File selectedFile = fileChooser.showSaveDialog(parentWindow);
 
    if (selectedFile != null) {
        try {
            // Créer un document PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
            document.open();
 
            // Ajouter les éléments de l'interface utilisateur 
            com.itextpdf.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Liste des produits", titleFont);
            //Image img = Image.getInstance("C:/Users/ABDOU/Desktop/fold/hors kraya/pic/7.jpg");
            
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10f);
            document.add(title);
 
            com.itextpdf.text.Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Paragraph date = new Paragraph("Date: " + LocalDate.now().toString(), regularFont);
            date.setAlignment(Element.ALIGN_LEFT);
            date.setSpacingAfter(10f);
            document.add(date);
 
            Paragraph produits = new Paragraph("Voici la liste de vos produits disponible sur la boutique en ligne de ALKIFF", regularFont);
            produits.setAlignment(Element.ALIGN_LEFT);
            produits.setSpacingAfter(10f);
            document.add(produits);
 
            ArrayList<produits> products=(ArrayList<produits>)st.afficher(1);          
       
            
            PdfPTable table = new PdfPTable(5); // 3 colonnes pour Nom, Prix et Quantité
            
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
 
            // En-tête de table
            table.addCell(new PdfPCell(new Phrase("Titre", regularFont)));
            table.addCell(new PdfPCell(new Phrase("Description", regularFont)));
            table.addCell(new PdfPCell(new Phrase("prix", regularFont)));

            // Contenu de table
            for (produits p : products) {
                System.out.println(p);
                table.addCell(new PdfPCell(new Phrase(p.getProduct_name(), regularFont)));
                table.addCell(new PdfPCell(new Phrase(p.getProduct_description(), regularFont)));
                table.addCell(new PdfPCell(new Phrase(String.valueOf(p.getProduct_price()), regularFont)));
            }

            document.add(table);
 
            document.close();
        } catch (IOException | DocumentException ex) {
            System.err.println("Erreur lors de l'écriture dans le fichier: " + ex.getMessage());
        }
    } else {
        System.out.println("La sélection de fichier a été annulée");
    }
    }
    
    /*
    public void player() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Scanner scanner = new Scanner(System.in);
        File file = new File("C:\\Users\\ABDOU\\Desktop\\gestionproduit\\src\\gui\\tit.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        String response = "";
        while(!response.equals("Q")) {
         System.out.println("P = play, S = Stop, R = Reset, Q = Quit");
         System.out.print("Enter your choice: ");
         response = scanner.next();
         response = response.toUpperCase();
         switch(response) {
          case ("P"): clip.start();
          break;
          case ("S"): clip.stop();
          break;
          case ("R"): clip.setMicrosecondPosition(0);
          break;
          case ("Q"): clip.close();
          break;
          default: System.out.println("Not a valid response");
         }
        }
        System.out.println("Byeeee!"); 
 }
*/

    @FXML
    private void handleSignupButtonClick(MouseEvent event) {
        String first_name = input_firstName.getText();
        String last_name = input_lastName.getText();
        String username = input_username.getText();
        String email = input_email.getText();
        String password = input_password.getText();
        String gender = input_gender.getText();
        SendMail sm = new SendMail();
        String emailSubject = "Account created";
        String emailMessage = "votre compte a été ajoute !";
        sm.send(email, emailSubject, emailMessage);
        authentificationService.signup(first_name, last_name, username, email, password, gender);
        if (input_firstName.getText().isEmpty() || input_lastName.getText().isEmpty() || input_username.getText().isEmpty()
                || input_email.getText().isEmpty() || input_password.getText().isEmpty()
                || input_gender.getText().isEmpty()) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!input_email.getText().matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Format email incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un email valide !");
            alert.showAndWait();
            return;
        }

        String motdepasse = input_password.getText();
        if (!motdepasse.matches(".*[A-Z].*") || !motdepasse.matches(".*\\d.*")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Le mot de passe doit contenir au moins une lettre majuscule et un chiffre.");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    private void signup(javafx.event.ActionEvent event) throws SQLException {
        pn_signup.toFront();
    }

    @FXML
    private void Login(javafx.event.ActionEvent event) throws SQLException {
        AuthentificationService authService = new AuthentificationService();
        Connection cnx = MyConnection.getTest().getCnx();
        String user_Name = txtuname.getText();
        String password = txtpass.getText();
        if (user_Name.equals("") && password.equals("")) {
            JOptionPane.showMessageDialog(null, "UserName or Paswword blank");
        } else {
            String requete = "SELECT * FROM user WHERE username = ?";
            PreparedStatement statement = cnx.prepareStatement(requete);
            statement.setString(1, user_Name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (!authService.checkPasswd(password, rs.getString(5))) {
                    JOptionPane.showMessageDialog(null, "Wrong credentials.");
                } else {
                    User user = new User();
                    user.setId_user(rs.getInt(1));
                    user.setFirst_Name(rs.getString(2));
                    user.setLast_Name(rs.getString(3));
                    user.setUser_Name(rs.getString(4));
                    user.setPassword(rs.getString(5));
                    user.setEmail(rs.getString(6));
                    user.setPhone_number(rs.getInt(7));
                    user.setGender(rs.getString(8));
                    user.setRole(rs.getString(9));

                    this.userSession = UserSession.getInstace(user);
                    pn_captcha.toFront();
                }

            } else {

                JOptionPane.showMessageDialog(null, " Login failed  ");
                txtuname.setText("");
                txtpass.setText("");
                txtuname.requestFocus();

            }

            //Class.forName("com.mysql.jdbc.Driver");
            // cnx = DriverManager.getConnection(URL, USER, PWD);
            //} catch(ClassNotFoundException ex) {
            // Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ForgetPassword(javafx.event.ActionEvent event) {
    }

    @FXML
    private void btn_log(javafx.event.ActionEvent event) {
        pn_login.toFront();
    }

    @FXML
    private void captchachecker(javafx.event.ActionEvent event) {
        String userinput = textarea.getText();
        if (userinput.equals(String.valueOf(captchahere.getText()))) {
            if (!(userinput.equals("captcha"))) {
                messagebtn.setText("Success");
                messagebtn.setTextFill(Color.GREEN);
                

                this.userSession = UserSession.getInstace(null);
                try {
                    User u=su.afficher(21);
                    lusername.setText(this.userSession.getUser().getUser_Name());
                    lfname.setText(this.userSession.getUser().getFirst_Name());
                    llname.setText(this.userSession.getUser().getLast_Name());
                    lemail.setText(this.userSession.getUser().getEmail());
                    String tmp =String.valueOf(this.userSession.getUser().getPhone_number());
                    lphone_number.setText(tmp);
                    lgender.setText(this.userSession.getUser().getGender());
                    lrole.setText(this.userSession.getUser().getRole());
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (this.userSession.getUser().getRole().equals("ADMIN")) {
                    pn_captcha.toBack();
                    pn_login.toBack();
                    pn_boutique.toFront();
                    loption.setText("Option");btn_listuser.setVisible(true);
                    btn_mescommandes.setVisible(true);
                    btn_listuser.setDisable(false);
                    btn_mescommandes.setDisable(false);  
                    lrole.setText(this.userSession.getUser().getRole());
                    llname.setText(this.userSession.getUser().getUser_Name());
                    ///////////////////////////////////////////
                    btn_ajouterblog.setDisable(true);
                    btn_mesblog.setDisable(true);
                    btn_ajouterevent.setDisable(true);
                    btn_mesevent.setDisable(true);
                    btn_mesproduit.setDisable(true);
                    btn_ajouterproduit.setDisable(true);
                    /////////////////////////////////////////
                    btn_ajouterblog.setVisible(false);
                    btn_mesblog.setVisible(false);
                    btn_ajouterevent.setVisible(false);
                    btn_mesevent.setVisible(false);
                    btn_mesproduit.setVisible(false);
                    btn_ajouterproduit.setVisible(false);
                }else if (this.userSession.getUser().getRole().equals("Client")){
                    pn_boutique.toFront();
                    loption.setText("");
                    lrole.setText(this.userSession.getUser().getRole());
                    llname.setText(this.userSession.getUser().getUser_Name());
                    ///////////////////////////////////////////
                    btn_ajouterblog.setDisable(true);
                    btn_mesblog.setDisable(true);
                    btn_ajouterevent.setDisable(true);
                    btn_mesevent.setDisable(true);
                    btn_mesproduit.setDisable(true);
                    btn_ajouterproduit.setDisable(true);
                    btn_listuser.setDisable(true);
                    btn_mescommandes.setDisable(true);        
                    /////////////////////////////////////////
                    btn_ajouterblog.setVisible(false);
                    btn_mesblog.setVisible(false);
                    btn_ajouterevent.setVisible(false);
                    btn_mesevent.setVisible(false);
                    btn_mesproduit.setVisible(false);
                    btn_ajouterproduit.setVisible(false);
                    btn_listuser.setVisible(false);
                    btn_mescommandes.setVisible(false);
                    pn_captcha.toBack();
                    pn_login.toBack();
                    
                }

            } else {
                messagebtn.setText("please click on new captcha");
                messagebtn.setTextFill(Color.RED);
            }
        } else {
            messagebtn.setText("Fail");
            messagebtn.setTextFill(Color.RED);
        }
    }

    @FXML
    private void setcaptcha(javafx.event.ActionEvent event) {
        char data[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int max = 61;
        String captchastring = "";
        int min = 0;
        for (int i = 0; i < 6; i++) {
            int randomnumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
            captchastring += data[randomnumber];
        }
        System.out.println(captchastring);
        captchahere.setText(captchastring);
        captchahere.setTextFill(Color.GREEN); 
   }

    @FXML
    private void handleLoginButtonClick(javafx.event.ActionEvent event) {
    }

    @FXML
    private void btn_mescommandesa(javafx.event.ActionEvent event) {
    }

    @FXML
    private void btn_listusera(javafx.event.ActionEvent event) {
                pn_listuser.toFront();
    }

    @FXML
    private void set(javafx.event.ActionEvent event) throws SQLException {
        String lusername2 = lusername.getText();
        String lfname2 = lfname.getText();
        String llname2 = lfname.getText();
        String lemail2 = lemail.getText();
        String lphone_number2 = lphone_number.getText();
        int tmp =Integer.parseInt(lphone_number2);
        String lgender2 = lgender.getText();
        String lrole2 = lrole.getText();
        su.modifier(lusername2, lfname2, llname2, lemail2, tmp,lgender2, lrole2,this.userSession.getUser().getId_user());
    }
}
