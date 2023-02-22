/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.auth;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Entities.User;
import Service.ServiceUser;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class AfficherController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ServiceUser su = new ServiceUser();
    @FXML
    private TextField lusername;
    @FXML
    private TextField lfname;
    @FXML
    private TextField llname;
    @FXML
    private TextField lemail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            User u=su.afficher(6);
            lusername.setText(u.getUser_Name());
            lfname.setText(u.getFirst_Name());
            llname.setText(u.getLast_Name());
            lemail.setText(u.getEmail());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void set(ActionEvent event) throws SQLException {
        String lusername2 = lusername.getText();
        String lfname2 = lfname.getText();
        String llname2 = llname.getText();
        String lemail2 = lemail.getText();
        su.modifier(lusername2, lfname2, llname2, lemail2, 6);


    }
    
}
