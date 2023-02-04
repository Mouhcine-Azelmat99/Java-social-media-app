package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import help.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label error_label;

    @FXML
    private Button login_btn;
    
    @FXML
    private Button signup_btn;
    
    @FXML
    private TextField username_txt;
    
    @FXML
    private TextField password_txt;
    
    @FXML
    private Label msg_error;

    @FXML
    void login(ActionEvent e) throws IOException, SQLException {
    	String username,password;
    	username=username_txt.getText();
    	password=password_txt.getText();
    	if(username.isEmpty() || password.isEmpty()) {
    		msg_error.setText("username and password required !!!");
    	}else {
    		DbController db=new DbController();
    		Connection conn = db.getConnect();
    		String query = "SELECT COUNT(*) FROM users WHERE username='"+username+"' AND password='"+password+"'";
    		Statement statement = conn.createStatement();
    		ResultSet result = statement.executeQuery(query);
    		while(result.next()) {
    			if (result.getInt(1) >0 ) {
    				String q = "SELECT id,email FROM users WHERE username='"+username+"' AND password='"+password+"'";
    	    		Statement st = conn.createStatement();
    	    		ResultSet r = st.executeQuery(q);
    	    		int user_id=0;
	    			String email="";
    	    		while(r.next()) {
    	    			user_id=r.getInt(1);
    	    			email=r.getString(2);
    	    		}
    				ProfileController.username=username;
    				ProfileController.email=email;
    				ProfileController.user_id=user_id;
    				SceneController sc=new SceneController();
    				sc.switchScene(e, "home");
    			}else {
    	    		JOptionPane.showMessageDialog(null, "Login Failded user not exist");
    			}
    		}
    		result.close();
    		statement.close();
    		conn.close();

    		
		}
    }
    @FXML
    void toSignup(ActionEvent e) throws IOException {
    	SceneController sc=new SceneController();
		sc.switchScene(e, "register");
    }

}
