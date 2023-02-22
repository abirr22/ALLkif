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

    public interface IServiceCommande <T>{
    public void ajouter(T c);
    public void supprimer(int id);
    public void modifier(T c);
    public List<T> getAll();
    public T getOneById(int id);
}
    

