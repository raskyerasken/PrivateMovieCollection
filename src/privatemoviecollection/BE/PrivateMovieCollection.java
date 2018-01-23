/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.BE;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mr.Andersen
 */
public class PrivateMovieCollection 
{
    private final IntegerProperty id;
    private final StringProperty title =  new SimpleStringProperty();
    private final StringProperty filelink =  new SimpleStringProperty();
    private final IntegerProperty rating= new SimpleIntegerProperty();
    Date lastview;
    
    private final StringProperty categoryName = new SimpleStringProperty();

    public PrivateMovieCollection() 
    {
        this.id = new SimpleIntegerProperty();
    }

    public String getCategoryName() 
    {
        return categoryName.get();
    }

    public void setCategoryName(String value) 
    {
        categoryName.set(value);
    }

    public StringProperty categoryNameProperty() 
    {
        return categoryName;
    }

    
    public int getId()
    {
        return id.get();
    }
    
    public void setId(int id)
    {
        this.id.set(id);
    }
    
    public IntegerProperty idProperty()
    {
        return id;
    }
     
    public String getTitle() 
    {
        return title.get();
    }

    public void setTitle(String title) 
    {
        this.title.set(title);
    }
    
    public StringProperty titleProperty()
    {
        return title;
    }
    
     public int getRating()
    {
        return rating.get();
    }
    
    public void setRating(int rating)
    {
        this.rating.set(rating);
    }
    
    public IntegerProperty ratingProperty()
    {
        return rating;
    }

    public Date getLastview() 
    {
        return lastview;
    }

    public void setLastview(Date lastview) 
    {
        this.lastview = lastview;
    }

    public String getFilelink() 
    {
        return filelink.get();
    }

    public void setFilelink(String filelink) 
    {
        this.filelink.set(filelink);
    }

    @Override
    public String toString() 
    {
        return "PrivateMovieCollection{" + "id=" + id + ", title=" + title + ", filelink=" + filelink + ", rating=" + rating + ", lastview=" + lastview + ", allMovies=" +  '}';
    }
    
    public StringProperty filelinkProperty()
    {
        return filelink;
    }

}