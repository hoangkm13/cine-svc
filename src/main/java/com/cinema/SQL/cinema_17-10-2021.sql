-- MySQL dump 10.13  Distrib 8.0.26, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.26

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

--
-- Table structure for table `film`
--

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

INSERT INTO `film` VALUES (1,'2021-09-17 08:20:20','An exploration of big cat breeding and its bizarre underworld, populated by eccentric characters.',18,'tiger-king','Tiger King','2021-09-17 08:20:20'),(2,'2021-09-17 08:20:20','Amanda Marie Knox is an American woman who spent almost four years in an Italian prison.',12,'amanda-knox','Amanda Knox','2021-09-17 08:20:20'),(3,'2021-09-17 08:20:20','Citizenfour is a 2014 documentary film directed by Laura Poitras, concerning Edward Snowden and the NSA spying scandal.',12,'citizenfour','Citizenfour','2021-09-17 08:20:20'),(4,'2021-09-17 08:20:20','Director Morgan Spurlock\'s social experiment in fast-food gastronomy sees him attempting to subsist uniquely on food from the McDonalds',12,'super-size-me','Super Size Me','2021-09-17 08:20:20'),(5,'2021-09-17 08:20:20','Filmmaker James Marsh masterfully recreates high-wire daredevil Philippe Petit\'s 1974 stunt walking on a wire across the Twin Towers.',12,'man-on-wire','Man on Wire','2021-09-17 08:20:20'),(6,'2021-09-17 08:20:20','A motley group of office workers go through hilarious misadventures at the Scranton, Pennsylvania, branch of the Dunder Mifflin Paper Company.',15,'the-office','The Office','2021-09-17 08:20:20'),(7,'2021-09-17 08:20:20','The Bluth family, once a prominent name in the business, loses everything after the head patriarch gets convicted for fraud.',15,'arrested-development','Arrested Development','2021-09-17 08:20:20'),(8,'2021-09-17 08:20:20','Larry David, a famous television writer and producer, gets into various misadventures with his friends and celebrity colleagues in Los Angeles.',15,'curb-your-enthusiasm','Curb Your Enthusiasm','2021-09-17 08:20:20'),(9,'2021-09-17 08:20:20','Peter Griffin and his family of two teenagers, a smart dog, a devilish baby and his wife find themselves in some of the most hilarious scenarios.',15,'family-guy','Family Guy','2021-09-17 08:20:20'),(10,'2021-09-17 08:20:20','Four young, schoolgoing boys, Stan Marsh, Kyle Broflovski, Eric Cartman and Kenny McCormick, who live in South Park set out on various adventures.',15,'south-park','South Park','2021-09-17 08:20:20'),(11,'2021-09-17 08:20:20','Peppa, an outgoing preschool pig, participates in many energetic activities. She learns something new every day and has a lot of fun with her family and friends.',0,'peppa-pig','Peppa Pig','2021-09-17 08:20:20'),(12,'2021-09-17 08:20:20','Dora, a seven-year-old girl of Latin American descent, embarks upon numerous adventures in the wilderness with her friend Boots, a monkey, and a variety of fun and useful tools.',0,'dora-the-explorer','Dora The Explorer','2021-09-17 08:20:20'),(13,'2021-09-17 08:20:20','Six brave puppies, captained by a tech-savvy ten-year-old boy, Ryder, work together to accomplish high-stakes rescue missions to safeguard the residents of the Adventure Bay community.',0,'paw-patrol','PAW Patrol','2021-09-17 08:20:20'),(14,'2021-09-17 08:20:20','Bespectacled aardvark Arthur Read demonstrates to kids how to deal with such childhood traumas and challenges as homework, teachers and bullies.',0,'arthur','Arthur','2021-09-17 08:20:20'),(15,'2021-09-17 08:20:20','A yellow sea sponge named SpongeBob SquarePants lives in the city of Bikini Bottom deep in the Pacific Ocean.',0,'spongebob','SpongeBob','2021-09-17 08:20:20'),(16,'2021-09-17 08:20:20','Exonerated after spending nearly two decades in prison for a crime he did not commit, Steven Avery filed suit against Manitowoc County, Wis., and several individuals involved with his arrest.',18,'making-a-murderer','Making a Murderer','2021-09-17 08:20:20'),(17,'2021-09-17 08:20:20','An innocent man is accused of murder, leading his attorney on a wild chase to confirm his alibi using raw footage from a television show.',18,'long-shot','Long Shot','2021-09-17 08:20:20'),(18,'2021-09-17 08:20:20','Henry Lee Lucas was an American convicted serial killer whose crimes spanned from 1960 to 1983. He was convicted of murdering eleven people and condemned to death for the murder of Debra Jackson, although his sentence would be commuted to life in prison in 1998.',18,'the-confession-killer','The Confession Killer','2021-09-17 08:20:20'),(19,'2021-09-17 08:20:20','Henry Lee Lucas was an American convicted serial killer whose crimes spanned from 1960 to 1983. He was convicted of murdering eleven people and condemned to death for the murder of Debra Jackson.',18,'the-innocent-man','The Innocent Man','2021-09-17 08:20:20'),(20,'2021-09-17 08:20:20','In 2001 novelist Michael Peterson\'s wife died, and he claimed she perished after falling down stairs at their home. The medical examiner, however, determined that she had been beaten with a weapon',18,'the-staircase','The Staircase','2021-09-17 08:20:20'),(21,'2021-09-17 08:20:20','Will Hunting, a genius in mathematics, solves all the difficult mathematical problems. When he faces an emotional crisis, he takes help from psychiatrist Dr Sean Maguireto, who helps him recover.',12,'good-will-hunting','Good Will Hunting','2021-09-17 08:20:20'),(22,'2021-09-17 08:20:20','Forrest Gump, a man with a low IQ, joins the army for service where he meets Dan and Bubba. However, he cannot stop thinking about his childhood sweetheart Jenny Curran, whose life is messed up.',12,'forrest-gump','Forrest Gump','2021-09-17 08:20:20'),(23,'2021-09-17 08:20:20','Social misfit Juno protects herself with a caustic wit, but her unplanned pregnancy has the teen getting more involved in the lives of her baby\'s adoptive parents than she expected.',12,'juno','Juno','2021-09-17 08:20:20'),(24,'2021-09-17 08:20:20','Gil arrives with his fiancee and her family in Paris for a vacation, even as he tries to finish his debut novel. He is beguiled by the city, which takes him to a time past, away from his fiancee.',12,'midnight-in-paris','Midnight In Paris','2021-09-17 08:20:20'),(25,'2021-09-17 08:20:20','Dewey Finn, an amateur rock enthusiast, slyly takes up his friend\'s substitute teacher\'s job. Bearing no qualifications for it, he instead starts training the students to form a band.',12,'school-of-rock','School of Rock','2021-09-17 08:20:20'),(26,'2021-09-17 08:20:20','Two friends and fellow magicians become bitter enemies after a sudden tragedy. As they devote themselves to this rivalry, they make sacrifices that bring them fame but with terrible consequences.',15,'the-prestige','The Prestige','2021-09-17 08:20:20'),(27,'2021-09-17 08:20:20','Discontented with his capitalistic lifestyle, a white-collared insomniac forms an underground fight club with Tyler, a careless soap salesman. The project soon spirals down into something sinister.',15,'fight-club','Fight Club','2021-09-17 08:20:20'),(28,'2021-09-17 08:20:20','King George VI tries to overcome his stammering problem with the help of speech therapist Lionel Logue and makes himself worthy enough to lead his country through World War II.',15,'kings-speech','Kings Speech','2021-09-17 08:20:20'),(29,'2021-09-17 08:20:20','Hugh Glass, a legendary frontiersman, is severely injured in a bear attack and is abandoned by his hunting crew. He uses his skills to survive and take revenge on his companion, who betrayed him.',15,'the-revenant','The Revenant','2021-09-17 08:20:20'),(30,'2021-09-17 08:20:20','Mark Zuckerberg creates a social networking site, Facebook, with the help of his friend Eduardo Saverin. But soon, a string of lies tears their relationship apart even as Facebook connects people.',12,'the-social-network','The Social Network','2021-09-17 08:20:20'),(31,'2021-09-17 08:20:20','Teddy Daniels and Chuck Aule, two US marshals, are sent to an asylum on a remote island in order to investigate the disappearance of a patient, where Teddy uncovers a shocking truth about the place.',15,'shutter-island','Shutter Island','2021-09-17 08:20:20'),(32,'2021-09-17 08:20:20','Nick Dunne discovers that the entire media focus has shifted on him when his wife Amy Dunne disappears on the day of their fifth wedding anniversary.',15,'gone-girl','Gone Girl','2021-09-17 08:20:20'),(33,'2021-09-17 08:20:20','When the police take time to find Keller Dover\'s daughter and her friend, he decides to go on a search himself. His desperation leads him closer to finding the truth and also jeopardises his own life.',15,'prisoners','Prisoners','2021-09-17 08:20:20'),(34,'2021-09-17 08:20:20','A serial killer begins murdering people according to the seven deadly sins. Two detectives, one new to the city and the other about to retire, are tasked with apprehending the criminal.',15,'seven','Seven','2021-09-17 08:20:20'),(35,'2021-09-17 08:20:20','Robert Graysmith, a cartoonist by profession, finds himself obsessively thinking about the Zodiac killer. He uses his puzzle-solving abilities to get closer to revealing the identity of the killer.',15,'zodiac','Zodiac','2021-09-17 08:20:20'),(36,'2021-09-17 08:20:20','Dracula, who owns a high-end resort for monsters, attempts to keep his daughter from falling in love with Jonathan, a human.',0,'hotel-transylvania','Hotel Transylvania','2021-09-17 08:20:20'),(37,'2021-09-17 08:20:20','Gru, a criminal mastermind, adopts three orphans as pawns to carry out the biggest heist in history. His life takes an unexpected turn when the little girls see him as their potential father.',0,'despicable-me','Despicable Me','2021-09-17 08:20:20'),(38,'2021-09-17 08:20:20','Anna sets out on a journey with an iceman, Kristoff, and his reindeer, Sven, in order to find her sister, Elsa, who has the power to convert any object or person into ice.',0,'frozen','Frozen','2021-09-17 08:20:20'),(39,'2021-09-17 08:20:20','In this animated feature by noted Japanese director Hayao Miyazaki, 10-year-old Chihiro (Rumi Hiiragi) and her parents (Takashi Naitô, Yasuko Sawaguchi) stumble upon a seemingly abandoned amusement park.',0,'spirited-away','Spirited Away','2021-09-17 08:20:20'),(40,'2021-09-17 08:20:20','Carl, an old widower, goes off on an adventure in his flying house in search of Paradise Falls, his wife\'s dream destination.',0,'up','Up','2021-09-17 08:20:20'),(41,'2021-09-17 08:20:20','Forever alone in a crowd, failed comedian Arthur Fleck seeks connection as he walks the streets of Gotham City.',15,'joker','Joker','2021-09-17 08:20:20'),(42,'2021-09-17 08:20:20','The Abbott family must now face the terrors of the outside world as they fight for survival in silence. Forced to venture into the unknown, they realize that the creatures that hunt by sound are not the only threats that lurk beyond the sand path.',15,'a-quiet-place','A Quiet Place','2021-09-17 08:20:20'),(43,'2021-09-17 08:20:20','Nina, a ballerina, gets the chance to play the White Swan, Princess Odette. But she finds herself slipping into madness when Thomas, the artistic director, decides that Lily might fit the role better.',15,'black-swan','Black Swan','2021-09-17 08:20:20'),(44,'2021-09-17 08:20:20','Louis Bloom, a petty thief, realises that he can make money by capturing photographs of criminal activities and starts resorting to extreme tactics to get them.',15,'nightcrawler','Nightcrawler','2021-09-17 08:20:20'),(45,'2021-09-17 08:20:20','Clarice Starling, an FBI agent, seeks help from Hannibal Lecter, a psychopathic serial killer and former psychiatrist, in order to apprehend another serial killer who has been claiming female victims.',15,'the-silence-of-the-lambs','The Silence of The Lambs','2021-09-17 08:20:20'),(46,'2021-09-17 08:20:20','After falling in love with struggling artist Ally, Jackson, a musician, coaxes her to follow her dreams, while he battles with alcoholism and his personal demons.',15,'a-star-is-born','A Star Is Born','2021-09-17 08:20:20'),(47,'2021-09-17 08:20:20','Dean and Cynthia are married with a daughter and their marriage is about to fall apart. Both come from dysfunctional families and struggle to make sense of their relationship.',15,'blue-valentine','Blue Valentine','2021-09-17 08:20:20'),(48,'2021-09-17 08:20:20','Sebastian (Ryan Gosling) and Mia (Emma Stone) are drawn together by their common desire to do what they love. But as success mounts they are faced with decisions that begin...',15,'la-la-land','La La Land','2021-09-17 08:20:20'),(49,'2021-09-17 08:20:20','Duke reads the story of Allie and Noah, two lovers who were separated by fate, to Ms Hamilton, an old woman who suffers from Alzheimer\'s, on a daily basis out of his notebook.',15,'the-notebook','The Notebook','2021-09-17 08:20:20'),(50,'2021-09-17 08:20:20','Seventeen-year-old Rose hails from an aristocratic family and is set to be married. When she boards the Titanic, she meets Jack Dawson, an artist, and falls in love with him.',15,'titanic','Titanic','2021-09-17 08:20:20');

