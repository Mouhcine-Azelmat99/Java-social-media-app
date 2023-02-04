package controllers;

import java.io.IOException;

import help.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfileController {
	static String username;
	static String email;
	static int user_id;

	
	public void initialize() {
		
		this.username_label.setText(username);
		this.email_label.setText(email);
	}

    @FXML
    private Button backtohome;

    @FXML
    private Label email_label;

    @FXML
    private Label username_label;

    @FXML
    void toHome(ActionEvent e) throws IOException {
    	SceneController sc=new SceneController();
		sc.switchScene(e, "home");
    }

}
