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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import services.SaleService;


/**
 * FXML Controller class
 *
 * @author ABDOU
 */
public class InterfaceController implements Initializable {
    
    
    ProductService sp = new ProductService();
    SaleService ss = new SaleService();
    produits  tmpp = new produits();
    private Image image;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {   
                display ();
                display2();
                recherche_avance();
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
                ResultSet resultSet2 = sp.Selectionner(1);
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
 
            // Ajouter les éléments de l'interface utilisateur pour le ticket d'achat
            com.itextpdf.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Liste des produits", titleFont);
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
}
