/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author siwar
 */
public class AvisEvenement {
     private int idEvenement, idAvisE;
     private String Avis;

    public AvisEvenement() {
    }

    public AvisEvenement(String Avis) {
        this.Avis = Avis;
    }

    public AvisEvenement(int idEvenement, int idAvisE, String Avis) {
        this.idEvenement = idEvenement;
        this.idAvisE = idAvisE;
        this.Avis = Avis;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public int getIdAvisE() {
        return idAvisE;
    }

    public void setIdAvisE(int idAvisE) {
        this.idAvisE = idAvisE;
    }

    public String getAvis() {
        return Avis;
    }

    public void setAvis(String Avis) {
        this.Avis = Avis;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idAvisE;
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
        final AvisEvenement other = (AvisEvenement) obj;
        if (this.idAvisE != other.idAvisE) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvisEvenement{" + "Avis=" + Avis + '}';
    }

  

     
}
