/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import java.util.List;

/**
 *
 * @author zayat
 * @param <T>
 */

    public interface IService <T>{
    public void ajouter(T p);
    public void supprimer(int id,int id_panier);
    public void modifier(T p);
    public List<T> getAll();
    public T getOneById(int id);
}
    

