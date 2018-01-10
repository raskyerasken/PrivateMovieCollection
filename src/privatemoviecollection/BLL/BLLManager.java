/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.objects.NativeString.search;
import privatemoviecollection.BE.CatMovieBE;
import privatemoviecollection.BE.CategoryID;
import privatemoviecollection.BE.PrivateMovieCollection;
import privatemoviecollection.DAL.PrivateMovieCollectionDAL;

/**
 *
 * @author mr.Andersen
 */
public class BLLManager 
{
    PrivateMovieCollectionDAL pmcdal = new PrivateMovieCollectionDAL();
    List<PrivateMovieCollection> allGenres = new ArrayList();

    
    public void add(PrivateMovieCollection allMovies) throws SQLException
    {
        pmcdal.add(allMovies);
    }
    
//    public List<PrivateMovieCollection> getAllMovies()
//    {
//        return pmcdal.getAllMovies();
//    }
    
    public void remove(PrivateMovieCollection selectPrivateMovieCollection) throws SQLException
    {
        pmcdal.remove(selectPrivateMovieCollection);
    }
    
//    public void add(CategoryID allMovies) 
//    {
//        pmcdal.addCategoryID(allMovies);
//    }
//    
//    public List<CategoryID> getAllByGenre()
//    {
//        return pmcdal.getAllByGenre();
//    }
//    
//    public void remove(CategoryID categoryidMovies)
//    {
//        pmcdal.removeCategoryID(categoryMovies);
//    }
//    
//    public void addMoviesToPlayer(CatMovieBE ID)
//    {
//        pmcdal.addMoviesToPlayer(ID);
//    }
//    
//    public void removeMoviesPlaylist(CatMovieBE movieList)
//    {
//        pmcdal.removeMoviesPlaylist(movieList);
//    }
//    
//    public List<CatMovieBE> getSelectedMovieList(int movieListID)
//    {
//        return pmcdal.getSelectedMovieList(movieListID);
//    }
    
    public void update(PrivateMovieCollection privateMovieCollection) throws SQLException
    {
        pmcdal.update(privateMovieCollection);
    }

    public List getAllMovie() 
    {
        return pmcdal.getAllMovies();
    }

    public List<PrivateMovieCollection> getAllMovies() 
    {
        return pmcdal.getAllMovies();
    }
    
//    public List<PrivateMovieCollection> getAllByGenre()
//    {
//        return pmcdal.getAllByGenre();
//        
//    }

    public void addGenre(CategoryID genre) throws SQLException
    {
       pmcdal.addGenre(genre);
        
    }
    
    public void removeGenre(String genre) throws SQLException
    {
        pmcdal.removeGenre(genre);
        
        
    }

    public ObservableList<CategoryID> allGenre() {
     return pmcdal.allGenre();
             }

    
}
