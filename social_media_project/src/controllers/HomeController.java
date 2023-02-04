package controllers;

import java.awt.Color;
//import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
//import java.awt.Insets;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Blob;

import javafx.scene.layout.StackPane;

import help.SceneController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.Post;

public class HomeController {
	

    @FXML
    private ImageView like_btn;

    @FXML
    private Label post_content;

    @FXML
    private Label post_date;

    @FXML
    private ImageView post_img;

    @FXML
    private Label post_title;

    @FXML
    private Label post_user;

    @FXML
    private VBox posts_vbox;

    @FXML
    private ImageView save_btn;

    @FXML
    private Button toAdd_btn;
    
    //navbar
    @FXML
    private Button btn_to_home;

    @FXML
    private Button btn_to_messages;

    @FXML
    private Button btntoProfile;

    @FXML
    void ToAddScreen(ActionEvent e) throws IOException {
    	SceneController sc=new SceneController();
		sc.switchScene(e, "addPost");
    }
    
//    @Override
    public void initialize() throws SQLException, MalformedURLException {
    	DbController db=new DbController();
		Connection conn = db.getConnect();
		String query = "SELECT title,content,img,username,created_at,likes,posts.id FROM posts INNER JOIN users ON users.id=posts.user_id ORDER BY created_at DESC";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		String title,content,username,img,date;
		int likes;
		int post_id;
		while(result.next()) {
//			byte [] img = result.getBytes(3);
			title=result.getString(1);
			content=result.getString(2);
			img=result.getString(3);
			username=result.getString(4);
			date=result.getString(5);
			likes=result.getInt(6);
			post_id=result.getInt(7);
			this.addToScreen(title, content, img, username, date,likes,post_id);
		}
    }
    
