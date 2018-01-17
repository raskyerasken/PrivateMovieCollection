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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import privatemoviecollection.BE.CatMovieBE;
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
    CatMovieBE catMoviebe= new CatMovieBE();
    private CategoryID genreMovie = new CategoryID();
    String URLAdressSong;
    @FXML
    private TextField movieRating;
    private ToggleGroup movieGenre;
   
    @FXML
    private Button selectMovie;
    PrivateMovieCollection movie=new PrivateMovieCollection();
    BLLManager BLL= new BLLManager();
    @FXML
    private ComboBox<String> selectGenre1;
    @FXML
    private ComboBox<String> selectGenre2;
    @FXML
    private ComboBox<String> selectGenre3;
    private Model model;
    boolean titleError = false;
 int maxRating= 10; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
    }   
    private void setComboBox()
    {  
        selectGenre1.getItems().clear();
        for (CategoryID  id : model.allGenre()) 
        {
            selectGenre1.getItems().add(id.getCategory());
        }
        
        selectGenre2.getItems().removeAll(selectGenre2.getItems());
        for (CategoryID  id : model.allGenre()) 
        {
            selectGenre2.getItems().add(id.getCategory());
        }
        selectGenre3.getItems().removeAll(selectGenre3.getItems());
        for (CategoryID  id : model.allGenre()) 
        {
            selectGenre3.getItems().add(id.getCategory());
        }
        
    
    }
    
    //allows us to create error messages
    private void showErrorDialog(String title, String header, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    //Saves our shit to another shit
    @FXML
    private void saveBtn(ActionEvent event) 
    {
        try
        {
            if (URLAdressSong == null || movieTitle==null )
            {
                showErrorDialog("Please Select Movie and give it a title",
                        null, " Please Select Movie and give it a title");
            }
            else
            { 
                int rate = Integer.parseInt(movieRating.getText());
                if (rate > maxRating)
                {
                    showErrorDialog("Please input rating from 1 - 10 only",
                            null, " You can only input rating from 1 to 10");
                }
                else
                {
                    titleError = true;
                    for (PrivateMovieCollection pmc : model.getAllMovies()) 
                    {   
                        if (pmc.getTitle().trim().equals(movieTitle.getText())) 
                        {
                            showErrorDialog("Naming error", null, "That name already exists");
                            titleError = false;
                        }
                    }
                    
                    if(titleError)
                    {
                        movie.setRating(rate);
                        saveRating();
                        java.util.Date utilDate = new java.util.Date();
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        movie.setFilelink(URLAdressSong);
                        movie.setTitle(movieTitle.getText());
                        movie.setLastview(sqlDate);
                        setGenre();
                        model.add(movie);
                        Stage stage = (Stage) cancel.getScene().getWindow();
                        stage.close();
                    }
                }
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setGenre()
    {
        catMoviebe.setMovieName(movie.getTitle());
        if(selectGenre1.getSelectionModel().getSelectedItem()!=null)
        {
            catMoviebe.setCategoryName(selectGenre1.getSelectionModel().getSelectedItem());
            model.addMovieGenre(catMoviebe);
        }

        if(selectGenre2.getSelectionModel().getSelectedItem()!=null)
        {
             catMoviebe.setCategoryName(selectGenre2.getSelectionModel().getSelectedItem());
             model.addMovieGenre(catMoviebe);
        }

        if(selectGenre3.getSelectionModel().getSelectedItem()!=null)
        {
             catMoviebe.setCategoryName(selectGenre3.getSelectionModel().getSelectedItem());
             model.addMovieGenre(catMoviebe);
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("mp4", "mp4");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("mpe4","mpeg4");
        chooser.setFileFilter(filter);
        chooser.setFileFilter(filter2);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogTitle("choosertitle");
        
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
        { 
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            stageToFront();
        } 
        URLAdressSong=""+chooser.getSelectedFile();
        stageToFront();
     }
    
    void stageToFront()
    {
     Stage stage = (Stage) selectMovie.getScene().getWindow();
     stage.toFront();
    }

    private void saveRating()
    {
        String title = movieTitle.getText();
        System.out.println(genreMovie.getCategory());
    }

    void setModel(Model model) 
    {
        this.model=model;
        setComboBox();
    }
    
}
