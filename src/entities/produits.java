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
public class produits {
    private int id_product ;
    private String product_name;
    private String product_description;
    private String product_photo;
    private float product_price ; 
    private String addeddate ; 
    private int related_artist; 

    public produits(String product_name, String product_description, String product_photo, float product_price, int related_artist) {
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_photo = product_photo;
        this.product_price = product_price;
        this.related_artist = related_artist;
    }

    public produits() {
    }

    public produits(int id_product, String product_name, String product_description, String product_photo, float product_price) {
        this.id_product = id_product;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_photo = product_photo;
        this.product_price = product_price;
    }

    public produits(String product_name, String product_description, String product_photo, float product_price) {
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_photo = product_photo;
        this.product_price = product_price;
    }
    
    

    public int getId_product() {
        return id_product;
    }


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_photo() {
        return product_photo;
    }

    public void setProduct_photo(String product_photo) {
        this.product_photo = product_photo;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public String getAddeddate() {
        return addeddate;
    }

    public void setAddeddate(String addeddate) {
        this.addeddate = addeddate;
    }

    public int getRelated_artist() {
        return related_artist;
    }

    public void setRelated_artist(int related_artist) {
        this.related_artist = related_artist;
    }

    @Override
    public String toString() {
        return "produits{" + "id_product=" + id_product + ", product_name=" + product_name + ", product_description=" + product_description + ", product_photo=" + product_photo + ", product_price=" + product_price + ", addeddate=" + addeddate + ", related_artist=" + related_artist + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id_product;
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
        final produits other = (produits) obj;
        if (this.id_product != other.id_product) {
            return false;
        }
        return true;
    }
    
        

    
}
