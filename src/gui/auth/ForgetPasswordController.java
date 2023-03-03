/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.auth;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private Button handleButtonSendSms;
    @FXML
    private TextField txtnumber;
    @FXML
    private Button handleButtonReturn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonSendSms(ActionEvent event) {
    }

    @FXML
    private void handleButtonReturn(ActionEvent event) {
    }
    
}
