DROP DATABASE IF EXISTS course ;

CREATE DATABASE IF NOT EXISTS course;

USE course;

CREATE TABLE student_information (
id INT(250) PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(40),
last_name VARCHAR(40),
birth_date  DATE,
gender  BOOLEAN ,
TC_address VARCHAR(13),
phone_number VARCHAR(15),
email_address VARCHAR(50),
home_address VARCHAR(100)
);

