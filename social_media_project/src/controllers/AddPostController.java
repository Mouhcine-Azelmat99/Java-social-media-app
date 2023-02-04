package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import help.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddPostController {

    @FXML
    private Button add_btn;

    @FXML
    private TextArea content_txt;

    @FXML
    private Button img_upload;

    @FXML
    private Label msg_error;

    @FXML
    private TextField title_txt;
    
    //navbar
    
    @FXML
    private Button btn_to_home;

    @FXML
    private Button btn_to_messages;

    @FXML
    private Button btntoProfile;
    
    String pathOfImage = "";

    @FXML
    void addpost(ActionEvent e) throws SQLException, IOException {
    	String title,content;
    	title=title_txt.getText();
    	content=content_txt.getText();
    	int user_id =ProfileController.user_id;
    	if(title.isEmpty() || title.isEmpty()) {
    		msg_error.setText("title and content are required !!!");
    	}else {
    		DbController db=new DbController();
    		Connection conn = db.getConnect();
    	
    		
    		if(pathOfImage.isBlank()) {
    			String query = "INSERT INTO posts (title,content,user_id) VALUES (?,?,?)";
        		PreparedStatement ps = conn.prepareStatement(query);
        		ps.setString(1,title);
        		ps.setString(2,content);
        		ps.setInt(3,user_id);
        		int result = ps.executeUpdate();
        		if(result > 0) {
        			SceneController sc=new SceneController();
        			sc.switchScene(e, "home");
        		}else {
        			JOptionPane.showMessageDialog(null, "posts not added");
        		}
    		}else {
    			String query = "INSERT INTO posts (title,content,img,user_id) VALUES (?,?,?,?)";
        		PreparedStatement ps = conn.prepareStatement(query);
        		ps.setString(1,title);
        		ps.setString(2,content);
//    			InputStream in = new FileInputStream(new File(pathOfImage));
    			ps.setString(3, pathOfImage);
        		ps.setInt(4,user_id);
        		int result = ps.executeUpdate();
        		if(result > 0) {
        			SceneController sc=new SceneController();
        			sc.switchScene(e, "home");
        		}else {
        			JOptionPane.showMessageDialog(null, "posts not added");
        		}
    		}
		}
    }
    
//    FileChooser fch=new FileChooser();
//    FileInputStream fins=new FileInputStream();
        
    @FXML
    private Label filepath;
    
    @FXML
    void upload_img(ActionEvent event) {
        JFileChooser fch = new JFileChooser();
        fch.setCurrentDirectory(new File(System.getProperty("user.home")));

//    	File file = fch.showOpenDialog(new Stage());
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","png","gif");
    	fch.addChoosableFileFilter(filter);
    	int resultfile = fch.showSaveDialog(null);
    	if (resultfile== JFileChooser.APPROVE_OPTION) {
    		File selectedfile = fch.getSelectedFile();
    		String path = selectedfile.getAbsolutePath();
    		System.out.println(path);
    		if(!path.isEmpty()){
    			this.pathOfImage=path;
    			filepath.setText("image : "+path);
    		}
    	}
    }

    
//Navbar functions
    
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
