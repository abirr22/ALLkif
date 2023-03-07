/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.smartcardio.Card;
import javax.smartcardio.CardException;

/**
 * FXML Controller class
 *
 * @author zayat
 */
public class PaimentController implements Initializable {

    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField expiryMonthField;
    @FXML
    private TextField expiryYearField;
    @FXML
    private TextField cvvField;
    @FXML
    private Button payButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        // TODO
    }    

    @FXML
    private void processPayment(ActionEvent event) { 
         try {
            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("number", cardNumberField.getText());
            cardParams.put("exp_month", expiryMonthField.getText());
            cardParams.put("exp_year", expiryYearField.getText());
            cardParams.put("cvc", cvvField.getText());

            Card card = Card.create(cardParams);

            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", 1000);
            chargeParams.put("currency", "usd");
            chargeParams.put("source", card.getId());
            chargeParams.put("description", "Test Payment");

            Charge charge = Charge.create(chargeParams);

            System.out.println("Payment Successful: " + charge.getId());

        } catch (CardException e) {
            System.out.println("Card Error: " + e.getMessage());
        } catch (RateLimitException e) {
            System.out.println("Rate Limit Error: " + e.getMessage());
        } catch (InvalidRequestException e) {
            System.out.println("Invalid Request Error: " + e.getMessage());
        } catch (AuthenticationException e) {
            System.out.println("Authentication Error: " + e.getMessage());
        } catch (APIConnectionException e) {
            System.out.println("API Connection Error: " + e.getMessage());
        } catch (StripeException e) {
            System.out.println("Stripe Error: " + e.getMessage());
        }
    }
    }
    

