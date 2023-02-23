/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entities.Artiste;
import Entities.User;
import Utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level; 
import java.util.logging.Logger;
import Entities.Client;
import java.text.SimpleDateFormat;
 
import java.sql.Statement;
import static javafx.scene.input.KeyCode.T;


/**
 *
 * @author abirk
 */
public class ServiceUser implements Iservice<User> {
    
   Connection cnx = ConnectionUtils.getInstance().getCnx();
    
   
    public void ajouter(User user) throws SQLException {
        
      
      String role="Admin";
      if(user instanceof Client)
        role="Client";
      else if (user instanceof Artiste){
               role="Artiste";
       }
       String req = "INSERT INTO `user` (`first_name`, `last_name`,`username`,`password`,`email`,`phone_number`,`gender`,`role`) "
               + "VALUES ('" + user.getFirst_Name() + "', '" + user.getLast_Name()
               + "', '" + user.getUser_Name() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', '"
                + user.getPhone_number() + "', '" + user.getGender() +  "' , '" + user.getRole() + "');";
        Statement st = cnx.createStatement();
       st.executeUpdate(req);
        System.out.println("Utilisateur ajouté");
   }

    
    public void ajouter2(User  user ) throws SQLException{
        String req = "INSERT INTO `user` (`first_name`, `last_name`,`username`,password`,`email`,`phone_number`,`gender`,`role`) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, user.getFirst_Name());
        ps.setString(2, user.getLast_Name());
        ps.setString(3, user.getUser_Name());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getEmail());
        
        ps.setInt(6, user.getPhone_number());
        ps.setString(7, user.getGender());
        ps.setString(8, user.getRole());

        ps.executeUpdate();
    }
   public void supprimer(int id) throws SQLException{
        String req = "DELETE FROM `user` WHERE id_user ="+id;
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    
   public void modifier(String s,String s2,String s3,String s4, int s5 ,String s6,String s7, int id ) throws SQLException{
        String req = "UPDATE `user` SET `first_name` = '"+s+"',`last_name`= '"+s2+"',`username`='"+s3+"',`email`='"+s4+"',`phone_number`='"+s5+"',`gender`='"+s6+"',`role`='"+s7+"' WHERE `id_user` = "+id;
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
   
       public User afficher(int id) throws SQLException
       {
            User p = new User();
            String req = "SELECT * FROM `user` WHERE id_user = "+id+"";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);
            while (rs.next()) {
                p.setFirst_Name(rs.getString("first_Name"));
                p.setLast_Name(rs.getString("last_Name"));
                p.setUser_Name(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setEmail(rs.getString("email"));
                p.setPhone_number(rs.getInt("phone_number"));
                p.setGender(rs.getString("gender")); 
                p.setRole(rs.getString("role")); 
                
            }
            return p;
       }

   public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u ;
               if(rs.getString("role").equals("Client)"))
            {
                u= new Client(rs.getInt("id_user"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password"),rs.getString("email"),rs.getInt("phone_number"),rs.getString("gender"),rs.getString("role"));

            }
            else if (rs.getString("role").equals("Artiste"))
            {
                u= new Artiste(rs.getInt("id_user"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password"),rs.getString("email"),rs.getInt("phone_number"),rs.getString("gender"),rs.getString("role"));
            }
            else {
                 u = new User(rs.getInt("id_user"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password"),rs.getString("email"),rs.getInt("phone_number"),rs.getString("gender"),rs.getString("role"));
                //list.add(user);
                }
                list.add(u);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
   public User getOneById(int id) {
        User p = null;
        try {
            String req = "Select * from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p = new User( rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    } 
}