/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import privatemoviecollection.BE.CategoryID;
import privatemoviecollection.BE.PrivateMovieCollection;
import privatemoviecollection.BLL.BLLManager;

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
    private RadioButton selectedDrama;
    private RadioButton selectedHorror;
    private RadioButton selectedAction;
    private RadioButton selectedCrime;
    private RadioButton selectedComedy;
    private RadioButton selectedWar;
    private RadioButton selectedThriller;
    private RadioButton selectedNoir;
    private RadioButton selectedAnimation;
    private RadioButton selectedWestern;
    
    private CategoryID genreMovie = new CategoryID();
   String URLAdressSong;
    @FXML
    private ToggleGroup movieRating;
    private ToggleGroup movieGenre;
   
    @FXML
    private Button selectMovie;
     PrivateMovieCollection movie=new PrivateMovieCollection();
     BLLManager BLL= new BLLManager();
    @FXML
    private ComboBox<?> selectGenre1;
    @FXML
    private ComboBox<?> selectGenre2;
    @FXML
    private ComboBox<?> selectGenre3;
    private Model model;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveBtn(ActionEvent event) throws SQLException 
    {
       
       
     
        
        if (URLAdressSong == null || movieTitle==null )
        {
            
        }
        else
        { 
            saveRating();
            
            movie.setFilelink(URLAdressSong);
            movie.setTitle(movieTitle.getText());
            model.add(movie);
            Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
            
        
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
        Stage stage = (Stage) selectMovie.getScene().getWindow();
        stage.toFront();
    }

    
    
  
    private void saveRating()
    {
        String title = movieTitle.getText();
        Toggle rating = movieRating.getSelectedToggle();
    

        if (rating.equals(ratingOne))
                {
                    movie.setRating(1);  
                }
          if (rating.equals(ratingTwo))
                {
                    movie.setRating(2);  
                }
         if (rating.equals(ratingThree))
                {
                    movie.setRating(3);
                }
        if (rating.equals(ratingFour))
                {
                    movie.setRating(4);
                }
        if (rating.equals(ratingFive))
                {
                    movie.setRating(5);
                }
        if (rating.equals(ratingSix))
                {
                    movie.setRating(6);
                }
        
//        Toggle genre = movieGenre.getSelectedToggle();
//        if (genre.equals(selectedDrama))
//        {
//            genreMovie.setCategory("Drama");
//        }
//        
//        if (genre.equals(selectedHorror))
//        {
//            genreMovie.setCategory("Horror");
//        }
//        
//        if (genre.equals(selectedAction))
//        {
//            genreMovie.setCategory("Action");
//        }
//        if (genre.equals(selectedCrime))
//        {
//            genreMovie.setCategory("Crime");
//        }
//        if (genre.equals(selectedComedy))
//        {
//            genreMovie.setCategory("Comedy");
//        }
//        if (genre.equals(selectedWar))
//        {
//            genreMovie.setCategory("War");
//        }
//        if (genre.equals(selectedThriller))
//        {
//            genreMovie.setCategory("Thriller");
//        }
//        if (genre.equals(selectedNoir))
//        {
//            genreMovie.setCategory("Noir");
//        }
//        if (genre.equals(selectedAnimation))
//        {
//            genreMovie.setCategory("Animation");
//        }
//        if (genre.equals(selectedWestern))
//        {
//            genreMovie.setCategory("Western");
//        }
        System.out.println(genreMovie.getCategory());
     
    }

    void setModel(Model model) {
    this.model=model;
    }
    
   
    
}
