/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.testes;
import edu.esprit.entites.panier;
import edu.esprit.entites.commande;
import edu.esprit.services.ServicePanier;
import edu.esprit.services.ServiceCommande;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author zayat
 */
public class MainClasseFacture extends Application { 
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/edu/esprit/gui/FXML.fxml"));
        primaryStage.setTitle("");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
      public static void main(String[] args) { 
        launch(args);
        
    
}
}
