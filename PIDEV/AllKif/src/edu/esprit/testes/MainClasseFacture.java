/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.testes;
import edu.esprit.entites.facture;
import edu.esprit.services.ServiceFacture;

/**
 *
 * @author zayat
 */
public class MainClasseFacture { 
      public static void main(String[] args) { 
        facture f = new facture(); 
        ServiceFacture sf = new ServiceFacture(); 
        sf.ajouter(f);
    
}
}
