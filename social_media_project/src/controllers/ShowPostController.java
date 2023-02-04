package controllers;

//import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import help.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Post;

public class ShowPostController {

    @FXML
    private Button btn_to_home;

    @FXML
    private Button btn_to_messages;

    @FXML
    private Button btntoProfile;

    @FXML
    private Label content;

    @FXML
    private Label date;

    @FXML
    private ImageView img;

    @FXML
    private Label title;

    @FXML
    private Label username;
    
    public void initialize() throws SQLException, MalformedURLException {
    	title.setText(Post.title);
    	username.setText(Post.user);
    	date.setText(Post.created_at);
    	content.setText(Post.content);
//    	img.setImage(new File(Post.img));
        File file = new File(Post.img);
    	Image image=new Image(file.toURI().toString());
    	img.setImage(image);
    }

    @FXML
    void toProfile(ActionEvent e) throws IOException {
    	SceneController sc=new SceneController();
		sc.switchScene(e, "profile");
    }

    @FXML
    void to_home(ActionEvent e) throws IOException {
    	SceneController sc=new SceneController();
		sc.switchScene(e, "home");
    }

    @FXML
    void to_messages(ActionEvent event) {

    }
    @FXML
    void to_friends(ActionEvent e) throws IOException {
    	SceneController sc=new SceneController();
		sc.switchScene(e, "freinds");
    }

}
