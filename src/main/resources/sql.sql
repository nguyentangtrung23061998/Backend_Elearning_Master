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
  `userid` bigint(20) NOT NULL,
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
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
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
INSERT INTO `lecture` VALUES (1588322749257,'tense advance','background2.jpg','Bai 1 - Introduction to Microservices, Spring Boot, and Spring Cloud.ts','Bai 1 - Introduction to Microservices, Spring Boot, and Spring Cloud.aac',NULL,NULL,'2020-05-01 08:45:49','2020-05-01 12:47:20',NULL,NULL);
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
INSERT INTO `user` VALUES (1587713344988,'thanh','$2a$10$xd2bzfddOEEdR2YmUtTI5uPMVC3HrRHd.O9gWeSIdNJ9fKdW/lXyG','trungab22c@gmail.com','','Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aGFuaCIsImlhdCI6MTU4ODMzOTkwMSwiZXhwIjoxNTg4NDI2MzAxfQ.ztAZoxZccEGwiOkMNzG7RkzwgFuXh8EkuNI4h8sHOzXXrdW-CNHLGB-6dfhO7orQNvVXWkv00UnYaj-hPscH3A','Trung đẹp trai','2020-04-24 07:29:05','2020-05-01 13:31:43','',''),(1588172402901,'trungdeptrai','$2a$10$5SsyRVsjSWqZ8IOc1hBf9.xzvA2dq9OaytyRVMy125NjMIcxel5Si','trungnen@gmail.com','','Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cnVuZ2RlcHRyYWkiLCJpYXQiOjE1ODgzMjE2MDcsImV4cCI6MTU4ODQwODAwN30.63NLU-BBAdP6MmKJjmETQ5WX8NS3YQMeBnqYHU1DRFyc2TR0sgbpN-ytqvPH4f8bfzTIE7FM_GQ7BK0XmHhwaA','Nguyen Trung','2020-04-29 15:00:03','2020-05-01 08:26:47','','');
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
INSERT INTO `user_role` VALUES (1588172402901,2),(1587713344988,3);
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

-- Dump completed on 2020-05-01 21:46:38
