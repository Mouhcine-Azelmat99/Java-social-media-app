package controllers;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import help.SceneController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import models.Post;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;

public class FreindController {

    @FXML
    private Button btn_to_home;

    @FXML
    private Button btn_to_messages;

    @FXML
    private Button btntoProfile;

    @FXML
    private Button search_btn;

    @FXML
    private TextField search_input;

    @FXML
    private Label username;
    
    @FXML
    private VBox friend_vbox;
    
    @FXML
    private VBox my_friend_vbox;

    @FXML
    void search(ActionEvent event) throws SQLException, MalformedURLException {
    	String search_value=search_input.getText();
    	if(!search_value.isBlank()) {
    		my_friend_vbox.getChildren().removeAll(my_friend_vbox.getChildren());
    		friend_vbox.getChildren().removeAll(friend_vbox.getChildren());
    		String query = "SELECT DISTINCT id,username FROM users where id!="+ProfileController.user_id+" and id not in (select friend_id from friend) and username='"+search_value+"' group by username";
//    		System.out.println(query);
    		DbController db=new DbController();
    		Connection conn = db.getConnect();
    		Statement statement = conn.createStatement();
    		ResultSet result = statement.executeQuery(query);
    		while(result.next()) {
    			String username;
    			int friend_id;
    			friend_id=result.getInt(1);
    			username=result.getString(2);
    			System.out.println(username);
    			this.addToScreen(username,friend_id);
    		}

    	}else {
    		this.initialize();
    	}
    }
    
    public void initialize() throws SQLException, MalformedURLException {
    	DbController db=new DbController();
		Connection conn = db.getConnect();
		String query = "SELECT DISTINCT users.id,username FROM friend right join users on users.id=friend.user_id where friend.user_id is null group by username";
//		String query = "SELECT id,username FROM users where id!="+ProfileController.user_id+" and id not in (select friend_id from friend)";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		while(result.next()) {
			String username;
			int friend_id;
			friend_id=result.getInt(1);
			username=result.getString(2);
			System.out.println(username);
			this.addToScreen(username,friend_id);
		}
		
		String q = "SELECT username FROM friend Inner join users on users.id=friend.friend_id where friend.user_id="+ProfileController.user_id;
		Statement st = conn.createStatement();
		ResultSet r = st.executeQuery(q);
		while(r.next()) {
			String username_value=r.getString(1);
			System.out.println(username_value);
			this.addToScreen(username_value);
		}
    }
    
    private void addToScreen(String username) throws MalformedURLException {
    	HBox hb=new HBox();
    	hb.setPadding(new Insets(10));
//    	hb.setBackground(new Background(new BackgroundFill(Color.BLUE,null,null)));
    	hb.setStyle("-fx-background-color: #eeee;");
    	hb.setAlignment(Pos.CENTER_LEFT);
    	hb.setSpacing(30);
    	hb.setPrefWidth(538);
    	hb.setPrefHeight(50);
    	Label username_label = new Label(username);
    	username_label.setFont(new Font(20));
    	Image img = new Image(new File("C:\\Users\\HP 840\\Desktop\\JAVA\\java-learning\\social_media_project\\src\\images\\profile.png").toURI().toURL().toExternalForm());
    	ImageView user_img = new ImageView(img);
    	user_img.setFitWidth(61);
    	user_img.setFitHeight(60);
    	hb.getChildren().addAll(user_img,username_label);
		my_friend_vbox.getChildren().add(hb);

    }
    
    private void addToScreen(String username,final int friend_id) throws MalformedURLException {
    	HBox hb=new HBox();
    	hb.setPadding(new Insets(10));
//    	hb.setBackground(new Background(new BackgroundFill(Color.BLUE,null,null)));
    	hb.setStyle("-fx-background-color: #eeee;");
    	hb.setAlignment(Pos.CENTER_LEFT);
    	hb.setSpacing(30);
    	hb.setPrefWidth(538);
    	hb.setPrefHeight(50);
    	Label username_label = new Label(username);
    	username_label.setFont(new Font(20));
    	Image img = new Image(new File("C:\\Users\\HP 840\\Desktop\\JAVA\\java-learning\\social_media_project\\src\\images\\profile.png").toURI().toURL().toExternalForm());
    	ImageView user_img = new ImageView(img);
    	user_img.setFitWidth(61);
    	user_img.setFitHeight(60);
    	Button add = new Button("Add");
    	add.setOnMouseClicked(new EventHandler<Event>() {
    		public void handle(Event e) {
        		DbController db=new DbController();
        		Connection conn = db.getConnect();
        		String query = "INSERT INTO friend (user_id,friend_id) VALUES (?,?)";
        		PreparedStatement ps = null;
				try {
					ps = conn.prepareStatement(query);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
        		try {
					ps.setInt(1,ProfileController.user_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		try {
					ps.setInt(2,friend_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		int result = 0;
				try {
					result = ps.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		if(result > 0) {
        			JOptionPane.showMessageDialog(null, "User add to friends");
        			SceneController sc=new SceneController();
        			try {
						sc.switchScene(e, "freinds");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

        		}else {
        			JOptionPane.showMessageDialog(null, "user not added friends list");
        		}
   	    	  
   	      }
   	    });
    	hb.getChildren().addAll(user_img,username_label,add);
		friend_vbox.getChildren().add(hb);
		
		

//    	if(friend_vbox!=null) {
//    		friend_vbox.getChildren().add(hb);
//    	}
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
