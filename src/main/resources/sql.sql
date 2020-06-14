-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: elearning
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_Active` bit(1) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1589007336704,'Tense Basic','Course is perfect','english_15.png',_binary '',1590768028203,'2020-05-09 06:55:37','2020-05-30 16:43:09',NULL,NULL),(1589007354669,'Tense Advance','Course is very perfect ','english_14.jpg',_binary '',1590768028203,'2020-05-09 06:55:55','2020-05-30 16:45:58',NULL,NULL),(1589007399525,'Condition bacsic','This is difficult','english12.jpg',_binary '',1590768028203,'2020-05-09 06:56:40','2020-05-30 16:46:35',NULL,NULL),(1590859121340,'Condition Advance','This is difficult','enlisgh_10.png',_binary '',1590851786213,'2020-05-30 17:18:41','2020-05-30 17:21:10',NULL,NULL),(1590859171612,'Compare Basic','This is basic','english_5.jpg',_binary '',1590851786213,'2020-05-30 17:19:32','2020-05-30 17:22:04',NULL,NULL),(1590859181221,'Compare Advance','This is advance','59665294-learn-english-red-vector-learn-english-flat-vector-learn-english-background-learn-english.jpg',_binary '',1590851786213,'2020-05-30 17:19:41','2020-05-30 17:22:26',NULL,NULL),(1590859200771,'Enough grammar','This is basic','english_2.jpg',_binary '',1590851786213,'2020-05-30 17:20:01','2020-05-30 17:22:42',NULL,NULL),(1590859224462,'Enough grammar v2','This is nomarl','enlish7.jpg',_binary '',1590851786213,'2020-05-30 17:20:24','2020-05-31 07:53:31',NULL,NULL),(1592063113295,'English Easy','English is easy',NULL,_binary '',1590768028203,'2020-06-13 15:45:13','2020-06-13 15:53:41',NULL,NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examcourse`
--

