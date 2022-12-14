DROP DATABASE IF EXISTS theatre_app;
CREATE DATABASE theatre_app;
use theatre_app;

-- user table
DROP TABLE IF EXISTS users;
create table users(
    email VARCHAR(250),
    password VARCHAR(250),
    userType VARCHAR(250) DEFAULT 'ordinary',
    primary key(email)
);

-- first admin user
-- can be used to create other admin
INSERT INTO users (email,password,userType)
VALUES ('fbcharles747@gmail.com','mypassword','admin');





-- movie table
DROP TABLE IF EXISTS movies;
CREATE TABLE movies(
    id INT AUTO_INCREMENT primary KEY,
    name VARCHAR(250),
    announce_date TIMESTAMP DEFAULT NOW(),
    duration Time
);

-- some sameple movie data
INSERT INTO movies(name,duration)
VALUES("Don't look up",'1:25:0'),
("Molly's game",'2:20');


-- theater table
DROP TABLE IF EXISTS theater;

CREATE TABLE theater(
    id INT AUTO_INCREMENT,
    name VARCHAR(250),
    address VARCHAR(250),
    primary key(id)
);

INSERT INTO theater (name, address)
VALUES ("Cineplex Entertainment","91 Crowfoot Ter Nw, Calgary AB T3G 4J8");
