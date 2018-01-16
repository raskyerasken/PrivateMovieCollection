/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import privatemoviecollection.BE.PrivateMovieCollection;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class alertWindowController implements Initializable {
private ObservableList<PrivateMovieCollection> badMovies
            = FXCollections.observableArrayList();
    @FXML
    private Button saveGenre;
    @FXML
    private Button removeMovie;
    @FXML
    private ListView<String> genreListView;
    private Model model;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //Raske was here
    }    

    private void showErrorDialog(String title, String header, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
<<<<<<< HEAD
    private void saveBtn(ActionEvent event) 
    {
        if (!genreListView.getSelectionModel().isEmpty())  
=======
    private void saveBtn(ActionEvent event) {
       badMovies.remove(genreListView.getSelectionModel().getSelectedIndex());
        genreListView.getItems().remove(genreListView.getSelectionModel().getSelectedIndex());
        if(genreListView.getItems().isEmpty())
>>>>>>> 0490edc0ab028f119bc39eec91184ff89dcf4ec6
        {
            badMovies.remove(genreListView.getSelectionModel().getSelectedIndex());
            genreListView.getItems().remove(genreListView.getSelectionModel().getSelectedIndex());
            if(genreListView.getItems().isEmpty())
            {
                Stage stage =(Stage) removeMovie.getScene().getWindow();
                stage.close();
            }
        }
        else
            showErrorDialog("Selection Error", null, "You need to select a movie in order to save it."); 
    }

    @FXML
    private void removeMovie(ActionEvent event) throws SQLException 
    {
        if (!genreListView.getSelectionModel().isEmpty()) 
        {
            model.removeMovie(badMovies.get(genreListView.getSelectionModel().getSelectedIndex()));
            badMovies.remove(genreListView.getSelectionModel().getSelectedIndex());
            genreListView.getItems().remove(genreListView.getSelectionModel().getSelectedIndex());
        }
        else
            showErrorDialog("Selection Error", null, "You need to select a movie in order to remove it.");
    }
    
    void setModel(Model model)
    {
        this.model=model;
    }

    void setBadMovies(ObservableList<PrivateMovieCollection> badMovie) {
        badMovies.setAll(badMovie);
        for (PrivateMovieCollection privateMovieCollection : badMovie) {
            
            genreListView.getItems().add(privateMovieCollection.getTitle());
        }
  
    }
    
}
