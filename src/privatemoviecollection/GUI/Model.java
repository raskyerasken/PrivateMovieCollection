/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.BE.CatMovieBE;
import privatemoviecollection.BE.CategoryID;
import privatemoviecollection.BE.PrivateMovieCollection;
import privatemoviecollection.BLL.BLLManager;

/**
 *
 * @author mr.Andersen
 */
public class Model 
{

    
    private BLLManager bllManager = new BLLManager();
    
    private ObservableList<PrivateMovieCollection> movieList
            = FXCollections.observableArrayList();
    
    private ObservableList<CategoryID> genreList
            = FXCollections.observableArrayList();
    
    private ObservableList<CatMovieBE> movieCategory
            = FXCollections.observableArrayList();
    
    List<PrivateMovieCollection> getAllMovies()
    {
        movieList.clear();
        movieList.addAll(bllManager.getAllMovies());
        
        return movieList;
    }
    
   void add (PrivateMovieCollection movie) throws SQLException
    {   
        bllManager.add(movie);
        movieList.add(movie);
    }

    void addGenre(CategoryID category) throws SQLException 
    { 
        genreList.add(category);
        bllManager.addGenre(category);
    }
    
    public void removeMovie(PrivateMovieCollection movie) throws SQLException
    {
        bllManager.remove(movie);
        movieList.remove(movie);
    }

    List<CategoryID> allGenre() {
        genreList.clear();
        genreList.addAll(bllManager.allGenre());
        return genreList;
        
    }

    public List<PrivateMovieCollection> getAllMoviesList(String movie) throws SQLException
    {
        movieList.setAll(bllManager.getAllMoviesList(movie));
        return movieList;
    }

    void removeGenre(String selectedItem) throws SQLException {
    bllManager.removeGenre(selectedItem);
    }

    void addMovieGenre(CatMovieBE categoryToMovie) {
     bllManager.add(categoryToMovie);
        movieCategory.add(categoryToMovie);
    }

    void getAllMoviesByGenre(String selectedGenre) throws SQLException {
     bllManager.getAllMovieByGenre(selectedGenre);
        System.out.println(bllManager.getAllMovieByGenre(selectedGenre));
    }
}
