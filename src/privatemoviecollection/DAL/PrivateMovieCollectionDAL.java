/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.BE.CatMovieBE;
import privatemoviecollection.BE.CategoryID;
import privatemoviecollection.BE.PrivateMovieCollection;

/**
 *
 * @author mr.Andersen
 */
public class PrivateMovieCollectionDAL 
{
    private ConnectionManager cm = new ConnectionManager();
    
    public List<PrivateMovieCollection> getAllMovies()
    {
        List<PrivateMovieCollection> allMovies 
                = new ArrayList<>();
        
        try (Connection con = cm.getConnection())
        {
            
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM Movie");
            ResultSet rs = stmt.executeQuery();
                while (rs.next())
                {
                    PrivateMovieCollection s = new PrivateMovieCollection();
                    s.setTitle(rs.getString("Name"));
                    s.setId(rs.getInt("id"));
                    s.setRating(rs.getInt("Rating"));
                    s.setFilelink(rs.getString("Filelink"));
                    allMovies.add(s);
                }
        }
        
        catch (SQLException ex)
        {
            Logger.getLogger(PrivateMovieCollectionDAL.class.getName()).log(
            Level.SEVERE, null, ex);
        }
        return allMovies;
    }
    
    public List<PrivateMovieCollection> getAllMoviesList(String search) throws SQLServerException, SQLException
    {
        List<PrivateMovieCollection> allMovies = new ArrayList();
        
        try (Connection con = cm.getConnection())
        {
            String query
                    = "SELECT * FROM Movie WHERE name=?";
            
            PreparedStatement stmt
                    = con.prepareStatement(query);
            stmt.setString(1,"%" + search + "%");
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                PrivateMovieCollection s = new PrivateMovieCollection();
                s.setTitle(rs.getString("Name"));
                s.setId(rs.getInt("id"));
                s.setRating(rs.getInt("Rating"));
                s.setFilelink(rs.getString("Filelink"));
                allMovies.add(s);
            }
        } 
        return allMovies;
    }
    
    //hello
    public List<PrivateMovieCollection> getAllByGenre(String search) throws SQLException, SQLServerException
    {
        ArrayList<PrivateMovieCollection> allGenres = new ArrayList<>();
        
        try (Connection con = cm.getConnection())
        {
            String query 
                    = "SELECT * FROM category"
                    + "WHERE name LIKE ?";
            
            PreparedStatement pstmt
                    = con.prepareStatement(query);
            
            pstmt.setString(1, "%" + search + "%");
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                PrivateMovieCollection m = new PrivateMovieCollection();
                m.setId(rs.getInt("Id"));
                m.setTitle(rs.getString("Name"));
                allGenres.add(m);
            }
        }
        return allGenres;
    }

    public void add (PrivateMovieCollection allMovies) throws SQLServerException, SQLException 
    {  
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT INTO Movie"
                    + "(name, rating, filelink) "
                    + "VALUES(?,?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, allMovies.getTitle());
            pstmt.setInt(2, allMovies.getRating());
            pstmt.setString(3, allMovies.getFilelink());
           
            int affected = pstmt.executeUpdate();
            if (affected<1)
            {
                throw new SQLException("Movie could not be added");
            }
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) 
            {
                allMovies.setId(rs.getInt(1));
            }
            
        }
    
        catch (SQLException ex) 
        {
            Logger.getLogger(PrivateMovieCollectionDAL.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
      
    
    public void remove(PrivateMovieCollection selectedPrivateMovieCollection) throws SQLServerException, SQLException
    {
     
        try (Connection con = cm.getConnection()) 
        {
            String sql = "DELETE FROM Movie WHERE name=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, selectedPrivateMovieCollection.getTitle());
            pstmt.execute();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(PrivateMovieCollectionDAL.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
  

    public void update(PrivateMovieCollection PrivateMovieCollection) throws SQLServerException, SQLException {
        try (Connection con = cm.getConnection()) 
        {
            String sql 
                    = "UPDATE Movie SET "
                    + "id=?, name=?, Rating=?, filelink=?, lastview=?) "
                    + "WHERE id=?";
            
            
            PreparedStatement pstmt 
                    = con.prepareStatement(sql);
            pstmt.setString(1, PrivateMovieCollection.getTitle());
            pstmt.setInt(2, PrivateMovieCollection.getId());
            pstmt.setInt(3, PrivateMovieCollection.getRating());
            pstmt.setString(4, PrivateMovieCollection.getFilelink());
            pstmt.setDate(5, (Date) PrivateMovieCollection.getLastview());
            
            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Movie list could not be updated");
        }
        
        catch (SQLException ex) 
        {
            Logger.getLogger(PrivateMovieCollectionDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void  addGenre (CategoryID genre) throws SQLServerException, SQLException 
    { 
        try (Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT INTO Category"
                    + "(name) "
                    + "VALUES(?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, genre.getCategory());
            
           
            
            int affected = pstmt.executeUpdate();
            if (affected<1)
            {
                throw new SQLException("Genre could not be added");
            }
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) 
            {
                genre.setId(rs.getInt(1));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(PrivateMovieCollectionDAL.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }     
   
    public void removeGenre(String genre) throws SQLServerException, SQLException
    {
        System.out.println(genre);
        try (Connection con = cm.getConnection()) 
        {
            String sql = "DELETE FROM Category "
                    + "WHERE name=? " ;
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, genre);
            pstmt.execute();
        }
        
        catch (SQLException ex) 
        {
            Logger.getLogger(PrivateMovieCollectionDAL.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }  

    public ObservableList<CategoryID> allGenre() 
    {
        ObservableList<CategoryID> Genre 
            = FXCollections.observableArrayList();
        try (Connection con = cm.getConnection())
        {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM Category");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                CategoryID s = new CategoryID();
                s.setCategory(rs.getString("name"));
                s.setId(rs.getInt("id"));
                Genre.add(s);
            }
        }
        
        catch (SQLException ex)
        {
            Logger.getLogger(PrivateMovieCollectionDAL.class.getName()).log(
            Level.SEVERE, null, ex);
        }
        return (ObservableList<CategoryID>) Genre;
    }

    public void addGenreToMovies(CatMovieBE categoryToMovie) {
     try (Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT INTO movieCategory"
                    + "(categoryName, movieName) "
                    + "VALUES(?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, categoryToMovie.getCategoryName());
            pstmt.setString(2, categoryToMovie.getMovieName());
            
           
            int affected = pstmt.executeUpdate();
            if (affected<1)
            {
                throw new SQLException("Movie could not be added");
            }
       }
     catch (SQLException ex) 
        {
            Logger.getLogger(PrivateMovieCollectionDAL.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    
    
}
    


