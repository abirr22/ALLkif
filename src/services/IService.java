/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author khedi
 */
interface IService<T> {
    public void ajouter(T p);
    public void supprimer(int id);
    public void modifier(T p);
    public ResultSet Selection();
    public ResultSet SelectionOne(int id);

    
//    public List<T> getAll();
//    public T getOneByOne(int id);
    
}
