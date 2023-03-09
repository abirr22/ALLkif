/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author zayat
 */
public class facture { 
    private int id_facture;
    private int id_commande;
    private boolean flag;

    public facture() {
    }

    public facture(boolean flag) {
        this.flag = flag;
    }

    public facture(int id_facture, int id_commande, boolean flag) {
        this.id_facture = id_facture;
        this.id_commande = id_commande;
        this.flag = flag;
    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id_facture;
        hash = 79 * hash + this.id_commande;
        hash = 79 * hash + (this.flag ? 1 : 0);
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
        final facture other = (facture) obj;
        if (this.id_facture != other.id_facture) {
            return false;
        }
        if (this.id_commande != other.id_commande) {
            return false;
        }
        if (this.flag != other.flag) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "facture{" + "flag=" + flag + '}';
    }
    
    
}
