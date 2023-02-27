/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entites;

import java.util.Objects;
import javafx.scene.control.TableColumn;

/**
 *
 * @author zayat
 */
public class panier { 

    private int id_panier;
    private double prix;
    private int quantite;
    private int id_user;
    private String name;
    private String description;
    private String pic;
    private int price;
    private int id_prod;
  

    public panier() {
    }

    public panier(double prix, int quantite) {
        this.prix = prix;
        this.quantite = quantite;
    }

    public panier(int id_panier, double prix, int quantite, int user) {
        this.id_panier = id_panier;

        this.prix = prix;
        this.quantite = quantite;
        this.id_user = id_user;
       
        
    }

    public panier(int id_panier, double prix, int quantite, String name, String description, String pic, int price, int id_prod) {
        
        this.id_panier=id_panier;
        this.prix=prix;
        this.quantite=quantite;
        this.name=name;
        this.description=description;
        this.pic=pic;
        this.id_prod=id_prod;
        this.price=price;
        
        
        
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name =name ;
    }
    public String getPic() {
        return name;
    }

    public void setPic(String pic) {
        this.pic =pic ;
    } 
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description ;
    }
     public int getPrice() {
        return price;
    }

    public void setPrice(int  price) {
        this.price=price ;
    } 

    /**
     *
     * @return
     */
    
   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id_panier;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.prix) ^ (Double.doubleToLongBits(this.prix) >>> 32));
        hash = 53 * hash + this.quantite;
        hash = 53 * hash + this.id_user;
       
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
        if (this.id_panier != other.id_panier) {
            return false;
    }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }

        return true;
    } 

    @Override
    public String toString() {
        return "panier{" + "prix=" + prix + ", quantite=" + quantite + '}';
    }
    
   
   
    
    

   
}

    //public panier(TableColumn<panier, Integer> id_panier, TableColumn<panier, Integer> id_prod, int prix, int quantite) {
    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
   
