package data;

import Connection.ConnectioningDB;
import Main.Implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayList {
    Play play = new Play();

    public void createAPlayList() throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection connection = ConnectioningDB.getConnection();
        System.out.println("Enter the name of playlist");
        String playlistName = sc.nextLine();
        String sql = "Insert into playlist_detail(playlist_name) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, playlistName); //setting playl name
        int count = preparedStatement.executeUpdate();

        if (count != 0) {
            System.out.println("New PlayList Created");
        }
        System.out.println("1: DO YOU WANT TO ADD SONGS TO PLAY LIST");
        System.out.println("2: GO BACK TO MAIN MENU");
        int choice = sc.nextInt();
        do {
            switch (choice) {
                case (1):
                    addSongsIntoPlayList();
                    break;
                case (2):
                    String[] arg = new String[0];
                    Implementation.main(arg);
            }
        } while (choice < 3);
    }

    public void addSongsIntoPlayList() throws Exception {

        Connection connection = ConnectioningDB.getConnection();
        Scanner scanner = new Scanner(System.in);
        JukeBoxOperation jukeOperation = new JukeBoxOperation();
        String sql = "SELECT playlist_id,playlist_name from playlist_detail;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println("PlayList ID = " + resultSet.getInt(1));
            System.out.println("PlayList Name = " + resultSet.getString(2));
        }

        System.out.println("Please enter the playList ID to add songs");
        int playListID = scanner.nextInt();

        System.out.println("Search song based on following option");
        System.out.println("1 : Display all Songs");
        System.out.println("2 : Artist Name");
        System.out.println("3 : Genre");
        System.out.println("4 : Song Name");
        int option = scanner.nextInt();
        switch (option) {

            case (1):

                System.out.format("%-10s %-30s %-30s %-30s %-30s  \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                List<Songs> allSongs = jukeOperation.displaySongs();


                break;
            case (2):
                System.out.println("ENTER THE ARTIST NAME YOU WANT TO SEARCH");
                scanner.nextLine();
                String artistName = scanner.nextLine();
                List<Songs> songsListOfArtist = jukeOperation.searchByArtist(artistName); //
                System.out.format("%-10s %-30s %-30s %-30s %-30s  \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                System.out.println("-----------------------------------------------------------------------------------------");
                for (Songs songs : songsListOfArtist) {
                    System.out.format("%-10s %-30s %-30s %-30s %-30s  \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                }
                break;
            case (3):
                System.out.println("ENTER THE GENRE TYPE YOU WANT TO SEARCH");
                String genreType = scanner.nextLine();
                List<Songs> songsList = jukeOperation.searchByGenre(genreType);
                System.out.format("%-10s %-30s %-30s %-30s %-30s \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                System.out.println("-----------------------------------------------------------------------------------------");
                for (Songs songs : songsList) {
                    System.out.format("%-10s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                }
                break;
            case (4):
                System.out.println("ENTER THE SONG NAME YOU WANT TO SEARCH");
                scanner.nextLine();
                String songName = scanner.nextLine();
                List<Songs> songsListBasedOnName = jukeOperation.searchBySongName(songName);
                System.out.format("%-10s %-30s %-30s %-30s %-30s \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                System.out.println("-----------------------------------------------------------------------------------------");
                for (Songs songs : songsListBasedOnName) {
                    System.out.format("%-10s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                }
                break;
            case (5):
                String[] arg = new String[0];
                Implementation.main(arg);
                break;

            default:
                System.err.println("PLEASE SELECT THE RIGHT OPTION");
                option = scanner.nextInt();
        }

        System.out.println("Please enter the song_id you want to add to the playlist_id");
        int songID = scanner.nextInt();

        String sqlUpdate = "INSERT INTO playlist(playlist_id,song_id) values(?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
        preparedStatement.setInt(1, playListID);
        preparedStatement.setInt(2, songID);
        int row = preparedStatement.executeUpdate();
        if (row != 0) {
            System.out.println(" Song Added to the playlist ");
        }
        System.out.println("1 Do you Want To Add Some more songs");
        System.out.println("2 Do you Want To play the songs in playlist");
        System.out.println("3 Go back to main menu");
        int choice = scanner.nextInt();
        do {
            switch (choice) {
                case (1):
                    addSongsIntoPlayList();
                    break;
                case (2):
                    List<Songs> playList = existingPlaylist();
                    System.out.format("%-10s %-30s %-30s %-30s %-30s  \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------");
                    for (Songs songs : playList) {
                        System.out.format("%-10s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                    }
                    System.out.println("-----------------------------------------------------------------------------------------------------------------");
                    System.out.println("\t\t1: DO YOU WANT TO PLAY THE PLAYLIST");
                    System.out.println("\t\t2: GO BACK TO MAIN MENU");
                    int select = scanner.nextInt();
                    switch (select) {
                        case (1):
                            play.playAllSongs(playList);
                            break;
                        case (2):
                            String[] arg = new String[0];
                            Implementation.main(arg);
                            break;
                        default:
                            System.err.println("PLEASE SELECT THE CORRECT OPTION");
                    }
                    break;
                case (3):
                    String[] arg = new String[0];
                    Implementation.main(arg);
                    break;
                default:
                    System.err.println("PLEASE SELECT THE RIGHT OPTION");
                    choice = scanner.nextInt();
                    break;
            }
        } while (choice < 4);
    }

    public List<Songs> existingPlaylist() throws SQLException, ClassNotFoundException {

        Connection connection = ConnectioningDB.getConnection();
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        List<Songs> playListSongs = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT playlist_id,playlist_name from playlist_detail;");
        while (resultSet.next()) {
            System.out.println("playListID = " + resultSet.getInt(1));
            System.out.println("playListName = " + resultSet.getString(2));
        }
        System.out.println(" Please enter the playListID you want to open");

        int playListID = scanner.nextInt();
        String sql = "Select song_id from playlist where playlist_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, playListID);

        ResultSet resultSet1 = preparedStatement.executeQuery();
        String sql3 = "Select * from songs where song_id = ?";
        while (resultSet1.next()) {
            int songId = resultSet1.getInt(1);
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql3);
            preparedStatement1.setInt(1, songId);
            ResultSet resultSet2 = preparedStatement1.executeQuery();
            while (resultSet2.next()) {
                playListSongs.add(new Songs(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getString(3), resultSet2.getString(4), resultSet2.getString(5), resultSet2.getString(6)));
            }
        }
        return playListSongs;
    }
}

