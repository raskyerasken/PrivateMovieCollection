/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.BE;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.beans.property.IntegerProperty;

/**
 *
 * @author mr.Andersen
 */
public class CategoryID 
{   
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty category=  new SimpleStringProperty();
    

    public String getCategory()
    {
        return category.get();
    }
    
    public void setCategory(String category)
    {
        this.category.set(category);
    }
    
    public StringProperty categoryProperty()
    {
        return category;
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
    
}
