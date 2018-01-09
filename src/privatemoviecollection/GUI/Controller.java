/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author mr.Andersen
 */
public class Controller implements Initializable 
{    
    @FXML
    private ComboBox<String> selectGenre;
    private ObservableList<String> allListBox;
    private ListView<String> listBox;
    private Stage primaryStage;
    @FXML
    private Button addMovieBtn;
    
    
//    ObservableList<String> selectGenre = 
//    FXCollections.observableArrayList(
//        "1",
//        "2",
//        "3"
//    );
//ComboBox comboBox = new ComboBox(selectGenre);  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        allListBox = FXCollections.observableArrayList();
        //listBox.setItems(allListBox);
        selectGenre.getItems().removeAll(selectGenre.getItems());
        selectGenre.getItems().addAll("Triangle", "Circle","Rectangle");
        //selectGenre.getSelectionModel().select("Triangle");
    }    
    
    
    private void loadStage(String viewName) throws IOException
    {
        primaryStage = (Stage) addMovieBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/" + viewName));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(primaryStage);

        newStage.show();
    }
    
     private void addMovie()
    {
        try
        {
            loadStage("addMovie.fxml");
        }
        catch (IOException ex)
        {
            showErrorDialog("I/O Exception", "DATASTREAM FAILED!", "The requested view could not be loaded.");
        }
    }
     
     private void showErrorDialog(String title, String header, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
