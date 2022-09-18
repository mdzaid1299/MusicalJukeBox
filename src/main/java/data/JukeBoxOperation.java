package data;

import Connection.ConnectioningDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            System.out.format("%-10s %-18s %-15s %-18s %-18s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());

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
}