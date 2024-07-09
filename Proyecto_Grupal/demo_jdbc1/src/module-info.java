module demo_jdbc1 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	opens demo_jdbc.models to javafx.base;
	opens application to javafx.graphics, javafx.fxml;
}
