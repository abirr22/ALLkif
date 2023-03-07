/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entites;

import java.util.Objects;

/**
 *
 * @author zayat
 */
public class commande {
    private int id_panier;
    private int id_commande; 
    private int totale; 
    private boolean valide;
    private String name;
    private String description;
    private int price;
    private String pic;
    private int id_user;
   private int id_prod;
   private String content;

    public commande(int id_commande, int totale, int id_user, String content) {
        this.totale = totale;
        this.id_user = id_user;
        this.content = content;
        this.id_commande = id_commande;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public commande() {
    }

    public commande(int totale, boolean valide) {
        this.totale = totale;
        this.valide = valide;
    }
    
    public commande(int total, int id_panier, String content) {
        this.id_panier = id_panier;
        this.totale = total;
        this.content = content;
       
        
    }

    public commande(int id_panier, int id_commande, int totale, boolean valide,int id_user,int id_prod) {
        this.id_panier = id_panier;
        this.id_commande = id_commande;
      this.id_user=id_user;
        this.totale = totale;
        this.valide = valide;
        this.id_prod = id_prod; 
        
    }

    public commande(int id_commande, String name, String description, String pic, int price, int id_panier, int totale, boolean valide, int id_prod) {
          this.id_panier=id_panier;
        this.name=name;
        this.description=description;
        this.pic=pic;
        this.price=price;
          this.totale = totale;
        this.valide = valide;
        this.id_prod=id_prod;
        
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

    public void setTotale(int totale) {
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
 public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id_panier;
        hash = 37 * hash + this.id_commande;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.totale) ^ (Double.doubleToLongBits(this.totale) >>> 32));
        hash = 37 * hash + (this.valide ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + this.price;
        hash = 37 * hash + Objects.hashCode(this.pic);
        hash = 37 * hash + this.id_user;
        hash = 37 * hash + this.id_prod;
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
        if (this.price != other.price) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_prod != other.id_prod) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.pic, other.pic)) {
            return false;
        }
        return true;
    }

    public commande(int totale, String content) {
        this.totale = totale;
        this.content = content;
    }
   
    

    @Override
    public String toString() {
        return "commande{" + "totale=" + totale + ", valide=" + valide + '}';
    }

    
   

    
    
}
