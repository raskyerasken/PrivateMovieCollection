/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void ratingEdit(ActionEvent event) {
    }

    @FXML
    private void SaveRating(ActionEvent event) {
    }

    @FXML
    private void cancelButton(ActionEvent event) {
    }

    void setModel(Model model)
    {
        this.model = model;
    }
    
}
