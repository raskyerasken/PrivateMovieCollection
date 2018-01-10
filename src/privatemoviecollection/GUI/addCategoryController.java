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
import javafx.scene.control.TextField;

/**
 *
 * @author kasper
 */
public class addCategoryController {

    @FXML
    private Button saveGenre;
    @FXML
    private Button cancel;
    @FXML
    private TextField txtAddGenre;
    @FXML
    private Button addGenre;
    @FXML
    private Button removeGenre;
    @FXML
    private ListView<?> genreListView;

    @FXML
    private void saveBtn(ActionEvent event) {
    }

    @FXML
    private void cancelBtn(ActionEvent event) {
    }
    
}
