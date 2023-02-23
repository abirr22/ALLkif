/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.auth;

import Entities.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import Service.ServiceUser;
/**
 * FXML Controller class
 *
 * @author abirk
 */
public class AfficheBackOffController implements Initializable {

    @FXML
    private TableView<?> afficher;
    @FXML
    private TableColumn<?, ?> id_userColumn;
    @FXML
    private TableColumn<?, ?> first_nameColumn;
    @FXML
    private TableColumn<?, ?> last_nameColumn;
    @FXML
    private TableColumn<?, ?> usernameColumn;
    @FXML
    private TableColumn<?, ?> passwordColumn;
    @FXML
    private TableColumn<?, ?> emailColumn;
    @FXML
    private TableColumn<?, ?> phone_numberColumn;
    @FXML
    private TableColumn<?, ?> genderColumn;
    @FXML
    private TableColumn<?, ?> roleColumn;
    @FXML
    private ComboBox<?> changer_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           //User u=su.afficher(6);
            //User u=su.getALL();
        changer_id.setPromptText("changer type utilisateur");
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
        List<User> list = ServiceUser.getAll();
        
// affiche les données dans le tableau
        afficher.setAll(list);
        
        
        
        
    }  
    
    
}
