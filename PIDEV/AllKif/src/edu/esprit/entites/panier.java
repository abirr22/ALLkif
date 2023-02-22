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
public class panier { 
     private int id_panier ; 
    private int id_prod;
    private double prix;
    private int quantite ;

    public panier() {
    }

    public panier(double prix, int quantite) {
        this.prix = prix;
        this.quantite = quantite;
    }

    public panier(int id_panier, int id_prod, double prix, int quantite) {
        this.id_panier = id_panier;
        this.id_prod = id_prod;
        this.prix = prix;
        this.quantite = quantite;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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
        hash = 59 * hash + this.id_panier;
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
        final panier other = (panier) obj;
        return this.id_panier == other.id_panier;
    }

    @Override
    public String toString() {
        return "panier{" + "prix=" + prix + ", quantite=" + quantite + '}';
    }
    
}
