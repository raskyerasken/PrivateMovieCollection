/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.GUI;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    List<PrivateMovieCollection> getAllMovies()
    {
        movieList.clear();
//        movieList.addAll(bllManager.getAllMovies());
        
        return movieList;
    }
}
