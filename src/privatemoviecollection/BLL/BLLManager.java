/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.BLL;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        pmcdal.removeConneting(selectPrivateMovieCollection);
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
    
    public List<PrivateMovieCollection> getAllMoviesList(String movie) throws SQLException
    {
        return pmcdal.getAllMoviesList(movie);
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
        pmcdal.removeGenreConnection(genre);
    }

    public ObservableList<CategoryID> allGenre() 
    {
     return pmcdal.allGenre();
    }
    
    private void getDate()
    {
        java.util.Date d = new java.util.Date();
    SimpleDateFormat sdf = new SimpleDateFormat("DD:MM:YYYY");
        System.out.println("The current time is "+sdf.format(d));
    }
    
    public void dayCount() throws ParseException
    {
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        
        Date date = sdf.parse("First date");
        cal1.setTime(date);
        date = sdf.parse("second date");
        cal2.setTime(date);
        
        System.out.println("Days = " + daysBetween(cal1.getTime(), cal2.getTime()));
       
                
                
    }
    
    public int daysBetween(Date d1, Date d2)
    {
        return (int)((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)); 
    }
    

    public void add(CatMovieBE categoryToMovie) {
   pmcdal.addGenreToMovies(categoryToMovie);
    }

    public  List<CatMovieBE> getAllMovieByGenre(String selectedGenre) throws SQLException {
    return pmcdal.getAllMoviesByGenre(selectedGenre);
    }

    public PrivateMovieCollection  getMovie(String movieName) throws SQLException {
    return pmcdal.getMovie(movieName);
    }

    public List<CatMovieBE> getMoviesGenre(String title) throws SQLException {
    return pmcdal.getGenreByMovue(title);
    }


    
}
