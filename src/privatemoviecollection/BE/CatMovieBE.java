/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author kasper
 */
public class CatMovieBE {
    
    
  private final IntegerProperty IDCategory= new SimpleIntegerProperty();
    private final IntegerProperty IDMovie= new SimpleIntegerProperty();
    
    public int getIDCategory()
    {
        return IDCategory.get();
    }
    
    public void setIDPlaylist(int IDCategory)
    {
        this.IDCategory.set(IDCategory);
    }
    
    public IntegerProperty IDCategoryProperty()
    {
        return IDCategory;
    }
        public int getIDMovie()
    {
        return IDMovie.get();
    }
    
    public void setIDSong(int IDMovie)
    {
        this.IDMovie.set(IDMovie);
    }
    
    public IntegerProperty IDMovieProperty()
    {
        return IDMovie;
    }

}
