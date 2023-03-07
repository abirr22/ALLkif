/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.gluonhq.charm.glisten.control.BottomNavigationButton;
import edu.esprit.entites.commande;
import edu.esprit.entites.panier;
import edu.esprit.services.ServiceCommande;
import edu.esprit.services.ServicePanier;
import edu.esprit.entites.MailSender;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage; 
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author zayat
 */
public class CommandeController implements Initializable {

    @FXML
    private Button fx_cc;
    @FXML
    private Button fx_AC;
    @FXML
    private TextField fx_total;
    String mail = "sarah.zayati@esprit.tn";
    //private TableView<commande> total;
    //@FXML
    //  private TableColumn<commande, Integer> commande;
    ServiceCommande pc = new ServiceCommande();
    @FXML
    private BottomNavigationButton panier;
    ServiceCommande sc = new ServiceCommande();
    @FXML
    private TextField textFiled;
    @FXML
    private Button SMS;
    @FXML
    private Label statusLabel; 
      public static final String AUTH_TOKEN = "15e280c0506f19da735b019d928a7f28";
    public static final String TWILIO_NUMBER = "+13157911257";
     public static final String ACCOUNT_SID = "ACae09f8846d7dc92c113b3c6f0c6ddf86";
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
       

        // TODO
        //commande.setCellValueFactory(cellData -> new SimpleIntegerProperty((int) cellData.getValue().getTotale()).asObject());  
        // ServiceCommande crud = new ServiceCommande();
        // Populate the table with data
        /*List<commande> data;
        data = crud.getAll();
        total.getItems().setAll(data);*/
        // totalCalculation();
    }

    public void setData(String data) {
        System.out.println("this function was called"+data);
       fx_total.setText(data);
        
    }

    @FXML
    private void confirmer_commande(MouseEvent event) {
        try {

            Parent page2
                    = FXMLLoader.load(getClass().getResource("confc.fxml"));
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setUserData("Hello ");
            stage.show();
        } catch (IOException ex) {

        }

    }

    @FXML
    private void annuler_commande(MouseEvent event) { 
        
         try {

            Parent page2
                    = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setUserData("Hello ");
            stage.show();
        } catch (IOException ex) {

        } 
          try {
            MailSender.sendMail(mail);
        } catch (Exception ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }


    private void totalCalculation() {
        //Object total = null;
        //.setText(String.valueOf(total));

    } 
    
    @FXML
    private void panier (ActionEvent event) {
         try {

            Parent page2
                    = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setUserData("Hello ");
            stage.show();
        } catch (IOException ex) {

        }
        
    }

    @FXML 
    private void comfirmer(ActionEvent event) {
       /*try {
            MailSender.sendMail(mail);
        } catch (Exception ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
  String toPhoneNumber = "+21655369782";
    //if (toPhoneNumber == null || toPhoneNumber.trim().isEmpty()) {
      //  statusLabel.setText("Please enter a phone number.");
        //return;
    //}
      
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    String messageText = "Merci pour votre Commande de  " + formatter.format(currentDate)+" Nous vous confirmons votre commande " +"Bonne réception";
    
    
    Message message = Message.creator(new PhoneNumber(toPhoneNumber),
            new PhoneNumber(TWILIO_NUMBER),
            messageText).create();
 
    //if (message.getSid() != null) {
      //  statusLabel.setText("SMS sent successfully to " + toPhoneNumber + "!");
    //} else {
      //  statusLabel.setText("Error sending SMS to " + toPhoneNumber + ".");
    

       
        sc.creecommande(3);  
        
        
        
        
    } 


    
        

   
   

   /* @FXML
    private void sendSMS(ActionEvent event) { 
        
    String toPhoneNumber = textFiled.getText();
    if (toPhoneNumber == null || toPhoneNumber.trim().isEmpty()) {
        statusLabel.setText("Please enter a phone number.");
        return;
    }

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String messageText = "Merci pour votre commande  " + formatter.format(currentDate);
    Message message = Message.creator(new PhoneNumber(toPhoneNumber),
            new PhoneNumber(TWILIO_NUMBER),
            messageText).create();

    if (message.getSid() != null) {
        statusLabel.setText("SMS sent successfully to " + toPhoneNumber + "!");
    } else {
        statusLabel.setText("Error sending SMS to " + toPhoneNumber + ".");
    }
    }*/ 
}

    

    
    
    
     
    

