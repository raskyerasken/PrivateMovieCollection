/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import privatemoviecollection.BE.CategoryID;
import privatemoviecollection.BE.PrivateMovieCollection;
import privatemoviecollection.BLL.BLLManager;

/**
 *
 * @author mr.Andersen
 */
public class Controller implements Initializable 
{    
    @FXML
    private ComboBox<String> selectGenre;
    private Stage primaryStage;
    private Button addMovieBtn;
    BLLManager BLL = new BLLManager();
    @FXML
    private TableView<PrivateMovieCollection> movieListView;
    @FXML
    private TableColumn<PrivateMovieCollection, String> Title;
    @FXML
    private TableColumn<PrivateMovieCollection, Integer> rating;
  Model model= new Model();
    @FXML
    private TextField txtSearch;
    @FXML
    private Button searchBtn;
    int dayCount = 0;
    boolean search = false;
   
    @FXML
    private TableColumn<PrivateMovieCollection, String> genre;

    private ListView<?> genreListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        selectGenre.getItems().clear();
        selectGenre.getItems().add("All movies");
        for (CategoryID  id : model.allGenre()) 
        {
        selectGenre.getItems().add(id.getCategory());
        }
      
        Title.setCellValueFactory(
        new PropertyValueFactory("title"));
        
        rating.setCellValueFactory(
        new PropertyValueFactory("rating"));
        
        genre.setCellValueFactory(
        new PropertyValueFactory("lastview"));

        
        movieListView.setItems((ObservableList<PrivateMovieCollection>)model.getAllMovies() );
    }
    
    
    @FXML
    private void playMovie(ActionEvent event) throws IOException 
    {
        File file = new File("");
        Desktop desktop = Desktop.getDesktop();
        file = new File(movieListView.getSelectionModel().getSelectedItem().getFilelink());
        desktop.open(file);
    }
    
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
    
    @FXML
    private void removeMovie(ActionEvent event) throws SQLException
    {
        PrivateMovieCollection selectedMovie = movieListView.getSelectionModel()
                .getSelectedItem();
        if (selectedMovie == null)
        {
            showErrorDialog("Nothing Selected", null, "Cannot delete nothing");
        }
        else
            
            model.removeMovie(selectedMovie);
    }
    
    
    private void loadStage(String viewName) throws IOException
    {
        primaryStage = (Stage) addMovieBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/gui/view/" + viewName));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(primaryStage);
        newStage.show();
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
    

    private void badMovieAlert() throws ParseException, IOException
    {

//       if (BLL.daysBetween(lastViewDate(), newTime()).getDays() > 700 && )
//               { 
//                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                   alert.setTitle("You should delete these movies:" + );
//                   
//               }  

//    }

//       if (BLL.daysBetween(lastViewDate(), newTime()).getDays() > 700)
//               { 
//                   Stage newStage = new Stage();
//        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("alertWindow.fxml"));
//        Parent root = fxLoader.load();
//        alertWindowController controller= fxLoader.getController();
//        controller.setModel(model);
//        Scene scene = new Scene(root);
//        newStage.setScene(scene);
//        newStage.show();
//               }  

    }

  
    private Date newTime() throws ParseException
    {
        Calendar cal1 = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        
        Date date = sdf.parse("This date");
        cal1.setTime(date);
        

        return date;
        
    }
    
//    private Date lastViewDate()
//    {
//        
//    }
    
    @FXML
    private void handleAbout(ActionEvent event) {  //sets the "About Us"
             String contentText = "\t Hello, and welcome to our PrivateMovieCollection."
                +"\n\t In the file menu you can find:\n"
                +"\t * How to add a new movie\n"
                +"\t * How to create a new playlist\n"
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
    private void addGenre(ActionEvent event) throws IOException {
        newAddGenreView();
    }

    @FXML
    private void addMovie(ActionEvent event) throws IOException 
    {
        newAddMovieView();
    }    

    @FXML
    private void searchMovie(ActionEvent event) throws SQLException 
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

    
    @FXML
private void genreFilter(ActionEvent event) throws SQLException {
        if(selectGenre.getSelectionModel().getSelectedItem()=="All movies")
        {
            movieListView.setItems
                ((ObservableList<PrivateMovieCollection>) 
                    model.getAllMovies());
        }
        else
        {
          model.getAllMoviesByGenre(selectGenre.getSelectionModel().getSelectedItem());
        }
}

    @FXML
    private void getSelectedPlaylist(MouseEvent event) {
    }
}



