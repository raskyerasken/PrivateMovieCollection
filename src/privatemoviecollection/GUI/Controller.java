/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private ObservableList<String> allListBox;
    private ListView<String> listBox;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //allListBox = FXCollections.observableArrayList();
        //listBox.setItems(allListBox);
        selectGenre.getItems().removeAll(selectGenre.getItems());
        selectGenre.getItems().addAll("Triangle", "Circle","Rectangle");
        selectGenre.getSelectionModel().select("Triangle");
    }    
    
}
