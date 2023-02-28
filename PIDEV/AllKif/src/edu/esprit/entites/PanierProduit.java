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
    private int quantite;  
    public String getQuantite;

    public PanierProduit() {
    }

    public PanierProduit(int quantite) {
        this.quantite = quantite;
    }

    public PanierProduit(int id_panier, int id_produit, int quantite) {
        this.id_panier = id_panier;
        this.id_produit = id_produit;
        this.quantite = quantite;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id_panier;
        hash = 97 * hash + this.id_produit;
        hash = 97 * hash + this.quantite;
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
        if (this.quantite != other.quantite) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PanierProduit{" + "quantite=" + quantite + '}';
    }
    


}

   
    

