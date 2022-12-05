
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
    rowNum INT,
    colNum INT,
    occupied BOOLEAN DEFAULT false,
    FOREIGN KEY(room_id) REFERENCES rooms(id)
    ON DELETE CASCADE
);


-- reservation is for registered user only
DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations(
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_email VARCHAR(250),
    mov_id INT,
    theater_id INT,
    room_id INT,
    room_Number INT,
    rowNum INT,
    colNum INT,
    startTime TIMESTAMP,
    FOREIGN KEY(mov_id) REFERENCES movies(id) ON DELETE CASCADE,
    FOREIGN KEY(theater_id) REFERENCES theater(id) ON DELETE CASCADE
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

DROP TABLE IF EXISTS paymentInfo;

CREATE TABLE paymentInfo(
    user_email VARCHAR(250),
    card_holder VARCHAR(250),
    card_number VARCHAR(250),
    cvv INT,
    FOREIGN KEY(user_email) REFERENCES users(email) ON DELETE CASCADE,
    PRIMARY KEY(user_email,card_number)
);









