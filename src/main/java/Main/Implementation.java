package Main;

import data.JukeBoxOperation;
import data.PlayList;
import data.PlayListDetail;
import data.Songs;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Implementation {
    public static void main(String[] args) throws Exception{
            Scanner scanner = new Scanner(System.in);

            Songs songsObj = new Songs();
            PlayListDetail playListDetail = new PlayListDetail();
            PlayList playListObj = new PlayList();
            JukeBoxOperation jukeBoxOperation = new JukeBoxOperation();
            Implementation implementation = new Implementation();

            int optionsOfMainMenu = 0;
            while (optionsOfMainMenu != 3) {
                System.out.println("============================================================================");
                System.out.println("\t\tWELCOME TO YOUR  MUSIC SYSTEM");
                System.out.println("\t\tPLEASE SELECT THE OPTION FROM THE MENU");
                System.out.println("\t\t1 : Search A Song");
                System.out.println("\t\t2 : Go To Existing PlayList");
                System.out.println("\t\t3 : Exit");
                System.out.println("============================================================================");
                optionsOfMainMenu = scanner.nextInt();

                switch (optionsOfMainMenu) {

                    case (1):
                        System.out.println("\t\tSearch song based on following option");
                        System.out.println("\t\t1 : Display all Songs");
                        System.out.println("\t\t2 : Artist Name");
                        System.out.println("\t\t3 : Genre");
                        System.out.println("\t\t4 : Song Name");
                        System.out.println("\t\t5 : GO BACK TO PREVIOUS MENU");
                        System.out.println("============================================================================");
                        try {

                            int option = scanner.nextInt();
                            switch (option) {

                                case (1):
                                    List<Songs> allSongs = jukeBoxOperation.displaySongs();
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                                    System.out.println("-----------------------------------------------------------------------------------------");
                                    for (Songs songs : allSongs) {
                                        System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                                    }
                                    System.out.println("PLEASE SELECT THE OPTION \n1: PLAY A SONG \n2: GO TO PLAYLIST\n3: GO BACK TO MAIN MENU");
                                    int choice = scanner.nextInt();

                                    switch (choice) {
                                        case (1):
                                            System.out.println("PLEASE ENTER THE SONG ID YOU WANT TO PLAY");
                                            int songID = scanner.nextInt();
                                            playListDetail.PlaySong(songsObj.returnPath(songID));
                                            break;
                                        case (2):
                                            System.out.println("1 FOR CREATING A NEW PLAYLIST\n2 FOR EXISTING PLAYLIST");
                                            int userChoice = scanner.nextInt();
                                            switch (userChoice) {
                                                case (1):
                                                    playListObj.createAPlayList();
                                                case (2):
                                                    List<Songs> playList = playListObj.existingPlaylist();
                                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                                                    System.out.println("-----------------------------------------------------------------------------------------");
                                                    for (Songs songs : playList) {
                                                        System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                                                    }
                                                    System.out.println("-----------------------------------------------------------------------------------------");
                                                    System.out.println("\t\t1: DO YOU WANT TO PLAY THE PLAYLIST");
                                                    System.out.println("\t\t2: GO BACK TO MAIN MENU");
                                                    int select = scanner.nextInt();
                                                    switch (select) {
                                                        case (1):
                                                            playListDetail.PlaySong(playList);
                                                            break;
                                                        case (2):
                                                            String[] arg = new String[0];
                                                            Implementation.main(arg);
                                                            break;
                                                        default:
                                                            System.err.println("PLEASE SELECT THE CORRECT OPTION");
                                                    }

                                            }
                                            break;
                                        case (3):
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
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                                    System.out.println("-----------------------------------------------------------------------------------------");
                                    for (Songs songs : songsListOfArtist) {
                                        System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                                    }
                                    break;
                                case (3):
                                    System.out.println("PLEASE ENTER THE GENRE TYPE YOU WANT TO SEARCH");
                                    String genreType = scanner.nextLine();
                                    List<Songs> songsList = jukeBoxOperation.searchByGenre(genreType);
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                                    System.out.println("-----------------------------------------------------------------------------------------");
                                    for (Songs songs : songsList) {
                                        System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
                                    }
                                    break;
                                case (4):
                                    System.out.println("PLEASE ENTER THE SONG NAME YOU WANT TO SEARCH");
                                    scanner.nextLine();
                                    String songName = scanner.nextLine();
                                    List<Songs> songsListBasedOnName = jukeBoxOperation.searchBySongName(songName);
                                    System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s \n", "SongID", "SongName", "Artist", "Duration", "GenreType");
                                    System.out.println("-----------------------------------------------------------------------------------------");
                                    for (Songs songs : songsListBasedOnName) {
                                        System.out.format("%-10s %-30s %-30s %-30s %-30s %-30s \n", songs.getSongId(), songs.getSongsName(), songs.getArtist(), songs.getDuration(), songs.getGenre());
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

                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                            System.out.println("e = " + e);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                }
            }
        }
    }
