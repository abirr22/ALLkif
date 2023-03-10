/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;
import javafx.scene.control.TextField;


/**
 *
 * @author siwar
 */
public class ControleSaisieTextFields {

    public ControleSaisieTextFields() {
    }
    
     public boolean isEmpty(String T) {
        return (T.equals(""));
    }
//
//    public boolean checkOnlyInteger(String T) {
//        String integerRegex = "^\d+$" ;
//        return T.matches(integerRegex);
//    }

    
    public boolean checkOnlyString(String T) {
        String AlphaRegex = "^[a-zA-Z].$";
        return T.matches(AlphaRegex);
    }

  
    
}
