/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import privatemoviecollection.BE.CategoryID;
import privatemoviecollection.BE.PrivateMovieCollection;
import privatemoviecollection.BLL.BLLManager;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class AddGenreController implements Initializable {

    @FXML
    private TextField txtAddGenre;
    @FXML
    private Button addGenre;
    @FXML
    private Button removeGenre;
    @FXML
    private ListView<String> genreListView;
    
    private ObservableList<String> genrelist
            = FXCollections.observableArrayList();
 
    BLLManager Bll = new BLLManager();
    CategoryID category = new  CategoryID();
    private Model model;
    @FXML
    private Button cancelButton;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO   
    }    


    @FXML
    private void removeGenre(ActionEvent event) 
    {
        try
        {
            model.removeGenre( genreListView.getSelectionModel().getSelectedItem().trim());
            setGenre();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void addGenre(ActionEvent event) 
    {
        try
        {    
            category.setCategory(txtAddGenre.getText());
            model.addGenre(category);
            setGenre(); 
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void setGenre()
    {
        genreListView.getItems().clear();
        for (CategoryID categoryID : model.allGenre()) 
        {
           genrelist.add(categoryID.getCategory());
        }
        genreListView.setItems(genrelist);
    }

    void setModel(Model model) 
    {
        this.model=model;
        setGenre();
    }

    @FXML
    private void cancelGenre(ActionEvent event) 
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
}
