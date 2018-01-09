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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kasper
 */
public class PrivateMovieCollectionController implements Initializable {

    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField movieTitle;
    @FXML
    private RadioButton ratingSix;
    @FXML
    private RadioButton ratingOne;
    @FXML
    private RadioButton ratingTwo;
    @FXML
    private RadioButton ratingThree;
    @FXML
    private RadioButton ratingFour;
    @FXML
    private RadioButton ratingFive;
    @FXML
    private RadioButton selectedDrama;
    @FXML
    private RadioButton selectedHorror;
    @FXML
    private RadioButton selectedAction;
    @FXML
    private RadioButton selectedCrime;
    @FXML
    private RadioButton selectedComedy;
    @FXML
    private RadioButton selectedWar;
    @FXML
    private RadioButton selectedThriller;
    @FXML
    private RadioButton selectedNoir;
    @FXML
    private RadioButton selectedAnimation;
    @FXML
    private RadioButton selectedWestern;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
