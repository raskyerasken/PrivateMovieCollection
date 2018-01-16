package privatemoviecollection.BLL;
import javafx.scene.media.MediaPlayer;
import privatemoviecollection.BE.PrivateMovieCollection;

/**
 *
 * @author mr.Andersen
 */
public class MovieManager 
{
    private PrivateMovieCollection movie;
    private MediaPlayer player;
    
    public PrivateMovieCollection getCurrentlyPlayingMovie()
    {
        return this.movie;
    }
    
    public javafx.util.Duration getMovieLength()
    {
        return player.getTotalDuration();
    }
    
    public javafx.util.Duration getMovieTimeElapsed()
    {
        return player.getCurrentTime();
    }
    
    public MediaPlayer getMediaPlayer()
    {
        return player;
    }
}
