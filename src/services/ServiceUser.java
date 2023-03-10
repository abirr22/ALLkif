/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Artiste;
import entities.User;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Client;
import org.mindrot.jbcrypt.BCrypt;
import java.text.SimpleDateFormat;

import java.sql.Statement;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author abirk
 */
public class ServiceUser implements Iservice<User> {

    Connection cnx = MyConnection.getTest().getCnx();

    public void ajouter(User user) throws SQLException {

        String role = "Admin";
        if (user instanceof Client) {
            role = "Client";
        } else if (user instanceof Artiste) {
            role = "Artiste";
        }
        String req = "INSERT INTO `user` (`first_name`, `last_name`,`username`,`password`,`email`,`phone_number`,`gender`,`role`) "
                + "VALUES ('" + user.getFirst_Name() + "', '" + user.getLast_Name()
                + "', '" + user.getUser_Name() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', '"
                + user.getPhone_number() + "', '" + user.getGender() + "' , '" + user.getRole() + "');";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Utilisateur ajouté");
    }

    public void ajouter2(User user) throws SQLException {
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

    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM `user` WHERE id_user =" + id;
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    public void modifier(String s, String s2, String s3, String s4, int s5, String s6, String s7, int id) throws SQLException {
        String req = "UPDATE `user` SET `first_name` = '" + s + "',`last_name`= '" + s2 + "',`username`='" + s3 + "',`email`='" + s4 + "',`phone_number`='" + s5 + "',`gender`='" + s6 + "' WHERE `id_user` = " + id;
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("modifier");
    }

    public User afficher(int id) throws SQLException {
        User p = new User();
        String req = "SELECT * FROM `user` WHERE id_user = " + id + "";
        PreparedStatement ps = cnx.prepareStatement(req); //preparestatment pour les requette bel parametre 
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
                User u;
                if (rs.getString("role").equals("Client)")) {
                    u = new Client(rs.getInt("id_user"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("phone_number"), rs.getString("gender"), rs.getString("role"));

                } else if (rs.getString("role").equals("Artiste")) {
                    u = new Artiste(rs.getInt("id_user"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("phone_number"), rs.getString("gender"), rs.getString("role"));
                } else {
                    u = new User(rs.getInt("id_user"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("phone_number"), rs.getString("gender"), rs.getString("role"));
                    //list.add(user);
                }
                list.add(u);
                //return list;
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
            Statement st = cnx.createStatement(); //pour les requette sans parametre
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                p = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

    public boolean checkEmailExists(String email) {
    Connection cnx = MyConnection.getTest().getCnx();
        boolean result = false;

        try {
            String req = "SELECT * FROM user WHERE email = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return result;
    }

    // public boolean checkIfUserCanBeAdded(User u) {
    // Vérifier que l'adresse email n'est pas déjà utilisée par un autre utilisateur
    //String checkEmailQuery = "SELECT COUNT(*) FROM user WHERE email=?";
    //try (PreparedStatement checkEmailStmt = cnx.prepareStatement(checkEmailQuery)) {
    //  checkEmailStmt.setString(1, u.getEmail());
    // ResultSet rs = checkEmailStmt.executeQuery();
    // if (rs.next() && rs.getInt(1) > 0) {
    // return false;
    //}
    // } catch (SQLException ex) {
    // Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
    /// return false;
    // }
    // return true;
///}
    public List<User> selectBy(String attribute, String value) throws SQLException {
        String sql = "SELECT * FROM `user` where " + attribute + "= ?";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setString(1, value);
        ResultSet rs = pstmt.executeQuery();
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId_user(rs.getInt(1));
            user.setFirst_Name(rs.getString(2));
            user.setLast_Name(rs.getString(3));
            user.setUser_Name(rs.getString(4));
            user.setPassword(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setPhone_number(rs.getInt(7));
            user.setGender(rs.getString(8));
            user.setRole(rs.getString(9));

        }
        return userList;
    }
public void updatePassword( User user) throws SQLException {
        System.out.println(user.getPassword());
        String sql="UPDATE `user` SET `PASSWORD`=? WHERE `ID_USER`=?";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setString(1,user.getPassword());
        pstmt.setInt(2,user.getId_user());
        pstmt.executeUpdate();
        System.out.println(user.getPassword());
    }

 public void ResetPaswword(String email, String password) {
        try {

            String req = "UPDATE user SET password = ? WHERE email = ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, password);
            ps.setString(2, email);

            ps.executeUpdate();
            System.out.println("Password updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }



//public void editUserPassword(User user) throws SQLException {
      //  user.setPassword(hashPasswd(user.getPlainPassword()));
        //updatePassword(user);
    //}

    public int ChercherMail(String email) {
        try {
            String req = "SELECT * from `user` WHERE `user`.`email` ='" + email + "'  ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString("email").equals(email)) {
                    System.out.println("mail trouvé ! ");
                    return 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
//To change body of generated methods, choose Tools | Templates.
    }

}
