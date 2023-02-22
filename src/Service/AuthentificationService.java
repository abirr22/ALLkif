/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.User;

/**
 *
 * @author abirk
 */
public class AuthentificationService implements IAuthentificationService {
   ServiceUser userService =null;
    @Override
    public void signup(String username, String email, String password) {
          
        User user = new User("abir","khelifi",username,password,email,1245,"femme");
        user.setRole("Client");
       try{
        userService.ajouter(user);
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

    public AuthentificationService() {
        userService= new ServiceUser(); 
        
    }
    

    @Override
    public void login(String email, String password) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isUserExist(String username, String email) {
        
        
        
        
        
        return false;
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
