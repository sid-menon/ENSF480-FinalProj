
DROP TABLE IF EXISTS rooms;
CREATE TABLE rooms(
    id INT AUTO_INCREMENT primary key,
    theater_id INT,
    room_Number INT,
    next_Available_Time TIMESTAMP DEFAULT NOW(),
    FOREIGN key(theater_id) REFERENCES theater(id)
    ON DELETE CASCADE

);

DROP TABLE IF EXISTS seats;

CREATE TABLE seats(
    room_id INT NOT NULL,
    rowChar CHAR(1),
    col INT,
    occupied BOOLEAN DEFAULT false,
    FOREIGN KEY(room_id) REFERENCES rooms(id)
    ON DELETE CASCADE
);


-- reservation is for registered user only
DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations(
    customer_email VARCHAR(250),
    theater_id INT,
    room_Number INT,
    rowChar CHAR(1),
    col INT,
    startTime TIMESTAMP,
    endTime TIMESTAMP
);


-- shows will be created by admin user in application
DROP TABLE IF EXISTS shows;
CREATE TABLE shows(
    mov_id INT,
    theater_id INT,
    room_id INT,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    FOREIGN KEY(mov_id) REFERENCES movies(id),
    FOREIGN KEY(theater_id) REFERENCES theater(id),
    FOREIGN KEY(room_id) REFERENCES rooms(id),
    primary key(mov_id,theater_id,start_time,end_time)
);

/*
information of the theater managed by admin user will be extracted
when admin user log in

*/ 
DROP TABLE IF EXISTS manages;
CREATE TABLE manages(
    mgr_email VARCHAR(250) NOT NULL,
    theater_id INT NOT NULL,
    FOREIGN KEY(mgr_email) REFERENCES users(email),
    FOREIGN KEY (theater_id) REFERENCES theater(id)
);

INSERT INTO manages(mgr_email,theater_id)
VALUES('fbcharles747@gmail.com',1);