--
-- Table structure for table `film_genre`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film_genre` (
  `film_id` bigint NOT NULL,
  `genre_id` bigint NOT NULL,
  KEY `FKd4b34b812xlb3nxh9b9m021dk` (`genre_id`),
  KEY `FKe3a6pfgbc4cglfjg7216egpig` (`film_id`),
  CONSTRAINT `FKd4b34b812xlb3nxh9b9m021dk` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`),
  CONSTRAINT `FKe3a6pfgbc4cglfjg7216egpig` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_genre`
--

INSERT INTO `film_genre` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,2),(7,2),(8,2),(9,2),(10,2),(11,3),(12,3),(13,3),(14,3),(15,3),(16,4),(17,4),(18,4),(19,4),(20,4),(21,5),(22,5),(23,5),(24,5),(25,5),(26,6),(27,6),(28,6),(29,6),(30,6),(31,7),(32,7),(33,7),(34,7),(35,7),(36,3),(37,3),(38,3),(39,3),(40,3),(41,8),(42,8),(43,8),(44,8),(45,8),(46,9),(47,9),(48,9),(49,9),(50,9);

--
-- Table structure for table `genre`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` VALUES (1,'documentaries'),(2,'comedies'),(3,'children'),(4,'crime'),(5,'feel-good'),(6,'drama'),(7,'suspense'),(8,'thriller'),(9,'romance');

--
-- Table structure for table `user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (1,'nttung@gmail.com','$2a$10$jhaGyhYVKuJkdHvV1nWEnONRcmTGi4lXt4f7kVaq4CgnWA2Fg2NrS','ROLE_ADMIN','nttung');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-17 15:38:35