    private void addToScreen(final String title,final String content,final String image,final String username,final String date,int likes,final int post_id) throws MalformedURLException {
    	HBox hbb=new HBox();
    	final VBox vb=new VBox();
    	vb.setPrefWidth(510);
    	final VBox vbPostText=new VBox();
    	vbPostText.setStyle("-fx-background-radius: 0;");
    	vbPostText.setStyle("-fx-background-color: #ffff;");
    	vbPostText.getStyleClass().add("color-palette");
//    	vbPostText.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
    	Label post_content = new Label();
    	Label post_date = new Label();
    	Label post_title = new Label();
    	Label post_user = new Label();
    	post_user.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
    	post_title.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
//    	vbPostText.setPrefHeight(500);
    	post_content.setWrapText(true);
//    	post_content.setMaxHeight(500);
//    	post_content.setPrefHeight(300);
    	post_content.setText(content);
    	post_date.setText(date);
    	post_title.setText(title);
    	post_user.setText(username);
    	
    	vbPostText.getChildren().addAll(post_user,post_date,post_title,post_content);
    	
    	VBox likesave = new VBox();
    	likesave.setAlignment(Pos.TOP_CENTER);
    	Button like = new Button("like");
    	Button show = new Button("show");
    	like.setOnMouseClicked(new EventHandler<Event>() {
    		public void handle(Event e) {
    			DbController db=new DbController();
    			Connection conn = db.getConnect();
    			System.out.println(post_id);
    			String query = "UPDATE posts SET likes = likes+1 WHERE posts.id="+Integer.toString(post_id);
    			PreparedStatement ps = null;
				try {
					ps = conn.prepareStatement(query);
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
        			JOptionPane.showMessageDialog(null, "posts is liked");
        			SceneController sc=new SceneController();
        			try {
						sc.switchScene(e, "home");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}else {
        			JOptionPane.showMessageDialog(null, "posts not liked");
        		}
   	      }

   	    });

    	Button save =new Button("save");
    	
    	Label like_label =new Label(Integer.toString(likes));
    	like_label.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
    	like_label.setAlignment(Pos.CENTER);
    	likesave.getChildren().add(like);
    	likesave.getChildren().add(like_label);
    	likesave.getChildren().add(save);
    	if (image!=null && !image.isBlank()) {
        	final VBox vbImg=new VBox();
        	Image img = new Image(new File(image).toURI().toURL().toExternalForm());
        	ImageView post_img = new ImageView(img);
        	post_img.setFitWidth(530);
        	post_img.setFitHeight(300);
        	vbImg.getChildren().addAll(post_img);
        	vb.getChildren().addAll(vbPostText,vbImg);
    	}else {
        	vb.getChildren().add(vbPostText);
    	}
    	VBox commentVb =new VBox();
		commentVb.setSpacing(10);
		commentVb.setPadding(new Insets(15));
		Label commentLabel =new Label("Comments");
		commentLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
		commentVb.setStyle("-fx-background-color: #ffff;");
		commentVb.getChildren().add(commentLabel);
    	vb.getChildren().add(commentVb);
    	HBox comment_input = new HBox();
    	final TextField comment_text=new TextField();
    	comment_text.setPromptText("add new comment");
    	comment_text.setPrefWidth(400);
    	comment_text.setPrefHeight(30);
    	Button comment_btn = new Button("Comment");
    	comment_input.getChildren().addAll(comment_text,comment_btn);
    	commentVb.getChildren().add(comment_input);
    	
    	comment_btn.setOnMouseClicked(new EventHandler<Event>() {
    		public void handle(Event e) {
    			DbController db=new DbController();
        		Connection conn = db.getConnect();
        		String query = "INSERT INTO comments (user_id,post_id,content) VALUES (?,?,?)";
        		PreparedStatement ps = null;
				try {
					ps = conn.prepareStatement(query);
					ps.setInt(1,ProfileController.user_id);
					ps.setInt(2,post_id);
					ps.setString(3,comment_text.getText());
					int result = ps.executeUpdate();
            		if(result > 0) {
            			JOptionPane.showMessageDialog(null, "comment added successfully");
            			SceneController sc=new SceneController();
            			try {
            				sc.switchScene(e, "home");
    					} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
            		}else {
            			JOptionPane.showMessageDialog(null, "comment cannot added");
            		}
    		}catch(SQLException e1) {
    			e1.printStackTrace();
    		}
    		}
    	});
//    	select comment to list 
    	DbController db=new DbController();
		Connection conn = db.getConnect();
		String query = "SELECT content,username FROM comments INNER JOIN users ON users.id=comments.user_id where post_id="+post_id+" ORDER BY created_at DESC";
		Statement statement = null;
		try {
			statement = conn.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ResultSet result = null;
		try {
			result = statement.executeQuery(query);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String content_comment,username_comment;;
		try {
			while(result.next()) {
				content_comment=result.getString(1);
				username_comment=result.getString(2);
				HBox comment_item =new HBox();
				comment_item.setAlignment(Pos.CENTER_LEFT);
				comment_item.setPadding(new Insets(10));
				comment_item.setStyle("-fx-background-color: #eeee;");
				Label username_comment_label=new Label(username_comment+" :  ");
				username_comment_label.setFont(Font.font("Calibri", FontWeight.BOLD, 12));
				Label content_comment_label=new Label(content_comment);
				content_comment_label.setWrapText(true);
				comment_item.getChildren().addAll(username_comment_label,content_comment_label);
				commentVb.getChildren().add(comment_item);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//    	comment.setOnMouseClicked(new EventHandler<Event>() {
//    		public void handle(Event e) {
//    			
//
//            	
//        		
//    		}
//    	});
    	hbb.getChildren().addAll(vb,likesave);
//    	hbb.getChildren().add(hb);
    	posts_vbox.getChildren().add(hbb);

		System.out.println(Post.title);
		show.setOnMouseClicked(new EventHandler<Event>() {
    		public void handle(Event e) {
    		   	Post.title=title;
    			Post.content=content;
    			Post.created_at=date;
    			Post.user=username;
    			Post.img=image;
//    	    	 System.out.println(title);
//    	    	 System.out.println(title);
    	    	  SceneController sc=new SceneController();
    	  			try {
						sc.switchScene(e, "showpost");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	      }
    	    });
    }
    
    //Navbar functions
    
    @FXML
    void toProfile(ActionEvent e) throws IOException {
    	System.out.println("user id :"+Integer.toString(ProfileController.user_id));
    	SceneController sc=new SceneController();
		sc.switchScene(e, "profile");
		
    }
    
    @FXML
    void to_home(ActionEvent e) throws IOException {
    	SceneController sc=new SceneController();
		sc.switchScene(e, "home");
    }

    @FXML
    void to_messages(ActionEvent e) {

    }
    @FXML
    void to_friends(ActionEvent e) throws IOException {
    	SceneController sc=new SceneController();
		sc.switchScene(e, "freinds");
    }

}
