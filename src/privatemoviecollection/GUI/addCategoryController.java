/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.sql.SQLException;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import privatemoviecollection.BE.CategoryID;
import privatemoviecollection.BLL.BLLManager;

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
    
    BLLManager Bll = new BLLManager();
    CategoryID category = new  CategoryID();
    private Model model;
    
    @FXML
    private void saveBtn(ActionEvent event) {
    }

    @FXML
    private void cancelBtn(ActionEvent event) {
    }

    @FXML
    private void removeGenre(ActionEvent event) throws SQLException {
        
        Bll.removeGenre((CategoryID) genreListView.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void addGenre(ActionEvent event) throws SQLException {
        category.setCategory(txtAddGenre.getText());
        model.addGenre(category);
    }

    void setModel(Model model) {
        this.model=model;
    }
    
    
}

