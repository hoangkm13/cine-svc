-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: cine.c8jtzysm4ldc.us-west-2.rds.amazonaws.com    Database: cine_aws_db
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

INSERT INTO `actor` VALUES (1,'59','Tom Kenny'),(2,'64 ','Bill Fagerbakke'),(3,'19','Tyler Jake');

--
-- Table structure for table `actor_film`
--

DROP TABLE IF EXISTS `actor_film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actor_film` (
  `film_id` bigint NOT NULL,
  `actor_id` bigint NOT NULL,
  KEY `FK3nf2jjwluwx6klglcul22gc25` (`actor_id`),
  KEY `FK80gycmnpxqtiaf3m3duaobefw` (`film_id`),
  CONSTRAINT `FK3nf2jjwluwx6klglcul22gc25` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`),
  CONSTRAINT `FK80gycmnpxqtiaf3m3duaobefw` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_film`
--

INSERT INTO `actor_film` VALUES (1,1),(2,2),(3,3),(4,1),(5,2),(6,3),(7,1),(8,2),(9,3),(10,1),(11,2),(12,3),(13,1),(14,2),(15,3),(16,1),(17,2),(18,3),(19,1),(20,2),(21,3),(22,1),(23,2),(24,3),(25,1),(26,2),(27,3),(28,1),(29,2),(30,3),(31,1),(32,2),(33,3),(34,1),(35,2),(36,3),(37,1),(38,2),(39,3),(40,1),(41,2),(42,3),(43,1),(44,2),(45,3),(46,1),(47,2),(48,3),(49,1),(50,2),(1,3),(2,1),(3,2),(4,3),(5,1),(6,2),(7,3),(8,1),(9,2),(10,3),(11,1),(12,2),(13,3),(14,1),(15,2),(16,3),(17,1),(18,2),(19,3),(20,1),(21,2),(22,3),(23,1),(24,2),(25,3),(26,1),(27,2),(28,3),(29,1),(30,2),(31,3),(32,1),(33,2),(34,3),(35,1),(36,2),(37,3),(38,1),(39,2),(40,3),(41,1),(42,2),(43,3),(44,1),(45,2),(46,3),(47,1),(48,2),(49,3),(50,1);

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment_text` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `film_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb6gnv47yxa2jewd4jpvm3pnfk` (`film_id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`),
  CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKb6gnv47yxa2jewd4jpvm3pnfk` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` VALUES (1,'Comment by Nghia','2022-06-20 09:15:59',NULL,NULL),(2,'Comment by Nghia','2022-06-20 09:16:05',NULL,NULL),(3,'ádasdasdasd','2022-06-20 09:16:39',NULL,NULL),(4,'','2022-06-20 09:17:30',NULL,NULL),(5,'','2022-06-20 09:19:00',NULL,NULL),(6,'test02','2022-06-20 13:18:33',19,14),(7,'test04','2022-06-20 13:18:46',15,14),(8,'test03','2022-06-20 13:18:51',15,14),(9,'test03','2022-06-20 13:18:52',15,14),(10,'phim rat hay','2022-06-20 20:39:30',15,16),(11,'Comment By Ngoc Nghia','2022-06-20 15:14:40',42,14),(12,'Dcm Hoanggggggg','2022-06-20 15:16:57',42,14),(13,'Comment By Nghia','2022-06-20 15:18:13',42,14),(14,'DMMMM THANG HOANGGGGGG','2022-06-20 15:18:30',42,14),(15,'Test comment','2022-06-21 01:59:39',42,14),(16,'phim rat hay','2022-06-21 15:11:31',15,16),(17,'phim rat hay','2022-06-21 15:11:36',15,16),(18,'phim rat hay','2022-06-21 15:11:40',15,16),(19,'phim rat hay','2022-06-21 15:11:44',15,16),(20,'phim rat hay','2022-06-21 15:11:48',15,16),(21,'phim rat hay','2022-06-21 15:11:52',15,16),(22,'phim rat hay','2022-06-21 15:11:56',15,16),(23,'phim rat hay','2022-06-21 15:12:01',15,16),(24,'phim rat hay','2022-06-21 15:12:06',15,16),(25,'phim rat hay','2022-06-21 15:48:09',15,16),(26,'Test comment','2022-06-23 09:40:29',3,14),(27,'AAAAAA','2022-06-23 09:40:44',3,14),(28,'Test comment','2022-06-23 17:53:26',8,14);

--
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `director` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

INSERT INTO `director` VALUES (1,'50','Micheal Bay'),(2,'60','Kevin Feige');

--
-- Table structure for table `dislikes`
--

DROP TABLE IF EXISTS `dislikes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dislikes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `film_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `DISLIKE_UC_FILM_ID_USER_ID` (`film_id`,`user_id`),
  KEY `FK3ff9bl5beacb4i22r3ggkmglc` (`user_id`),
  CONSTRAINT `FK3ff9bl5beacb4i22r3ggkmglc` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKunp2kk6149x25j6tnyv8p1tf` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dislikes`
--


--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `film_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_FILM_ID_USER_ID` (`film_id`,`user_id`),
  KEY `FKh3f2dg11ibnht4fvnmx60jcif` (`user_id`),
  CONSTRAINT `FKh3f2dg11ibnht4fvnmx60jcif` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKu45lgbb7s8q9qdd83bf88672` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

INSERT INTO `favorite` VALUES (8,1,17),(3,7,14),(12,7,17),(5,9,14),(6,22,14),(9,42,17);

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `description` longtext NOT NULL,
  `maturity` int NOT NULL,
  `slug` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `rating_star` int NOT NULL,
  `director_id` bigint DEFAULT NULL,
  `year` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4pqpakiyka8wwbiicmc0rgtwi` (`director_id`),
  CONSTRAINT `FK4pqpakiyka8wwbiicmc0rgtwi` FOREIGN KEY (`director_id`) REFERENCES `director` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

INSERT INTO `film` VALUES (1,'2021-09-17 08:20:20','An exploration of big cat breeding and its bizarre underworld, populated by eccentric characters.',18,'tiger-king','Tiger King','2022-06-19 16:27:10',5,2,2022),(2,'2021-09-17 08:20:20','Amanda Marie Knox is an American woman who spent almost four years in an Italian prison.',12,'amanda-knox','Amanda Knox','2022-06-19 16:27:14',3,1,2022),(3,'2021-09-17 08:20:20','Citizenfour is a 2014 documentary film directed by Laura Poitras, concerning Edward Snowden and the NSA spying scandal.',12,'citizenfour','Citizenfour','2022-06-19 16:27:09',5,1,2022),(4,'2021-09-17 08:20:20','Director Morgan Spurlock\'s social experiment in fast-food gastronomy sees him attempting to subsist uniquely on food from the McDonalds',12,'super-size-me','Super Size Me','2022-06-19 16:27:13',1,2,2022),(5,'2021-09-17 08:20:20','Filmmaker James Marsh masterfully recreates high-wire daredevil Philippe Petit\'s 1974 stunt walking on a wire across the Twin Towers.',12,'man-on-wire','Man on Wire','2022-06-19 16:27:13',2,1,2022),(6,'2021-09-17 08:20:20','A motley group of office workers go through hilarious misadventures at the Scranton, Pennsylvania, branch of the Dunder Mifflin Paper Company.',15,'the-office','The Office','2022-06-19 16:27:05',3,1,2022),(7,'2021-09-17 08:20:20','The Bluth family, once a prominent name in the business, loses everything after the head patriarch gets convicted for fraud.',15,'arrested-development','Arrested Development','2022-06-19 16:27:12',4,2,2022),(8,'2021-09-17 08:20:20','Larry David, a famous television writer and producer, gets into various misadventures with his friends and celebrity colleagues in Los Angeles.',15,'curb-your-enthusiasm','Curb Your Enthusiasm','2022-06-19 16:27:25',1,2,2022),(9,'2021-09-17 08:20:20','Peter Griffin and his family of two teenagers, a smart dog, a devilish baby and his wife find themselves in some of the most hilarious scenarios.',15,'family-guy','Family Guy','2022-06-19 16:27:25',4,1,2022),(10,'2021-09-17 08:20:20','Four young, schoolgoing boys, Stan Marsh, Kyle Broflovski, Eric Cartman and Kenny McCormick, who live in South Park set out on various adventures.',15,'south-park','South Park','2022-06-19 16:27:28',4,2,2022),(11,'2021-09-17 08:20:20','Peppa, an outgoing preschool pig, participates in many energetic activities. She learns something new every day and has a lot of fun with her family and friends.',0,'peppa-pig','Peppa Pig','2022-06-19 16:27:24',5,1,2022),(12,'2021-09-17 08:20:20','Dora, a seven-year-old girl of Latin American descent, embarks upon numerous adventures in the wilderness with her friend Boots, a monkey, and a variety of fun and useful tools.',0,'dora-the-explorer','Dora The Explorer','2022-06-19 16:27:27',2,1,2022),(13,'2021-09-17 08:20:20','Six brave puppies, captained by a tech-savvy ten-year-old boy, Ryder, work together to accomplish high-stakes rescue missions to safeguard the residents of the Adventure Bay community.',0,'paw-patrol','PAW Patrol','2022-06-19 16:27:27',3,2,2022),(14,'2021-09-17 08:20:20','Bespectacled aardvark Arthur Read demonstrates to kids how to deal with such childhood traumas and challenges as homework, teachers and bullies.',0,'arthur','Arthur','2022-06-19 16:27:11',1,1,2022),(15,'2021-09-17 08:20:20','A yellow sea sponge named SpongeBob SquarePants lives in the city of Bikini Bottom deep in the Pacific Ocean.',0,'spongebob','SpongeBob','2022-06-19 16:27:26',2,1,2022),(16,'2021-09-17 08:20:20','Exonerated after spending nearly two decades in prison for a crime he did not commit, Steven Avery filed suit against Manitowoc County, Wis., and several individuals involved with his arrest.',18,'making-a-murderer','Making a Murderer','2022-06-19 16:27:18',4,1,2022),(17,'2021-09-17 08:20:20','An innocent man is accused of murder, leading his attorney on a wild chase to confirm his alibi using raw footage from a television show.',18,'long-shot','Long Shot','2022-06-19 16:27:19',5,2,2022),(18,'2021-09-17 08:20:20','Henry Lee Lucas was an American convicted serial killer whose crimes spanned from 1960 to 1983. He was convicted of murdering eleven people and condemned to death for the murder of Debra Jackson, although his sentence would be commuted to life in prison in 1998.',18,'the-confession-killer','The Confession Killer','2022-06-19 16:27:23',2,2,2022),(19,'2021-09-17 08:20:20','Henry Lee Lucas was an American convicted serial killer whose crimes spanned from 1960 to 1983. He was convicted of murdering eleven people and condemned to death for the murder of Debra Jackson.',18,'the-innocent-man','The Innocent Man','2022-06-19 16:27:18',1,2,2022),(20,'2021-09-17 08:20:20','In 2001 novelist Michael Peterson\'s wife died, and he claimed she perished after falling down stairs at their home. The medical examiner, however, determined that she had been beaten with a weapon',18,'the-staircase','The Staircase','2022-06-19 16:27:21',4,1,2022),(21,'2021-09-17 08:20:20','Will Hunting, a genius in mathematics, solves all the difficult mathematical problems. When he faces an emotional crisis, he takes help from psychiatrist Dr Sean Maguireto, who helps him recover.',12,'good-will-hunting','Good Will Hunting','2022-06-19 16:27:22',5,1,2022),(22,'2021-09-17 08:20:20','Forrest Gump, a man with a low IQ, joins the army for service where he meets Dan and Bubba. However, he cannot stop thinking about his childhood sweetheart Jenny Curran, whose life is messed up.',12,'forrest-gump','Forrest Gump','2022-06-19 16:27:15',1,2,2022),(23,'2021-09-17 08:20:20','Social misfit Juno protects herself with a caustic wit, but her unplanned pregnancy has the teen getting more involved in the lives of her baby\'s adoptive parents than she expected.',12,'juno','Juno','2022-06-19 16:27:20',2,1,2022),(24,'2021-09-17 08:20:20','Gil arrives with his fiancee and her family in Paris for a vacation, even as he tries to finish his debut novel. He is beguiled by the city, which takes him to a time past, away from his fiancee.',12,'midnight-in-paris','Midnight In Paris','2022-06-19 16:27:04',5,2,2022),(25,'2021-09-17 08:20:20','Dewey Finn, an amateur rock enthusiast, slyly takes up his friend\'s substitute teacher\'s job. Bearing no qualifications for it, he instead starts training the students to form a band.',12,'school-of-rock','School of Rock','2022-06-19 16:27:05',5,1,2022),(26,'2021-09-17 08:20:20','Two friends and fellow magicians become bitter enemies after a sudden tragedy. As they devote themselves to this rivalry, they make sacrifices that bring them fame but with terrible consequences.',15,'the-prestige','The Prestige','2022-06-19 16:27:08',1,1,2022),(27,'2021-09-17 08:20:20','Discontented with his capitalistic lifestyle, a white-collared insomniac forms an underground fight club with Tyler, a careless soap salesman. The project soon spirals down into something sinister.',15,'fight-club','Fight Club','2022-06-19 16:27:03',2,2,2022),(28,'2021-09-17 08:20:20','King George VI tries to overcome his stammering problem with the help of speech therapist Lionel Logue and makes himself worthy enough to lead his country through World War II.',15,'kings-speech','Kings Speech','2022-06-19 16:27:07',1,1,2022),(29,'2021-09-17 08:20:20','Hugh Glass, a legendary frontiersman, is severely injured in a bear attack and is abandoned by his hunting crew. He uses his skills to survive and take revenge on his companion, who betrayed him.',15,'the-revenant','The Revenant','2022-06-19 16:27:08',4,2,2022),(30,'2021-09-17 08:20:20','Mark Zuckerberg creates a social networking site, Facebook, with the help of his friend Eduardo Saverin. But soon, a string of lies tears their relationship apart even as Facebook connects people.',12,'the-social-network','The Social Network','2022-06-19 16:27:20',1,1,2022),(31,'2021-09-17 08:20:20','Teddy Daniels and Chuck Aule, two US marshals, are sent to an asylum on a remote island in order to investigate the disappearance of a patient, where Teddy uncovers a shocking truth about the place.',15,'shutter-island','Shutter Island','2022-06-19 16:27:06',2,2,2022),(32,'2021-09-17 08:20:20','Nick Dunne discovers that the entire media focus has shifted on him when his wife Amy Dunne disappears on the day of their fifth wedding anniversary.',15,'gone-girl','Gone Girl','2022-06-19 16:26:59',3,2,2022),(33,'2021-09-17 08:20:20','When the police take time to find Keller Dover\'s daughter and her friend, he decides to go on a search himself. His desperation leads him closer to finding the truth and also jeopardises his own life.',15,'prisoners','Prisoners','2022-06-19 16:26:58',4,1,2022),(34,'2021-09-17 08:20:20','A serial killer begins murdering people according to the seven deadly sins. Two detectives, one new to the city and the other about to retire, are tasked with apprehending the criminal.',15,'seven','Seven','2022-06-19 16:27:23',2,2,2022),(35,'2021-09-17 08:20:20','Robert Graysmith, a cartoonist by profession, finds himself obsessively thinking about the Zodiac killer. He uses his puzzle-solving abilities to get closer to revealing the identity of the killer.',15,'zodiac','Zodiac','2022-06-19 16:27:00',1,1,2022),(36,'2021-09-17 08:20:20','Dracula, who owns a high-end resort for monsters, attempts to keep his daughter from falling in love with Jonathan, a human.',0,'hotel-transylvania','Hotel Transylvania','2022-06-19 16:26:56',1,1,2022),(37,'2021-09-17 08:20:20','Gru, a criminal mastermind, adopts three orphans as pawns to carry out the biggest heist in history. His life takes an unexpected turn when the little girls see him as their potential father.',0,'despicable-me','Despicable Me','2022-06-19 16:27:31',2,2,2022),(38,'2021-09-17 08:20:20','Anna sets out on a journey with an iceman, Kristoff, and his reindeer, Sven, in order to find her sister, Elsa, who has the power to convert any object or person into ice.',0,'frozen','Frozen','2022-06-19 16:27:16',2,1,2022),(39,'2021-09-17 08:20:20','In this animated feature by noted Japanese director Hayao Miyazaki, 10-year-old Chihiro (Rumi Hiiragi) and her parents (Takashi Naitô, Yasuko Sawaguchi) stumble upon a seemingly abandoned amusement park.',0,'spirited-away','Spirited Away','2022-06-19 16:26:57',3,2,2022),(40,'2021-09-17 08:20:20','Carl, an old widower, goes off on an adventure in his flying house in search of Paradise Falls, his wife\'s dream destination.',0,'up','Up','2022-06-19 16:27:15',4,2,2022),(41,'2021-09-17 08:20:20','Forever alone in a crowd, failed comedian Arthur Fleck seeks connection as he walks the streets of Gotham City.',15,'joker','Joker','2022-06-19 16:27:10',1,2,2022),(42,'2021-09-17 08:20:20','The Abbott family must now face the terrors of the outside world as they fight for survival in silence. Forced to venture into the unknown, they realize that the creatures that hunt by sound are not the only threats that lurk beyond the sand path.',15,'a-quiet-place','A Quiet Place','2022-06-19 16:27:00',1,1,2022),(43,'2021-09-17 08:20:20','Nina, a ballerina, gets the chance to play the White Swan, Princess Odette. But she finds herself slipping into madness when Thomas, the artistic director, decides that Lily might fit the role better.',15,'black-swan','Black Swan','2022-06-19 16:27:17',2,1,2022),(44,'2021-09-17 08:20:20','Louis Bloom, a petty thief, realises that he can make money by capturing photographs of criminal activities and starts resorting to extreme tactics to get them.',15,'nightcrawler','Nightcrawler','2022-06-19 16:27:02',2,2,2022),(45,'2021-09-17 08:20:20','Clarice Starling, an FBI agent, seeks help from Hannibal Lecter, a psychopathic serial killer and former psychiatrist, in order to apprehend another serial killer who has been claiming female victims.',15,'the-silence-of-the-lambs','The Silence of The Lambs','2022-06-19 16:27:01',4,1,2022),(46,'2021-09-17 08:20:20','After falling in love with struggling artist Ally, Jackson, a musician, coaxes her to follow her dreams, while he battles with alcoholism and his personal demons.',15,'a-star-is-born','A Star Is Born','2022-06-19 16:26:57',1,2,2022),(47,'2021-09-17 08:20:20','Dean and Cynthia are married with a daughter and their marriage is about to fall apart. Both come from dysfunctional families and struggle to make sense of their relationship.',15,'blue-valentine','Blue Valentine','2022-06-19 16:27:02',2,1,2022),(48,'2021-09-17 08:20:20','Sebastian (Ryan Gosling) and Mia (Emma Stone) are drawn together by their common desire to do what they love. But as success mounts they are faced with decisions that begin...',15,'la-la-land','La La Land','2022-06-19 16:27:29',3,2,2022),(49,'2021-09-17 08:20:20','Duke reads the story of Allie and Noah, two lovers who were separated by fate, to Ms Hamilton, an old woman who suffers from Alzheimer\'s, on a daily basis out of his notebook.',15,'the-notebook','The Notebook','2022-06-19 16:27:30',3,2,2022),(50,'2021-09-17 08:20:20','Seventeen-year-old Rose hails from an aristocratic family and is set to be married. When she boards the Titanic, she meets Jack Dawson, an artist, and falls in love with him.',15,'titanic','Titanic','2022-06-19 16:27:30',4,1,2022);

--
-- Table structure for table `film_genre`
--

DROP TABLE IF EXISTS `film_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film_genre` (
  `film_id` bigint NOT NULL,
  `genre_id` bigint NOT NULL,
  KEY `FKd4b34b812xlb3nxh9b9m021dk` (`genre_id`),
  KEY `FKe3a6pfgbc4cglfjg7216egpig` (`film_id`),
  CONSTRAINT `FKd4b34b812xlb3nxh9b9m021dk` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`),
  CONSTRAINT `FKe3a6pfgbc4cglfjg7216egpig` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_genre`
--

INSERT INTO `film_genre` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,2),(7,2),(8,2),(9,2),(10,2),(11,3),(12,3),(13,3),(14,3),(15,3),(16,4),(17,4),(18,4),(19,4),(20,4),(21,5),(22,5),(23,5),(24,5),(25,5),(26,6),(27,6),(28,6),(29,6),(30,6),(31,7),(32,7),(33,7),(34,7),(35,7),(36,3),(37,3),(38,3),(39,3),(40,3),(41,8),(42,8),(43,8),(44,8),(45,8),(46,9),(47,9),(48,9),(49,9),(50,9);

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` VALUES (1,'Documentaries'),(2,'Comedies'),(3,'Children'),(4,'Crime'),(5,'Feel-good'),(6,'Drama'),(7,'Suspense'),(8,'Thriller'),(9,'Romance');

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `film_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `LIKE_UC_FILM_ID_USER_ID` (`film_id`,`user_id`),
  KEY `FKi2wo4dyk4rok7v4kak8sgkwx0` (`user_id`),
  CONSTRAINT `FKi2wo4dyk4rok7v4kak8sgkwx0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKs32mm0o2p5j82qgu9clyfrag4` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `birth_of_date` varchar(50) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (14,'AAAÁDSADSDASDASDSADS@gmail.com','$2a$10$ZgkmIp58i7vRq.1QydGJEOS1Mli8p7rxwfNeAvWKjngmipZn57X/i','ROLE_ADMIN','hoangkm13','1975-05-12T16:00:00.000Z','FEMALE','0918863388','Hoang','Khuat','1.png'),(15,'n1ckprivat3@gmail.com','$2a$10$qgvVYQlnDnFfxuquMqaYXOITzfxmpYIveRUTp9cO2JPSXIKOMdn/2','ROLE_USER','usertest01',NULL,'MALE',NULL,NULL,NULL,NULL),(16,'hoan','$2a$10$kKvNfgX6pMumYX0IKZXuWefMOykGjGF6MA4wIY.nYvNI9zVjtIw1.','ROLE_USER','tester1234567','13/05/1975','MALE','0918863388',NULL,NULL,'1.png'),(17,'truongquocviet2001@gmail.com','$2a$10$Kmhm5UG2V79nWQmagn2MGOwjLM01d95PwTqFk009ED8ZlWa3m29Ui','ROLE_USER','viet2001','2022-06-23T17:00:00.000Z','MALE','0905054200','Viet','Truong','5.png'),(18,'truongquocviet2003@gmail.com','$2a$10$Ln4aysbLDgKhKQe05Epz2ul7Dp5yzCWlBfGpTiHbOWy8mSgidXRzC','ROLE_USER','viet2003','2022-06-09T17:00:00.000Z','MALE','0905054200','Viet','Truong','2.png'),(19,'lan@gmail.com','$2a$10$OPPBWvm8dw.SsSDNKzavXOYuCeiqcvAaQmUM9yG//ZyuYdh9czZ.S','ROLE_USER','lanngu','13/05/1975','Male','0918863399',NULL,NULL,'1.png'),(20,'','$2a$10$Dl90BvwNIXBBHYpU7lt61OZ5QS/P5iOiVJ/2uaZnQg9vwx5CfTtZa','ROLE_USER','',NULL,' ','','','','3.png');

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `view` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `film_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK52wlyyu8p7lgo3uqv3udc136` (`film_id`),
  KEY `FK37w6bab99jhjeja56i1te4htp` (`user_id`),
  CONSTRAINT `FK37w6bab99jhjeja56i1te4htp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK52wlyyu8p7lgo3uqv3udc136` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

INSERT INTO `view` VALUES (5,'2021-10-19 03:35:14',17,14),(6,'2021-10-19 04:13:46',22,14),(7,'2021-10-19 04:19:11',17,14),(8,'2021-10-19 04:21:39',17,14),(9,'2021-10-19 04:22:28',22,14),(10,'2021-10-19 04:22:49',7,14),(11,'2021-10-19 04:22:57',22,14),(12,'2021-10-19 04:40:39',2,15);
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-24  2:37:06
