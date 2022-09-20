package data;

import Main.Implementation;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Play {
    int response;
    List<Songs> songslist;
    int songIndex;

    public void playAllSongs(List<Songs> songslist) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);
        this.songslist = songslist;
        for (int i = 0; i < songslist.size(); i++) {
            songIndex = i;
            playAllSongs(songslist.get(i).getFilepath());
        }
    }

    public void playAllSongs(String songPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {


        Scanner scanner = new Scanner(System.in);
        try {
            String path = songPath;
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            String res = "";

            while (!res.equals("Q")) {
                System.out.println("P = play, T= Pause, S=Stop, L=Loop, R = Reset, Q = Quit,N = NextSong,O = previousSong , E = Exit");
                System.out.print("Enter your choice: ");

                res = scanner.next();
                res = res.toUpperCase();


                switch (res) {
                    case ("P"): {
                        clip.start();
                        break;
                    }
                    case ("T"): {
                        clip.stop();
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
                    case ("N"):
                        songIndex += 1;
                        clip.close();
                        playAllSongs(songslist.get(songIndex).getFilepath());
                        break;
                    case ("O"):
                        songIndex -= 1;
                        clip.close();
                        playAllSongs(songslist.get(songIndex).getFilepath());
                    case ("E"):
                        String[] arg = new String[0];
                        Implementation.main(arg);
                        break;

                    default:
                        System.err.println("PLEASE SELECT THE CORRECT OPTION");
                        res = scanner.next();
                        res = res.toUpperCase();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
