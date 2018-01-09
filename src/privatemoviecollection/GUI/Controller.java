/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author mr.Andersen
 */
public class Controller implements Initializable 
{    
    @FXML
    private ComboBox<String> selectGenre;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        selectGenre.getItems().removeAll(selectGenre.getItems());
        selectGenre.getItems().addAll("Action", "Drama","Crime", "Sci-Fi", "Crime", 
                                    "Western", "Horror", "Animation", "Thriller",
                                    "War");
        //selectGenre.getSelectionModel().select("Triangle");
    }

    @FXML
    private void playMovie(ActionEvent event) throws IOException 
    {
        File file = new File("");
        Desktop desktop = Desktop.getDesktop();
        file = new File("C:\\Users\\mr.Andersen\\Downloads\\Rogue.One.2016.720p.BluRay.x264-SPARKS\\rogue.one.2016.720p.bluray.x264-sparks//Rogue.One.2016.720p.BluRay.x264-SPARKS");
        desktop.open(file);
    }
    
        //allows the user to close the program, and does a pop-up making sure the user actually wants to
    @FXML
    private void closeProgram(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit this awesome mp3 player?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            Platform.exit();
        } 
    } 
    
    
}
