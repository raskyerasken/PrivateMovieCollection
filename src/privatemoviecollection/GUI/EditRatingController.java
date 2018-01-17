/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import privatemoviecollection.BE.PrivateMovieCollection;

/**
 * FXML Controller class
 *
 * @author mr.Andersen
 */
<<<<<<< HEAD
public class EditRatingController {

=======
public class EditRatingController 
{
>>>>>>> 219c7c6c568a16deb1fba7535872cf6bee8703a2
    @FXML
    private Button saveRating;
    @FXML
    private Button cancelButton;
    Model model;
    @FXML
    private TextField ratingEdit;
    private PrivateMovieCollection movie;
<<<<<<< HEAD
    int maxRating=10;
    
=======
    int maxRating=10; 

    //Saving the rating and user cannot add higher rating then 10
>>>>>>> 219c7c6c568a16deb1fba7535872cf6bee8703a2
    @FXML
    private void SaveRating(ActionEvent event)
    {
        try
        {
            int rate = Integer.parseInt(ratingEdit.getText());
            if (rate > maxRating)
            {
                showErrorDialog("Please input rating from 1 - 10 only",
                        null, " You can only input rating from 1 to 10");
            }
            else
            {
                movie.setRating(rate);
                model.removeMovieRate(movie);
                model.add(movie);
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Allows  us to create allert windows 
    private void showErrorDialog(String title, String header, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    //closes the window user is currently using
    @FXML
    private void cancelButton(ActionEvent event) 
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    //Gets the model from our controller so we use the same model
    void setModel(Model model)
    {
        this.model = model;
    }
    
    //Getting the movie for the controller 
    void getMovie(PrivateMovieCollection movie)
    {
        this.movie = movie;
        ratingEdit.setText(""+movie.getRating());
    }
    
}
