/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kasper
 */
public class CatMovieBE {

    private final StringProperty movieName = new SimpleStringProperty();
  private final StringProperty categoryName = new SimpleStringProperty();
  
    public String getMovieName() {
        return movieName.get();
    }

    public void setMovieName(String value) {
        movieName.set(value);
    }

    public StringProperty movieNameProperty() {
        return movieName;
    }

    @Override
    public String toString() {
        return "CatMovieBE{" + "movieName=" + movieName + ", categoryName=" + categoryName + '}';
    }

   

    public String getCategoryName() {
        return categoryName.get();
    }

    public void setCategoryName(String value) {
        categoryName.set(value);
    }

    public StringProperty categoryNameProperty() {
        return categoryName;
    }
    
    
  
}
