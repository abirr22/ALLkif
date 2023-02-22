/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author abirk
 */
public interface IAuthentificationService {
    public void signup(String username, String email, String password);
    public void login( String email, String password);
    public boolean isUserExist(String username,String email); 
}
