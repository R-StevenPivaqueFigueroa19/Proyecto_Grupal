package application;

import java.util.List;

import demo_jdbc.models.Constructors;
import demo_jdbc.respositories.ConstructorsRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ConstructorsRepository constructorsRepository = new ConstructorsRepository();
    private TableView<Constructors> tableView = new TableView<>();
    private ComboBox<Integer> yearComboBox = new ComboBox<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Formula 1 Constructors Results");

        // ComboBox para seleccionar el año
        List<Integer> years = constructorsRepository.getAvailableYears();
        yearComboBox.setItems(FXCollections.observableArrayList(years));
        yearComboBox.setOnAction(e -> updateTable(yearComboBox.getValue()));

        // Label para el ComboBox
        Label yearLabel = new Label("SELECCIONE EL AÑO QUE DESEA CONSULTAR: ");
        HBox hbox = new HBox(10, yearLabel, yearComboBox);

        // TableView para mostrar los resultados de los constructores
        TableColumn<Constructors, String> nameColumn = new TableColumn<>("Constructor Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Constructors, Integer> winsColumn = new TableColumn<>("Wins");
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<Constructors, Integer> totalPointsColumn = new TableColumn<>("Total Points");
        totalPointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

        TableColumn<Constructors, Integer> rankColumn = new TableColumn<>("Rank");
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

        tableView.getColumns().addAll(nameColumn, winsColumn, totalPointsColumn, rankColumn);

        // Layout
        VBox vbox = new VBox(10, hbox, tableView);
        vbox.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(vbox, 600, 400));
        primaryStage.show();
    }

    private void updateTable(int year) {
        List<Constructors> results = constructorsRepository.getConstructorsByYear(year);
        ObservableList<Constructors> data = FXCollections.observableArrayList(results);
        tableView.setItems(data);
    }

    public static void main(String[] args) {
        launch(args);
    }
}




