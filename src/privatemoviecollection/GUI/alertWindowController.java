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
import javafx.scene.control.ListView;
import privatemoviecollection.BE.PrivateMovieCollection;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class alertWindowController implements Initializable {
private ObservableList<String> badMovies
            = FXCollections.observableArrayList();
    @FXML
    private Button saveGenre;
    @FXML
    private Button removeMovie;
    @FXML
    private ListView<String> genreListView;
private Model model;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void saveBtn(ActionEvent event) {
    }

    @FXML
    private void removeMovie(ActionEvent event) {
    }
      void setModel(Model model) {
        this.model=model;
    }

    void setBadMovies(ObservableList<PrivateMovieCollection> badMovie) {
        for (PrivateMovieCollection privateMovieCollection : badMovie) {
            badMovies.add(privateMovieCollection.getTitle());
        }
    genreListView.getItems().setAll(badMovies);
    }
    
}
