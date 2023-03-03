/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.auth;

import Entities.User;
import Service.AuthentificationService;
import Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import Utils.ConnectionUtils;
import Utils.UserSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.captcha.Captcha;

/**
 * FXML Controller class
 *
 * @author abirk
 */
public class LoginController implements Initializable {
    @FXML
    private TextField txtuname;
    @FXML
    private TextField txtpass;
    Connection cnx;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Button signup;
    private Label captchahere;
    private Label messagebtn;
    private TextField textarea;
    @FXML
    private Button btnok;
    @FXML
    private Button ForgetPassword;

    //private void login(ActionEvent event) throws SQLException {
      //  AuthentificationService authService = new AuthentificationService();
       // Connection cnx = ConnectionUtils.getInstance().getCnx();
       // // System.out.println("i'm here " + cnx);
       // String user_Name = txtuname.getText();
       // String password = txtpass.getText();

       // if (user_Name.equals("") && password.equals("")) {
        //    JOptionPane.showMessageDialog(null, "UserName or Paswword blank");
       // } else {

          //  String requete = "SELECT * FROM user WHERE username = ?";
          //  PreparedStatement statement = cnx.prepareStatement(requete);
           // statement.setString(1, user_Name);
            //ResultSet rs = statement.executeQuery();

//Class.forName("com.mysql.jdbc.Driver");
            //cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_user","root","");
            //pst=cnx.prepareStatement("select * from `user` where username=? and password=?");
            // pst.setString(3, user_Name);
            // pst.setString(4, password);
            //rs = pst.executeQuery();
           // if (rs.next()) {
                //if (!authService.checkPasswd(password, rs.getString(5))) {
                    //JOptionPane.showMessageDialog(null, "Wrong credentials.");

                //} else {
                   // User user = new User();
                   // user.setId_user(rs.getInt(1));
                   // user.setFirst_Name(rs.getString(2));
                   // user.setLast_Name(rs.getString(3));
                   // user.setUser_Name(rs.getString(4));
                   // user.setPassword(rs.getString(5));
                   // user.setEmail(rs.getString(6));
                   // user.setPhone_number(rs.getInt(7));
                   // user.setGender(rs.getString(8));
                   //user.setRole(rs.getString(9));

                  //  UserSession userSession = UserSession.getInstace(user);

                    //redirect to captcha page
                  ///  String pageName = "captcha.fxml";
                   // try {
                      //  Node node = (Node) event.getSource();
                       // Stage stage = (Stage) node.getScene().getWindow();
                      //  Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource(pageName)));
                      //  stage.setScene(scene);
                       // stage.show();

                    //} catch (IOException ex) {
                      //  System.err.println(ex.getMessage());
                   // }
               // }

            // else {

             /// JOptionPane.showMessageDialog(null, " Login failed  ");
               /// txtuname.setText("");
               // txtpass.setText("");
               // /txtuname.requestFocus();

           // }

            //Class.forName("com.mysql.jdbc.Driver");
            // cnx = DriverManager.getConnection(URL, USER, PWD);
            //} catch(ClassNotFoundException ex) {
            // Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
       // }

   // }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //User u=su.login(user_name,password,email);
        //input_Username.setText(u.getUser_Name()); 
        // input_Password.setText(u.getPassword());  
        // input_Email.setText(u.getEmail());  
    }

    @FXML
    private void signup(ActionEvent event) {

        loadPage(event, "signup_page.fxml");
    }

    private void loadPage(ActionEvent event, String pageName) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource(pageName)));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void captchachecker(ActionEvent event) {

        String userinput = textarea.getText();
        if (userinput.equals(String.valueOf(captchahere.getText()))) {
            if (!(userinput.equals("captcha"))) {
                messagebtn.setText("Success");
                messagebtn.setTextFill(Color.GREEN);
            } else {
                messagebtn.setText("please click on new captcha");
                messagebtn.setTextFill(Color.RED);
            }
        } else {
            messagebtn.setText("Fail");
            messagebtn.setTextFill(Color.RED);
        }
    }

    private void setcaptcha(ActionEvent event) {

        char data[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int max = 61;
        String captchastring = "";
        int min = 0;
        for (int i = 0; i < 6; i++) {
            int randomnumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
            captchastring += data[randomnumber];
        }
        System.out.println(captchastring);
        captchahere.setText(captchastring);
        captchahere.setTextFill(Color.GREEN);
    }
    //  private void handleButtonReset(ActionEvent event) {
    //              captcha = setCaptcha();
    //     code.setText("");

    //}
    private String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    @FXML
    private void Login(ActionEvent event) throws SQLException  {
        
        AuthentificationService authService = new AuthentificationService();
        Connection cnx = ConnectionUtils.getInstance().getCnx();
        // System.out.println("i'm here " + cnx);
        String user_Name = txtuname.getText();
        String password = txtpass.getText();

        if (user_Name.equals("") && password.equals("")) {
            JOptionPane.showMessageDialog(null, "UserName or Paswword blank");
        } else {

            String requete = "SELECT * FROM user WHERE username = ?";
            PreparedStatement statement = cnx.prepareStatement(requete);
            statement.setString(1, user_Name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (!authService.checkPasswd(password, rs.getString(5))) {
                    JOptionPane.showMessageDialog(null, "Wrong credentials.");

                } else {
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

                    UserSession userSession = UserSession.getInstace(user);
        String pageName = "captcha.fxml";
                    try {
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("captcha.fxml")));
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                }

            } else {

                JOptionPane.showMessageDialog(null, " Login failed  ");
                txtuname.setText("");
                txtpass.setText("");
                txtuname.requestFocus();

            }

            //Class.forName("com.mysql.jdbc.Driver");
            // cnx = DriverManager.getConnection(URL, USER, PWD);
            //} catch(ClassNotFoundException ex) {
            // Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void ForgetPassword(ActionEvent event) {
        loadPage(event, "ForgetPassword.fxml"); 
    }

   
    

}
