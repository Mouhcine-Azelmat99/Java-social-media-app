package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import help.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController {

	@FXML
    private TextField email_txt;

    @FXML
    private Button login_btn;

    @FXML
    private Label msg_error;

    @FXML
    private TextField password_txt;

    @FXML
    private Button register_btn;

    @FXML
    private TextField tel_txt;

    @FXML
    private TextField username_txt;

    @FXML
    void register(ActionEvent e) throws SQLException, IOException {
    	String username,password,email,tel;
    	username=username_txt.getText();
    	email=email_txt.getText();
    	tel=tel_txt.getText();
    	password=password_txt.getText();
    	if(username.isBlank() || email.isBlank() || password.isBlank()) {
    		msg_error.setText("email , username, tel,password are required");
    	}else {
    		DbController db=new DbController();
    		Connection conn = db.getConnect();
    		String query = "INSERT INTO users (username,email,password,tel) VALUES (?,?,?,?)";
    		PreparedStatement ps = conn.prepareStatement(query);
    		ps.setString(1,username);
    		ps.setString(2,email);
    		ps.setString(3,password);
    		ps.setString(4,tel);
    		int result = ps.executeUpdate();
    		if(result > 0) {
    			SceneController sc=new SceneController();
    			sc.switchScene(e, "login");
    		}else {
    			JOptionPane.showMessageDialog(null, "Register Failed");
    		}
    	}
    }

    @FXML
    void toSignup(ActionEvent e) throws IOException {
    	SceneController sc=new SceneController();
		sc.switchScene(e, "login");
    }

}
