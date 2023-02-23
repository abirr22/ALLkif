/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ABDOU
 */
public class sale {
    private int id_sale;
    private int taux;
    private int id_produit;

    public sale() {
    }

    public sale(int taux, int id_produit) {
        this.taux = taux;
        this.id_produit = id_produit;
    }

    public sale(int id_sale, int taux, int id_produit) {
        this.id_sale = id_sale;
        this.taux = taux;
        this.id_produit = id_produit;
    }

    public int getId_sale() {
        return id_sale;
    }

    public void setId_sale(int id_sale) {
        this.id_sale = id_sale;
    }

    public int getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }
    
}
// sselectall / control saisie /