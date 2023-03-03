/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.produits;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ABDOU
 */
public interface IPService <T>{
    public void Ajouter(T t);
    public void Supprimer(int id);
    public void Modifier(int id,String titre,String description,String photo,Float prix);
    public ResultSet Selectionner(int id);
    public ResultSet SelectionnerSingle(int id);
    public List<produits> afficher(int id);
}
