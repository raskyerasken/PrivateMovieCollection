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
                    s.setId("ID");
                    s.setLastview("LastView");
                    s.setRating("Rating");
                    s.setFilelink("Filelink");
                    
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
                m.setId("Id");
                m.setTitle("Name");
                
                allGenres.add(m);
            }
        }
        return allGenres;
    }

    
}
