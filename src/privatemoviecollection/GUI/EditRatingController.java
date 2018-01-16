/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class EditRatingController implements Initializable {

    @FXML
    private Button saveRating;
    @FXML
    private Button cancelButton;
    Model model;
    @FXML
    private TextField ratingEdit;
    private PrivateMovieCollection movie;
    int maxRating=10;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    


    @FXML
    private void SaveRating(ActionEvent event){
        try{
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
     private void showErrorDialog(String title, String header, String message)
    {
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void cancelButton(ActionEvent event) {
           Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    void setModel(Model model)
    {
        this.model = model;
    }
    void getMovie(PrivateMovieCollection movie)
    {
    this.movie= movie;
    ratingEdit.setText(""+movie.getRating());
   }
    
}
