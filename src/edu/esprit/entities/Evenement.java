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
    private int  nbreParticipantMax, nbreParticipant;

    public Evenement() {
    }

    public Evenement(String nomEvenement, String descriptionEvenement, String Artiste, Date dateEvenement, double prixEvenement, int nbreParticipantMax) {
        this.nomEvenement = nomEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.Artiste = Artiste;
        this.dateEvenement = dateEvenement;
        this.prixEvenement = prixEvenement;
        this.nbreParticipantMax = nbreParticipantMax;
    }

    public Evenement(String nomEvenement, String descriptionEvenement, String Artiste, Date dateEvenement, double prixEvenement, int nbreParticipantMax, int nbreParticipant) {
        this.nomEvenement = nomEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.Artiste = Artiste;
        this.dateEvenement = dateEvenement;
        this.prixEvenement = prixEvenement;
        this.nbreParticipantMax = nbreParticipantMax;
        this.nbreParticipant = nbreParticipant;
    }

    public Evenement(int idEvenement, String nomEvenement, String descriptionEvenement, String Artiste, Date dateEvenement, double prixEvenement, int nbreParticipantMax, int nbreParticipant) {
        this.idEvenement = idEvenement;
        this.nomEvenement = nomEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.Artiste = Artiste;
        this.dateEvenement = dateEvenement;
        this.prixEvenement = prixEvenement;
        this.nbreParticipantMax = nbreParticipantMax;
        this.nbreParticipant = nbreParticipant;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    public String getArtiste() {
        return Artiste;
    }

    public void setArtiste(String Artiste) {
        this.Artiste = Artiste;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public double getPrixEvenement() {
        return prixEvenement;
    }

    public void setPrixEvenement(double prixEvenement) {
        this.prixEvenement = prixEvenement;
    }

    public int getNbreParticipantMax() {
        return nbreParticipantMax;
    }

    public void setNbreParticipantMax(int nbreParticipantMax) {
        this.nbreParticipantMax = nbreParticipantMax;
    }

    public int getNbreParticipant() {
        return nbreParticipant;
    }

    public void setNbreParticipant(int nbreParticipant) {
        this.nbreParticipant = nbreParticipant;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.idEvenement;
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
        return "Evenement{" + "nomEvenement=" + nomEvenement + ", descriptionEvenement=" + descriptionEvenement + ", Artiste=" + Artiste + ", dateEvenement=" + dateEvenement + ", prixEvenement=" + prixEvenement + ", nbreParticipantMax=" + nbreParticipantMax + '}';
    }

    
    
    
}