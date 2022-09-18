package data;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PlayListDetail {
    int response;
    List<Songs> songslist;
    int songIndex;
    public void PlaySong(List<Songs> songslist) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);
        this.songslist = songslist;
        for (int i = 0; i < songslist.size(); i++) {
            songIndex = i;
            PlaySong(songslist.get(i).getFilepath());
        }

    }

    public void PlaySong(String songPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {


        Scanner scanner = new Scanner(System.in);
        try {
            String path = songPath ;
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            String response = "";

            while (!response.equals("Q")) {
                System.out.println("P = play, T= Pause, S=Stop, L=Loop, R = Reset, Q = Quit,N = NextSong,O = previousSong");
                System.out.print("Enter your choice: ");

                response = scanner.next();
                response = response.toUpperCase();


                switch (response) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        //  System.out.println("Songs in queue: ");
                        break;
                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }

                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;

                    case ("Q"):
                        clip.close();
                        break;
                    case("N"):
                        songIndex += 1;
                        clip.close();
                        PlaySong(songslist.get(songIndex).getFilepath());
                        break;
                    case("O"):
                        songIndex -= 1;
                        clip.close();
                        PlaySong(songslist.get(songIndex).getFilepath());
                        break;

                    default:
                        System.err.println("PLEASE SELECT THE CORRECT OPTION");
                        response = scanner.next();
                        response = response.toUpperCase();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
