import static org.junit.jupiter.api.Assertions.*;
import data.JukeBoxOperation;
import data.Play;
import data.PlayList;
import data.Songs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

class JukeBoxTestTest {
    JukeBoxOperation jukeBoxOperation;
    Play playSong;
    PlayList playList;
    Songs songs;

    @BeforeEach
    void setUp() {
        jukeBoxOperation = new JukeBoxOperation();
        playSong = new Play();
        songs = new Songs();
        playList = new PlayList();
    }

    @AfterEach
    void tearDown() {
        jukeBoxOperation = null;
        playSong = null;
        songs = null;
        playList = null;
    }


    @Test
    void displaySongTable() throws SQLException, ClassNotFoundException {
        List<Songs> songsList = jukeBoxOperation.displaySongs();
        assertEquals(10, songsList.size());
    }

    @Test
    void searchBySongName() throws SQLException, ClassNotFoundException {
        String songName = "f";
        List<Songs> songsList = jukeBoxOperation.searchBySongName(songName);
        assertEquals(1, songsList.size());
    }

    @Test
    void searchByArtistName() throws SQLException, ClassNotFoundException {
        String artistName = "a";
        List<Songs> songsList = jukeBoxOperation.searchByArtist(artistName);
        assertEquals(2, songsList.size());
    }

    @Test
    void searchByGenreType() throws SQLException, ClassNotFoundException {

        String genreType = "Romantic";
        List<Songs> songsList = jukeBoxOperation.searchByGenre(genreType);
        assertEquals(2, songsList.size());
    }

}
