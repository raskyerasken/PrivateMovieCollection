/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import privatemoviecollection.BE.PrivateMovieCollection;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author mr.Andersen
 */
public class PrivateMovieCollectionDAL 
{
   private ConnectionManager cm = new ConnectionManager();
    
    public ArrayList<PrivateMovieCollection> getAllMovies(String search) throws SQLServerException, SQLException
    {
        ArrayList<PrivateMovieCollection> allMovies = new ArrayList<>();
        
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
                    s.setLastview(rs.getString("LastView"));
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
    
    //hello
    public ArrayList<PrivateMovieCollection> getAllByGenre(String search) throws SQLException, SQLServerException
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
                    + "(Id, name, Rating, filelink, lastview) "
                    + "VALUES(?,?,?,?,?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, allMovies.getTitle());
            pstmt.setInt(2, allMovies.getId());
            pstmt.setInt(3, allMovies.getRating());
            pstmt.setString(4, allMovies.getFilelink());
            pstmt.setString(5, allMovies.getLastview());
            
            
            int affected = pstmt.executeUpdate();
            if (affected<1){
                throw new SQLException("Movie could not be added");}
            
            ResultSet rs = pstmt.getGeneratedKeys();
            
            if (rs.next())
            {
                allMovies.setId(rs.getInt(1));
            }
         }
      }
    
    public void remove(PrivateMovieCollection selectedPrivateMovieCollection) throws SQLServerException, SQLException
    {
        try (Connection con = cm.getConnection()) {
            String sql = "DELETE FROM Movie WHERE name)=";
            
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, selectedPrivateMovieCollection.getTitle());
            pstmt.execute();
            
        }
        catch (SQLException ex) {
            Logger.getLogger(PrivateMovieCollectionDAL.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }    
    }

    
    
    
   }
    


