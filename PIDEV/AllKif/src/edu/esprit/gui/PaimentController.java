/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;
import com.stripe.model.Card;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField; 
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


/**
 * FXML Controller class
 *
 * @author zayat
 */
public class PaimentController implements Initializable {

    @FXML
    private ChoiceBox<String> carteList;
    @FXML
    private Label chCarte;
    @FXML
    private Hyperlink ajouterCarte;
    @FXML
    private Button PayerBtn;
    @FXML
    private TextField montant;
    @FXML
    private ChoiceBox<String> PaymentMethod;
    @FXML
    private Label soldeLabel;
    @FXML
    private Label otpLabel;
    @FXML
    private TextField codeF;
    @FXML
    private Button ConfirmerBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCB();

    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setSoldeLabel() {
        soldeLabel.setText("Votre Solde est: " + this.getSolde() + " TND");
    }

    public void setFormateur(User formateur) {
        this.formateur = formateur;
    }

    public User getFormateur() {
        return formateur;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setAmount(long amount) {
        montant.setText(String.valueOf(amount));
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }

    public String getSolde() {
        return solde;
    }

    public void initCB() {

        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Payer avec votre solde");
        options.add("Payer avec votre carte bancaire");
        PaymentMethod.setItems(options);
        PaymentMethod.setValue(options.get(0));
        PaymentMethod.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int i = PaymentMethod.getSelectionModel().getSelectedIndex();
            if (i == 0) {
                soldeLabel.setVisible(true);
                carteList.setVisible(false);
                ajouterCarte.setVisible(false);
                chCarte.setVisible(false);
            } else {
                soldeLabel.setVisible(false);
                carteList.setVisible(true);
                ajouterCarte.setVisible(true);
                chCarte.setVisible(true);

            }
        });
    }

    public void refreshCardList() {
        try {

            carteList.getItems().clear();
            PaymentAPI p = new PaymentAPI(utilisateur);
            ObservableList<PaymentMethod> cardList = p.getCustomerCards();
            ObservableList<String> options = FXCollections.observableArrayList();
            for (PaymentMethod card : cardList) {
                String option = card.getCard().getBrand().toUpperCase() + " **** **** **** " + card.getCard().getLast4();
                options.add(option);
            }
            carteList.setItems(options);
            carteList.setValue(options.get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void AddCardGUI(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCardGUI.fxml"));
            Parent root = loader.load();
            AddCardGUIController acg = loader.getController();
            acg.setUtilisateur(utilisateur);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            int resultCode = acg.getResultCode();
            if (resultCode == 1) {
                refreshCardList();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String genererCode() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return Integer.toString(otp);
    }

    @FXML
    private void PayerAction(ActionEvent event) {

        if ((Long.valueOf(solde) - Long.valueOf(montant.getText()) < 0 || Long.valueOf(solde) < 0) && PaymentMethod.getSelectionModel().getSelectedIndex() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Solde insuffisant");
            alert.showAndWait();
            return;
        }

        if (carteList.getSelectionModel().getSelectedItem() == null && PaymentMethod.getSelectionModel().getSelectedIndex() == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune carte selectionnée.");
            alert.showAndWait();
            return;
        }
        otpLabel.setVisible(true);
        codeF.setVisible(true);
        PayerBtn.setVisible(false);
        ConfirmerBtn.setVisible(true);

        Alert otpAlert = new Alert(Alert.AlertType.INFORMATION, "un code de vérification Khadamni a été envoyé vers "
                + utilisateur.getNum_tel());
        otpAlert.setTitle("OTP CODE");
        otpAlert.showAndWait();

        String codeVerif = this.genererCode();
        this.otp = codeVerif;
        SmsAPI.sendSMS(otp);

    }


    @FXML
    private void ConfirmerAction(ActionEvent event) {
         if (codeF.getText().equals(this.otp)) {
        int i = carteList.getSelectionModel().getSelectedIndex();
        PaymentAPI p = new PaymentAPI(utilisateur);
        ObservableList<PaymentMethod> cardList = p.getCustomerCards();
        String idcarte;
        if (PaymentMethod.getSelectionModel().getSelectedIndex() == 0) {
            idcarte = "";
        } else {
            idcarte = cardList.get(i).getId();
        }
        Inscription inscri = new Inscription(formation.getId_formation(), utilisateur.getId_user());
        if (p.sendMoney(formateur, Long.valueOf(montant.getText()) * 100, idcarte)) {
            inscri.ajouter_inscription();
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Vous étes inscrit avec succès."
                    + "Une facture de paiement a été envoyé vers " + utilisateur.getEmail());
            successAlert.setTitle("Succes");
            successAlert.showAndWait();
            MailAPI m = new MailAPI(utilisateur);
            m.sendEmail(formation);
            this.solde = String.valueOf(p.getBalance());
            Stage stage = (Stage) otpLabel.getScene().getWindow();
            stage.close();

        } else {
            Alert alert2 = new Alert(Alert.AlertType.WARNING, "probleme de paiement !");
            alert2.showAndWait();
        }

          } else {
              Alert alert2 = new Alert(Alert.AlertType.WARNING, "code de vérification incorrecte!");
               alert2.showAndWait();
          }
    }

    
}
