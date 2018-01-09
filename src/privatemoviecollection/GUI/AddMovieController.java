/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import privatemoviecollection.BE.PrivateMovieCollection;

/**
 * FXML Controller class
 *
 * @author kasper
 */
public class AddMovieController implements Initializable {
    
    @FXML
    private Button cancel;
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
    @FXML
    private ToggleGroup rating;
    String URLAdressSong;
    PrivateMovieCollection pmc;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveBtn(ActionEvent event) 
    {
        pmc.setTitle(movieTitle.getText());
        pmc.setRating(Integer.parseInt(ratingOne.getText()));
        pmc.setRating(Integer.parseInt(ratingTwo.getText()));
        pmc.setRating(Integer.parseInt(ratingThree.getText()));
        pmc.setRating(Integer.parseInt(ratingFour.getText()));
        pmc.setRating(Integer.parseInt(ratingFive.getText()));
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        
        if (URLAdressSong == null || movieTitle==null  )
        {
            
        }
    }

    @FXML
    private void cancelBtn(ActionEvent event) 
    {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSelectMovie(ActionEvent event) 
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
        { 
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        } 
        else 
        {
            System.out.println("No Selection ");
        }
        
        URLAdressSong=""+chooser.getSelectedFile();
        System.out.println(URLAdressSong);
    }

    
}
