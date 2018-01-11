/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 *
 * @author kasper
 */
public class alertWindowController {

    @FXML
    private Button saveGenre;
    @FXML
    private Button removeMovie;
    @FXML
    private ListView<?> genreListView;
    private Model model;


    @FXML
    private void saveBtn(ActionEvent event) {
    }

    @FXML
    private void removeMovie(ActionEvent event) {
    }
    
     void setModel(Model model) {
        this.model=model;
    
    }
    
}
