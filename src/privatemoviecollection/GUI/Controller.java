/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import privatemoviecollection.BE.CategoryID;
import privatemoviecollection.BE.PrivateMovieCollection;
import privatemoviecollection.BLL.BLLManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mr.Andersen
 */
public class Controller implements Initializable 
{    
    private final ObservableList<PrivateMovieCollection> badMovie
            = FXCollections.observableArrayList();
    boolean badMovies=false;
    @FXML
    private TableColumn<PrivateMovieCollection, String> genre;
    @FXML
    private TableView<PrivateMovieCollection> movieListView;
    @FXML
    private TableColumn<PrivateMovieCollection, String> Title;
    @FXML
    private TableColumn<PrivateMovieCollection, Integer> rating;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button searchBtn;
    Model model= new Model();
    int dayCount = 0;
    boolean search = false;
    @FXML
    private ComboBox<String> selectGenre;
    private Stage primaryStage;
    private Button addMovieBtn;
    BLLManager BLL = new BLLManager();
    int daysForBadMovie= 730;
    int badMovieRating=3;
    /*Makes the combobox with gerne, and tablecolums with privateMovieCollections, 
    and run badmovies if their is a bad movie*/
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        selectGenre.getItems().clear();
        selectGenre.getItems().add("All movies");
        model.allGenre().forEach((id) -> {
            selectGenre.getItems().add(id.getCategory());
        });
      
        Title.setCellValueFactory(
        new PropertyValueFactory("title"));
        
        rating.setCellValueFactory(
        new PropertyValueFactory("rating"));
        
        genre.setCellValueFactory(
        new PropertyValueFactory("categoryName"));
        try 
        {
            movieListView.setItems((ObservableList<PrivateMovieCollection>)model.getAllMovies() );
        }
        
        catch (SQLException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try 
        {
            badMovieAlert();
        } 
        
        catch (ParseException | IOException | SQLException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * This Opens up a file in your computer and runs it, so if you
     *      select a movie then it will open it, if nothing is selected you get an error
     */
    @FXML
    private void playMovie(ActionEvent event) throws IOException 
    {
        try
        {
            if (movieListView.getSelectionModel().getSelectedItem() != null ) 
            {
                File file = new File("");
                Desktop desktop = Desktop.getDesktop();
                file = new File(movieListView.getSelectionModel().getSelectedItem().getFilelink());
                desktop.open(file);
            }
            else
                showErrorDialog("Selection Error", null, 
                        "Check if you selected a movie from the list\n "
                                + "If the list is empty then please add a movie");
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //addmovie window show
    void newAddMovieView() throws IOException
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
        Parent root = fxLoader.load();
        AddMovieController controller= fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
    //EditRating window show
    void newEditRatingView() throws IOException
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("EditRating.fxml"));
        Parent root = fxLoader.load();
        EditRatingController controller= fxLoader.getController();
        controller.setModel(model);
        controller.getMovie(movieListView.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
    //addgenre window show
    void newAddGenreView() throws IOException
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("addGenre.fxml"));
        Parent root = fxLoader.load();
        AddGenreController controller= fxLoader.getController();
        controller.setModel(model);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
    
    //remove a movie if anyone is selectet, go down to the database.
    @FXML
    private void removeMovie(ActionEvent event) throws SQLException
    {
        try{
        PrivateMovieCollection selectedMovie = movieListView.getSelectionModel()
                .getSelectedItem();
        if (selectedMovie != null)
        {
            model.removeMovie(selectedMovie);
        }
        else
        showErrorDialog("Nothing Selected", null, "Cannot delete nothing");  
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
     
     
    
    //allows the user to close the program, and does a pop-up making sure the user actually wants to
    @FXML
    private void closeProgram(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit this Program?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK)
        {
            Platform.exit();
        } 
    }
    
    private void badMovieAlert() throws ParseException, IOException, SQLException
    {
       for (PrivateMovieCollection allMovy : model.getAllMovies()) 
       {
            if (BLL.daysBetween(allMovy.getLastview(), newTime())>daysForBadMovie || allMovy.getRating()<badMovieRating)
            {
                badMovies=true;
                badMovie.add(allMovy);
            }  
        }
       
        if(badMovies)
        {
            Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AlertWindows.fxml"));
            Parent root = fxLoader.load();
            alertWindowController controller= fxLoader.getController();
            controller.setModel(model);
            controller.setBadMovies(badMovie);
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.showAndWait();
            badMovies=false;
        }
    }
    
    // today day
    private Date newTime() throws ParseException
    {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
  
    
    
    //sets the "About Us"
    @FXML
    private void handleAbout(ActionEvent event) 
    {  
             String contentText = "\t Hello, and welcome to our PrivateMovieCollection."
                +"\n\t In the file menu you can find:\n"
                +"\t * How to add a new movie\n"
                +"\t * How to create a new movie list\n"
                +"\t * How to close the program \n"
                +"\n\t In the edit menu you can find:\n"
                +"\t * How to edit a movie\n"
                +"\t * How to delete a movie\n"
                +"\n\t We're 3 students who created this program.\n"
                +"\t If you have any problems at all,\n"
                +"\t you are very welcome to run head first into a wall \n"
                +"\t since there will be roughly zero support from us \n"
                +"\t unless you throw money at the screen. \n"
                +"\t Proudly presented by De Raske: \n"
                +"\t Jacob, Kristófer  & Kasper Raskafar\n";
                
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("About us and PrivateMovieCollection");
        about.setHeaderText(null);
        about.setContentText(contentText);
        about.getDialogPane().setPrefWidth(480);
        about.resizableProperty().set(true);
        about.showAndWait();
    }

    @FXML
    private void addGenre(ActionEvent event) 
    {
         try
         {
            newAddGenreView();
         }
         catch (IOException ex) 
         {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
         } 
    }

    @FXML
    private void addMovie(ActionEvent event)  
    {
        try
        {
            newAddMovieView();
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    
    
    @FXML
    private void searchMovie(ActionEvent event) 
    {
        try
        {
            if(!txtSearch.getText().isEmpty())
            {
                if (search) 
                {
                    search = false;
                    searchBtn.setText("Search");
                    movieListView.setItems
                        ((ObservableList<PrivateMovieCollection>) 
                            model.getAllMovies());
                }
                else
                {
                    String a = txtSearch.getText();
                    movieListView.setItems((ObservableList<PrivateMovieCollection>)
                            model.getAllMoviesList(a));
                    search = true;
                    searchBtn.setText("All Movies");
                }
            }
            else
            {
                showErrorDialog("Input error", null, "Please input search term");
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML
    private void genreFilter(ActionEvent event) 
    {
        try
        {
            if(selectGenre.getSelectionModel().getSelectedItem()=="All movies")
            {
                movieListView.setItems
                    ((ObservableList<PrivateMovieCollection>) 
                        model.getAllMovies());
            }
            else
            {
                movieListView.setItems(model.getAllMoviesByGenre(selectGenre.getSelectionModel().getSelectedItem()));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void editRating(ActionEvent event) 
    {  
        try
        {
            if(!movieListView.getSelectionModel().isEmpty())
            {
                newEditRatingView();
            }
            else
            {
                showErrorDialog("Selecet a movie", null,"Select a movie");
            }
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}

    



