package data;

import Connection.ConnectioningDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Songs {

    private int songId;
    private String songsName;
    private String artist;
    private String duration;
    private String genre;
    private String filepath;
    public Songs() {
    }

    public Songs(int songId, String songsName, String artist, String duration, String genre, String filepath) {
        this.songId = songId;
        this.songsName = songsName;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
        this.filepath = filepath;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongsName() {
        return songsName;
    }

    public void setSongsName(String songsName) {
        this.songsName = songsName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }


    public String returnPath(int songId) throws SQLException, ClassNotFoundException { // returning path &
        String path = "";
        Connection connection = ConnectioningDB.getConnection();
        String query = "Select filepath from songs where song_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, songId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            path = resultSet.getString(1);
        }
        return path;
    }

}
