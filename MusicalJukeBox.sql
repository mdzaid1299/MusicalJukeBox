create database if not exists  MusicalJukeboxts;
use MusicalJukeboxts;

create table Songs (song_id int primary key ,song_name varchar(30),song_duration varchar(30),
song_album varchar(30) ,song_genre varchar(30), filepath varchar(90));

CREATE TABLE `musicaljukeboxts`.`playlist` (
  `playlist_id` INT NOT NULL,
  `song_id` INT NOT NULL,
  PRIMARY KEY (`playlist_id`));


insert into songs value ( 100 , 'Feel Good' , 'Gorillaz' , '3.25'  , 'R&B', 'src/main/resources/Gorillaz - Feel Good Inc. (Official Video).wav');
insert into songs value ( 200 , "Johnny B good" , "Chuck Berry" , "4.67" ,  "Blue", "src/main/resources/CHUCK BERRY ： Johnny B. Goode (1958) HD.wav");
insert into songs value ( 300 , "3 pigs and the wolf trap" , "Jools TV" , "2.87" , "Nursery Rap", "src/main/resources/3 Little Pigs & The Big Bad Wolf ｜ Jools TV™️ Nursery Rhymes & Kid Songs ｜ Trapery Rhymes™️.wav");
insert into songs value ( 400 , "You know how we do it " , "Ice cube" , "5.6" ,  "90's Hip Hop", "src/main/resources/Ice cube you know how we do it lyrics on the screen.wav");
insert into songs value ( 500, "I'll Play The Blues For You" , "Danial Castro" , "4.25"  , "Jazz", "src/main/resources/Daniel Castro - I'll Play the Blues for You Lyrics.wav");
insert into songs value ( 600 , "Mysterious Girl" , "Peter Andre" , "3.32" , "Pop Reggie", "src/main/resources/Peter Andre - Mysterious Girl (Official Music Video).wav");
insert into songs value ( 700 , "Hamari Adhuri Kahani" , "Arijit Singh" , "4.78"  , "Sad", "src/main/resources/Hamari Adhuri Kahani (Title Song) Arijit Singh 320Kbps.wav");
insert into songs value ( 800 , "Shyad" , "Arijit" , "6.21"  , "Romantic", "src/main/resources/Shayad - Love Aaj Kal 128 Kbps.wav");
insert into songs value ( 900 , "Tere Naam" , "Udit Narayan" , "2.25"  , "Romantic", "src/main/resources/Tere Naam - Tere Naam 128 Kbps.wav");
insert into songs value ( 1000 , "Bailamos" , "Enrique Iglesias" , "3.25" , "Pop", "src/main/resources/Enrique Iglesias - Bailamos.wav");

drop database MusicalJukeboxts;
