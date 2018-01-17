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
    PrivateMovieCollection PMC = new PrivateMovieCollection();
    
    //Gets the movies from PrivateMovieCollection class
    List<PrivateMovieCollection> getAllMovies() throws SQLException
    {
        genreToMovies();
        return movieList;
    }
    
    
    void genreToMovies() throws SQLException
    { 
        movieList.clear();
        for (PrivateMovieCollection privateMovieCollection : bllManager.getAllMovies()) 
        {
            String genrer="";
            for (CatMovieBE genre  : bllManager.getMoviesGenre(privateMovieCollection.getTitle())) 
            {
                genrer=genrer+genre.getCategoryName()+",";
            }
            privateMovieCollection.setCategoryName(genrer);
            movieList.add(privateMovieCollection);
        }
    }
    
   void add (PrivateMovieCollection movie) throws SQLException
    {   
        bllManager.add(movie);
        genreToMovies();
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

    List<CategoryID> allGenre() 
    {
        genreList.clear();
        genreList.addAll(bllManager.allGenre());
        return genreList;
    }

    public List<PrivateMovieCollection> getAllMoviesList(String movie) throws SQLException
    {
        movieList.setAll(bllManager.getAllMoviesList(movie));
        return movieList;
    }

    void removeGenre(String selectedItem) throws SQLException 
    {
        bllManager.removeGenre(selectedItem);
    }

    void addMovieGenre(CatMovieBE categoryToMovie)
    {
        bllManager.add(categoryToMovie);
        movieCategory.add(categoryToMovie);
    }

     ObservableList<PrivateMovieCollection> getAllMoviesByGenre(String selectedGenre) throws SQLException 
     {
        movieList.clear();
        for (CatMovieBE catMovieBE : bllManager.getAllMovieByGenre(selectedGenre)) 
        {
            PMC=bllManager.getMovie(catMovieBE.getMovieName());
            PMC.setCategoryName(selectedGenre);
            movieList.add(PMC);
        } 
        return movieList; 
    }
     
    void removeMovieRate(PrivateMovieCollection movie) throws SQLException 
    {
        bllManager.removeRate(movie);
        movieList.remove(movie);
    }
}
