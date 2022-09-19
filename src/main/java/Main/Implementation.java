package Main;

import data.JukeBoxOperation;
import data.Play;
import data.PlayList;
import data.Songs;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Implementation {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Songs songsObj = new Songs();
        Play playSongObj = new Play();
        PlayList playListObj = new PlayList();
        JukeBoxOperation jukeBoxOperation = new JukeBoxOperation();

        System.out.println("-------------------------------------Welcome to Jukebox-------------------------------------");
        int opt = 0;
        while (opt != 3) {
            System.out.println("============================================================================================");
            System.out.println(".....PLEASE SELECT THE OPTION .....");
            System.out.println("Enter 1 : Search A Song\nEnter 2 : Create new Playlist\nEnter 3 : Check Out your Existing Playlists\nEnter 4 : Exit");
            System.out.println("============================================================================================");
            opt = scanner.nextInt();
            try {


                switch (opt) {

                    case (1):
                        System.out.println("...............Search song based on following option.............");
                        System.out.println("Enter 1 : Display all Songs\nEnter 2 : Display song by Artist Name\nEnter 3 : Display song by Genre \nEnter 4 : Display song by Song Name\nEnter 5 : Go back to previous menu");
                        System.out.println("============================================================================================");
                        int option = scanner.nextInt();
                        switch (option) {

                            case (1):
                                System.out.format("%-10s %-30s %-30s %-30s %-30s \n", "|SongID|", "|SongName|", "|Artist|", "|Duration|", "|Genre|");
                                List<Songs> allSongs = jukeBoxOperation.displaySongs();

                                System.out.println("PLEASE SELECT THE OPTION \n1: Play a song! \n2: Go to your playlists! \n3: Go back to the main menu!");
                                int choice = scanner.nextInt();

                                switch (choice) {
                                    case (1):
                                        System.out.println("Enter song id which you want to play");
                                        int songID = scanner.nextInt();
                                        playSongObj.playAllSongs(songsObj.returnPath(songID));
                                        break;
                                    case (2):
                                        System.out.println("1.For Creating new playlist\n2.Go back to existing playlist");
                                        int userChoice = scanner.nextInt();
                                        switch (userChoice) {
                                            case (1):
                                                playListObj.createAPlayList();
                                            case (2):
                                                System.out.format("%-10s %-30s %-30s %-30s %-30s  \n", "|SongID|", "|SongName|", "|Artist|", "|Duration|", "|Genre|");
                                                System.out.println("-----------------------------------------------------------------------------------------");
                                                List<Songs> playList = playListObj.existingPlaylist();
                                                for (Songs songs : playList) {
                                                    System.out.format("%-10s %-30s %-30s %-30s %-30s  \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                                                }
                                                System.out.println("============================================================================================");
                                                System.out.println("Enter 1: DO YOU WANT TO PLAY THE ENTIRE PLAYLIST\nEnter 2: DO YOU WANT TO PLAY A SONG FROM PLAYLIST\nEnter 3: GO BACK TO MAIN MENU");
                                                int select = scanner.nextInt();
                                                switch (select) {
                                                    case (1):
                                                        playSongObj.playAllSongs(playList);
                                                        break;
                                                    case (2):
                                                        System.out.format("%-10s %-30s %-30s %-30s %-30s \n","|SongID|", "|SongName|", "|Artist|", "|Duration|", "|Genre|");
                                                        System.out.println("-----------------------------------------------------------------------------------------");
                                                        for (Songs songs : playList) {
                                                            System.out.format("%-10s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                                                        }
                                                        System.out.println("Please enter the song id you want to play....");
                                                        int song_id = scanner.nextInt();
                                                        playSongObj.playAllSongs(songsObj.returnPath(song_id));
                                                        //break;
                                                    case (3):
                                                        String[] arg = new String[0];
                                                        Implementation.main(arg);
                                                        break;
                                                    default:
                                                        System.err.println("Not a valid option");
                                                }
                                        }
                                        break;
                                    case (3):
                                        String[] arg = new String[0];
                                        Implementation.main(arg);
                                        break;
                                    default:
                                        System.err.println("PLEASE SELECT THE RIGHT OPTION");
                                }
                                break;

                            case (2):
                                System.out.println("PLEASE ENTER THE ARTIST NAME YOU WANT TO SEARCH");
                                scanner.nextLine();
                                String artistName = scanner.nextLine();
                                List<Songs> songsListOfArtist = jukeBoxOperation.searchByArtist(artistName);
                                System.out.format("%-10s %-30s %-30s %-30s %-30s  \n", "|SongID|", "|SongName|", "|Artist|", "|Duration|", "|Genre|");
                                System.out.println("-----------------------------------------------------------------------------------------");
                                for (Songs songs : songsListOfArtist) {
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                                }
                                jukeBoxOperation.playSongs();
                                break;
                            case (3):
                                System.out.println("PLEASE ENTER THE GENRE TYPE YOU WANT TO SEARCH");
                                String genreType = scanner.nextLine();
                                List<Songs> songsList1 = jukeBoxOperation.searchByGenre(genreType);
                                System.out.format("%-10s %-30s %-30s %-30s %-30s \n", "|SongID|", "|SongName|", "|Artist|", "|Duration|", "|Genre|");
                                System.out.println("-----------------------------------------------------------------------------------------");
                                for (Songs songs : songsList1) {
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                                }
                                jukeBoxOperation.playSongs();
                                break;
                            case (4):
                                System.out.println("PLEASE ENTER THE SONG NAME YOU WANT TO SEARCH");
                                scanner.nextLine();
                                String songName = scanner.nextLine();
                                List<Songs> songsListBasedOnName = jukeBoxOperation.searchBySongName(songName);
                                System.out.format("%-10s %-30s %-30s %-30s %-30s  \n", "|SongID|", "|SongName|", "|Artist|", "|Duration|", "|Genre|");
                                System.out.println("-----------------------------------------------------------------------------------------");
                                for (Songs songs : songsListBasedOnName) {
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                                }
                                jukeBoxOperation.playSongs();
                                break;
                            case (5):
                                String[] arg = new String[0];
                                Implementation.main(arg);
                                break;

                            default:
                                System.err.println("PLEASE SELECT THE RIGHT OPTION");
                        }
                        break;

                    case (2):
                        playListObj.createAPlayList();
                        break;
                    case (3):
                        List<Songs> playListEx = playListObj.existingPlaylist();
                        System.out.format("%-10s %-30s %-30s %-30s %-30s  \n", "|SongID|", "|SongName|", "|Artist|", "|Duration|", "|Genre|");
                        System.out.println("-----------------------------------------------------------------------------------------");
                        for (Songs songs : playListEx) {
                            System.out.format("%-10s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                        }
                        System.out.println("-----------------------------------------------------------------------------------------");
                        System.out.println("1: DO YOU WANT TO PLAY THE ENTIRE PLAYLIST");
                        System.out.println("2: DO YOU WANT TO PLAY A SONG FROM PLAYLIST \n 3. DO YOU WANT TO ADD SONG INTO EXISTING PLAYLIST");
                        System.out.println("3: GO BACK TO MAIN MENU");
                        int select = scanner.nextInt();
                        switch (select) {
                            case (1):
                                playSongObj.playAllSongs(playListEx);
                                Implementation.main(args);
                                break;
                            case (2):
                                System.out.format("%-10s %-30s %-30s %-30s %-30s  \n","|SongID|", "|SongName|", "|Artist|", "|Duration|", "|Genre|");
                                System.out.println("-----------------------------------------------------------------------------------------");
                                for (Songs songs : playListEx) {
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s  \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                                }
                                System.out.println("Enter the song id you want to play");
                                int song_id = scanner.nextInt();
                                playSongObj.playAllSongs(songsObj.returnPath(song_id));
                                break;
                            case 3:
                                playListObj.addSongsIntoPlayList();
                                break;
                            case 4:
                                Implementation.main(args);
                                break;
                        }
                        break;

                    case (4):
                        System.exit(0);

                }
            } catch (UnsupportedAudioFileException | LineUnavailableException | SQLException | IOException |
                     ClassNotFoundException e) {
                System.out.println("e = " + e);
            }
        }
    }
}