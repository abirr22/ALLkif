/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author khedi
 */
public class Publications {
    private int id;
    private String titre_pub, texte_pub, photo_pub;
    
    public Publications(){
        
    }

    public Publications(String titre_pub, String texte_pub, String photo_pub) {
        this.titre_pub = titre_pub;
        this.texte_pub = texte_pub;
        this.photo_pub = photo_pub;
    }

    public Publications(int id, String titre_pub, String texte_pub, String photo_pub) {
        this.id = id;
        this.titre_pub = titre_pub;
        this.texte_pub = texte_pub;
        this.photo_pub = photo_pub;
    }

    public int getId() {
        return id;
    }

    public String getTitre_pub() {
        return titre_pub;
    }

    public String getTexte_pub() {
        return texte_pub;
    }   
    
    public String getPhoto_pub() {
        return photo_pub;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre_pub(String titre_pub) {
        this.titre_pub = titre_pub;
    }

    public void setTexte_pub(String texte_pub) {
        this.texte_pub = texte_pub;
    }

    public void setPhoto_pub(String photo_pub) {
        this.photo_pub = photo_pub;
    }

    @Override
    public String toString() {
        return "Publications{" + "Titre_pub=" + titre_pub + ", Texte_pub=" + texte_pub + '}'; 
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Publications other = (Publications) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
