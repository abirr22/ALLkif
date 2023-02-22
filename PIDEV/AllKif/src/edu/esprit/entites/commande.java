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
public class commande {
    private int id_panier;
    private int id_commande; 
    private int id_user; 
    private double totale; 

    public commande() {
    }

    public commande(double totale) {
        this.totale = totale;
    }

    public commande(int id_panier, int id_commande, int id_user, double totale) {
        this.id_panier = id_panier;
        this.id_commande = id_commande;
        this.id_user = id_user;
        this.totale = totale;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id_panier;
        hash = 53 * hash + this.id_commande;
        hash = 53 * hash + this.id_user;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.totale) ^ (Double.doubleToLongBits(this.totale) >>> 32));
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
        final commande other = (commande) obj;
        if (this.id_panier != other.id_panier) {
            return false;
        }
        if (this.id_commande != other.id_commande) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        else if (Double.doubleToLongBits(this.totale) != Double.doubleToLongBits(other.totale)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "commande{" + "totale=" + totale + '}';
    }

   

    
    
}
