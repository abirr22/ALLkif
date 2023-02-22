/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.auth;

import Service.AuthentificationService;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class Signup_pageController implements Initializable {

    @FXML
    private Button btn_signup;
    @FXML
    private TextField input_username;
    @FXML
    private TextField input_email;
    @FXML
    private TextField input_password;
    @FXML
    private TextField input_password_check;
    
    AuthentificationService authentificationService = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        authentificationService = new AuthentificationService();
        // TODO
        
        
     }    
    @FXML
    public void handleSignupButtonClick(MouseEvent event){
        String username = input_username.getText();
        String email = input_email.getText();
        String password = input_password.getText();
        String password_check= input_password_check.getText();
        authentificationService.signup(username, email, password);
    }
    
    
    
    
    
    
}
