/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ABDOU
 */
public interface ISService <T>{
    public void Ajouter(T t);
    public void Supprimer(int id);
    public void Modifier(int taux,int id);
    public ResultSet SelectionnerSingle(int id);

}
