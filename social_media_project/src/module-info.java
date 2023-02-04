module social_media_project {
	requires javafx.controls;
	requires javafx.fxml;
	opens controllers to javafx.fxml ;
	opens images to javafx.fxml ;
	requires java.sql;
	requires java.desktop;
	requires javafx.graphics;
//	opens models to javafx.fxml ;
//	opens views to javafx.fxml ;
	opens application to javafx.graphics, javafx.fxml;
}
