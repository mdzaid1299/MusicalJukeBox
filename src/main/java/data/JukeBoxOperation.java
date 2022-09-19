package data;

import Connection.ConnectioningDB;
import Main.Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JukeBoxOperation {


    public List<Songs> displaySongs() throws SQLException, ClassNotFoundException {
        List<Songs> songList = new ArrayList<>();
        Connection connection = ConnectioningDB.getConnection();

        String query = "select * from songs";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {

            Songs songData = new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));

            songData.setSongId(resultSet.getInt(1));
            songData.setSongsName(resultSet.getString(2));
            songData.setArtist(resultSet.getString(3));
            songData.setDuration(resultSet.getString(4));
            songData.setGenre(resultSet.getString(5));
            songData.setFilepath(resultSet.getString(6));

            songList.add(songData);
        }
        for (Songs songs : songList) {
            System.out.format("%-10s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());

        }

        return songList;
    }

    public List<Songs> searchBySongName(String songName) throws SQLException, ClassNotFoundException {

        List<Songs> newLL = new ArrayList<>();
        Connection connection = ConnectioningDB.getConnection();
        String sql = "select * from songs where song_name like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, songName + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            newLL.add(new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        return newLL;
    }


    public List<Songs> searchByArtist(String artistName) throws SQLException, ClassNotFoundException {

        List<Songs> newLL = new ArrayList<>();
        Connection connection = ConnectioningDB.getConnection();
        String sql = "select * from songs where song_artist like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, artistName + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            newLL.add(new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        return newLL;
    }

    public List<Songs> searchByGenre(String genre) throws SQLException, ClassNotFoundException {

        List<Songs> newLL = new ArrayList<>();
        Connection connection = ConnectioningDB.getConnection();
        String sql = "select * from songs where song_genre like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, genre + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            newLL.add(new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        return newLL;
    }

    public void playSongs() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Play playSong = new Play();
        Songs songs = new Songs();
        PlayList playList = new PlayList();

        System.out.println("PLEASE SELECT THE OPTION \n1: PLAY A SONG \n2: GO TO PLAYLIST\n3: GO BACK TO MAIN MENU");
        int choice = scanner.nextInt();

        switch (choice) {
            case (1):
                System.out.println("PLEASE ENTER THE SONG ID YOU WANT TO PLAY");
                int songID = scanner.nextInt();
                playSong.playAllSongs(songs.returnPath(songID));
                break;
            case (2):
                System.out.println("1 FOR CREATING A NEW PLAYLIST\n2 FOR EXISTING PLAYLIST");
                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case (1):
                        playList.createAPlayList();
                    case (2):
                        List<Songs> playList1 = playList.existingPlaylist ();
                        System.out.format("%-10s %-30s %-30s %-30s %-30s \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                        System.out.println("-----------------------------------------------------------------------------------------");
                        for (Songs s : playList1) {
                            System.out.format("%-10s %-30s %-30s %-30s %-30s \n", s.getSongId(), s.getSongsName(), s.getArtist(), s.getDuration(), s.getGenre());
                        }
                        System.out.println("-----------------------------------------------------------------------------------------");
                        System.out.println("\t\t1: DO YOU WANT TO PLAY THE ENTIRE PLAYLIST");
                        System.out.println("\t\t2: DO YOU WANT TO PLAY A SONG FROM PLAYLIST");
                        System.out.println("\t\t3: GO BACK TO MAIN MENU");
                        int select = scanner.nextInt();
                        switch (select) {
                            case (1):
                                playSong.playAllSongs(playList1);
                                break;
                            case (2):
                                System.out.format("%-10s %-30s %-30s %-30s %-30s \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                                System.out.println("-----------------------------------------------------------------------------------------");
                                for (Songs s2 : displaySongs()) {
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s\n", s2.getSongId(), s2.getSongsName(), s2.getArtist(), s2.getDuration(), s2.getGenre());
                                }
                                System.out.println("PLEASE ENTER THE SONG-ID YOU WANT TO PLAY");
                                int song_id = scanner.nextInt();
                                playSong.playAllSongs(songs.returnPath(song_id));

                            case (3):
                                String[] arg = new String[0];
                                Implementation.main(arg);
                                break;
                            default:
                                System.err.println("PLEASE SELECT THE CORRECT OPTION");
                        }

                } break;
            case (3):
                break;
            default:
                System.err.println("PLEASE SELECT THE RIGHT OPTION");


        }
    }
}