DROP TABLE IF EXISTS `examcourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `examcourse` (
  `id` bigint(20) NOT NULL,
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `answerfirst` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `answersecond` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `answerthird` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `answerfourth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `correctanswer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  `courseid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `exam_course_fk` (`courseid`),
  CONSTRAINT `exam_course_fk` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examcourse`
--

LOCK TABLES `examcourse` WRITE;
/*!40000 ALTER TABLE `examcourse` DISABLE KEYS */;
INSERT INTO `examcourse` VALUES (1589008233706,'How do you think your course? ','Answer 1','Answer 2','Answer 3','Answer 4','Answer 2','2020-05-09 07:10:34','2020-05-09 07:16:51',NULL,NULL,1589007336704),(1589009379952,'Question 3','Answer 2','Answer 3','Answer 31','Answer 24','Answer 2','2020-05-09 07:29:40',NULL,NULL,NULL,1589007336704),(1589009872239,'Question 4','Answer 2','Answer 3','Answer 31','Answer 24','Answer 2','2020-05-09 07:37:52',NULL,NULL,NULL,1589007336704),(1589009912708,'Question 5','Answer 2','Answer 3','Answer 31','Answer 24','Answer 2','2020-05-09 07:38:33',NULL,NULL,NULL,1589007336704),(1589009983701,'Question 3','Answer 2','Answer 3','Answer 31','Answer 24','Answer 2','2020-05-09 07:39:44',NULL,NULL,NULL,1589007354669),(1589010418388,'Question 4','Answer 2','Answer 3','Answer 31','Answer 24','Answer 2','2020-05-09 07:46:58',NULL,NULL,NULL,1589007354669);
/*!40000 ALTER TABLE `examcourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grammar_comment`
--

DROP TABLE IF EXISTS `grammar_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `grammar_comment` (
  `id` bigint(20) NOT NULL,
  `commentname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `grammar_list_content_id` bigint(20) NOT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `grammar_list_content_id` (`grammar_list_content_id`),
  CONSTRAINT `grammar_comment_ibfk_1` FOREIGN KEY (`grammar_list_content_id`) REFERENCES `grammar_list_content` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grammar_comment`
--

LOCK TABLES `grammar_comment` WRITE;
/*!40000 ALTER TABLE `grammar_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `grammar_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grammar_content`
--

DROP TABLE IF EXISTS `grammar_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `grammar_content` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grammar_content`
--

LOCK TABLES `grammar_content` WRITE;
/*!40000 ALTER TABLE `grammar_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `grammar_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grammar_exam`
--

DROP TABLE IF EXISTS `grammar_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `grammar_exam` (
  `id` bigint(20) NOT NULL,
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `answerfirst` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `answersecond` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `answerthird` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `answerfourth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `correctanswer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grammar_list_id` bigint(20) NOT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `grammar_list_id` (`grammar_list_id`),
  CONSTRAINT `grammar_exam_ibfk_1` FOREIGN KEY (`grammar_list_id`) REFERENCES `grammar_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grammar_exam`
--

LOCK TABLES `grammar_exam` WRITE;
/*!40000 ALTER TABLE `grammar_exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `grammar_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grammar_list`
--

DROP TABLE IF EXISTS `grammar_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `grammar_list` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grammar_list`
--

LOCK TABLES `grammar_list` WRITE;
/*!40000 ALTER TABLE `grammar_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `grammar_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grammar_list_content`
--

DROP TABLE IF EXISTS `grammar_list_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `grammar_list_content` (
  `id` bigint(20) NOT NULL,
  `grammar_content_id` bigint(20) NOT NULL,
  `grammar_list_id` bigint(20) NOT NULL,
  `diem` int(11) DEFAULT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `grammar_content_id` (`grammar_content_id`),
  KEY `grammar_list_id` (`grammar_list_id`),
  CONSTRAINT `grammar_list_content_ibfk_1` FOREIGN KEY (`grammar_content_id`) REFERENCES `grammar_content` (`id`),
  CONSTRAINT `grammar_list_content_ibfk_2` FOREIGN KEY (`grammar_list_id`) REFERENCES `grammar_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grammar_list_content`
--

LOCK TABLES `grammar_list_content` WRITE;
/*!40000 ALTER TABLE `grammar_list_content` DISABLE KEYS */;
/*!40000 ALTER TABLE `grammar_list_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lecture` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `audio` varchar(255) DEFAULT NULL,
  `document` varchar(255) DEFAULT NULL,
  `courseid` bigint(20) DEFAULT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_LECTURE_COURSE` (`courseid`),
  CONSTRAINT `FK_LECTURE_COURSE` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES (1589007336704,'English advance','simple-present.jpg','bai1.mp4',NULL,NULL,1589007336704,NULL,'2020-06-13 16:07:24',NULL,NULL),(1589007336705,'Present Advance',NULL,'present_bai2.mp4',NULL,NULL,1589007336704,NULL,'2020-05-28 11:14:46',NULL,NULL),(1589007336706,'Past Basic',NULL,'Present Tense - Learn English grammar through conversations.mp4',NULL,NULL,1589007336704,NULL,'2020-05-29 15:21:44',NULL,NULL),(1589007336707,'Past Advance',NULL,'past_advance.mp4',NULL,NULL,1589007354669,NULL,'2020-05-29 16:05:27',NULL,NULL),(1589007336708,'Fureture Basic',NULL,'Grammar Andy- Future Simple.mp4',NULL,NULL,1589007354669,NULL,'2020-05-29 16:10:38',NULL,NULL),(1589007336709,'Fureture Advance',NULL,'Grammar Andy- Future Simple.mp4',NULL,NULL,1589007336704,NULL,'2020-05-29 16:13:04',NULL,NULL),(1592068099890,'English advance v2',NULL,NULL,NULL,NULL,1592063113295,'2020-06-13 17:08:20',NULL,NULL,NULL);
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `rolename` varchar(255) NOT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_epk9im9l9q67xmwi4hbed25do` (`rolename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN',NULL,NULL,NULL,NULL),(2,'ROLE_STUDENT',NULL,NULL,NULL,NULL),(3,'ROLE_TEACHER',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course_enrolement`
--

DROP TABLE IF EXISTS `student_course_enrolement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student_course_enrolement` (
  `id` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `courseid` bigint(20) NOT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `courseid` (`courseid`),
  CONSTRAINT `student_course_enrolement_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `student_course_enrolement_ibfk_2` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course_enrolement`
--

LOCK TABLES `student_course_enrolement` WRITE;
/*!40000 ALTER TABLE `student_course_enrolement` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_course_enrolement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `createddate` timestamp NULL DEFAULT NULL,
  `modifieddate` timestamp NULL DEFAULT NULL,
  `createdby` varchar(100) DEFAULT NULL,
  `modifiedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1590768028203,'admin','$2a$10$ca1BxF6YDDGz.DJ21jOPCO7T2yfe1IVpazM3gpbqCJzcNYCobuwoq','admin@gmail.com','','Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MjEyNDE5NiwiZXhwIjoxNTkyMTQyMTk2fQ.R1sJ0qaN1IJnehEBbJuDQVSJFlbD2XJ8BtpFsEpKwI3fUJm66En6Rq1v5b1rV6olTUlJReZ1WvB0DRuWftnauA','Nguyen Trung','2020-05-29 16:00:28','2020-06-14 08:43:16','',''),(1590851786213,'trungdeptrai','$2a$10$/8thMisvIBarc5bU0q6DsemxXt1qkTOQoYQ1KHb2dYUfM8cR39gMe','ntt@gmail.com','https://firebasestorage.googleapis.com/v0/b/app-elearning-english.appspot.com/o/images%2F25de2ea8-47cb-478e-8260-caafd744f863.jpg?alt=media&token=17b75654-4431-48e0-ba72-04de994ea113','Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cnVuZ2RlcHRyYWkiLCJpYXQiOjE1OTIxMjQxNjYsImV4cCI6MTU5MjE0MjE2Nn0.mbeqid31TWb-PidRfezZkrS_JjPv2B8L3MZx5y8GRJvDnHPkGlWUuITiVmDx64gGqvUZ5e6ruT2-2u_E_z772Q','Nguyen Tang Trung','2020-05-30 15:16:26','2020-06-14 08:42:46','','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `userid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  PRIMARY KEY (`userid`,`roleid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1590768028203,1),(1590851786213,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'elearning'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-14 16:53:37
