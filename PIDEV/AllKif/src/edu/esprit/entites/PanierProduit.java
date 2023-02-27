/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entites;

/**
 *
 * @author zayat
 */
public class PanierProduit {
    private int id_panier;
    private int id_produit; 

    public PanierProduit() {
    }

    public PanierProduit(int id_panier, int id_produit) {
        this.id_panier = id_panier;
        this.id_produit = id_produit;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id_panier;
        hash = 59 * hash + this.id_produit;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PanierProduit other = (PanierProduit) obj;
        if (this.id_panier != other.id_panier) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "produitpanier{" + "id_panier=" + id_panier + ", id_produit=" + id_produit + '}';
    }

    public double getPrix() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getQuantite() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
