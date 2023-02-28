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
    private double totale; 
    private boolean valide;
    private String name;
    private String description;
    private int price;
    private String pic;

    public commande() {
    }

    public commande(double totale, boolean valide) {
        this.totale = totale;
        this.valide = valide;
    }

    public commande(int id_panier, int id_commande, double totale, boolean valide) {
        this.id_panier = id_panier;
        this.id_commande = id_commande;
      
        this.totale = totale;
        this.valide = valide;
    }

    public commande(int id_commande, String name, String description, String pic, int price, int id_panier, int totale, boolean valide) {
          this.id_panier=id_panier;
        this.name=name;
        this.description=description;
        this.pic=pic;
        this.price=price;
          this.totale = totale;
        this.valide = valide;
        
        
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

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id_panier;
        hash = 67 * hash + this.id_commande;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.totale) ^ (Double.doubleToLongBits(this.totale) >>> 32));
        hash = 67 * hash + (this.valide ? 1 : 0);
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
        if (Double.doubleToLongBits(this.totale) != Double.doubleToLongBits(other.totale)) {
            return false;
        }
        if (this.valide != other.valide) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "commande{" + "totale=" + totale + ", valide=" + valide + '}';
    }

    
   

    
    
}
