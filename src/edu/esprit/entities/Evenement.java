/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;
import java.time.LocalDate;



/**
 *
 * @author siwar
 */
public class Evenement {
    private int idEvenement;
    private String nomEvenement, descriptionEvenement, Artiste;
    private Date dateEvenement;
    private double prixEvenement;

    public Evenement() {
    }

    public Evenement(String nomEvenement, String descriptionEvenement, String Artiste, Date dateEvenement, double prixEvenement) {
        this.nomEvenement = nomEvenement;
        this.descriptionEvenement = descriptionEvenement;
         this.Artiste = Artiste;
        this.dateEvenement = dateEvenement;
        this.prixEvenement = prixEvenement;
    }

    public Evenement(int idEvenement, String nomEvenement, String descriptionEvenement, String Artiste, Date dateEvenement, double prixEvenement) {
        this.idEvenement = idEvenement;
        this.nomEvenement = nomEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.Artiste = Artiste;
        this.dateEvenement = dateEvenement;
        this.prixEvenement = prixEvenement;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }
    
    public String getArtiste() {
        return Artiste;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public double getPrixEvenement() {
        return prixEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }
    
     public void setArtiste(String Artiste) {
        this.Artiste = Artiste;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public void setPrixEvenement(double prixEvenement) {
        this.prixEvenement = prixEvenement;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idEvenement;
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
        final Evenement other = (Evenement) obj;
        if (this.idEvenement != other.idEvenement) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "nomEvenement=" + nomEvenement + ", descriptionEvenement=" + descriptionEvenement + ", Artiste=" + Artiste + ", dateEvenement=" + dateEvenement + ", prixEvenement=" + prixEvenement + '}';
    }

   
    
    
    
    
}
