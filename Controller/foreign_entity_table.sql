


DROP TABLE IF EXISTS seats;

CREATE TABLE seats(
    theater_id INT NOT NULL,
    rowChar CHAR(1),
    col INT,
    roomNo INT,
    occupied BOOLEAN DEFAULT false,
    FOREIGN KEY(theater_id) REFERENCES theater(id)
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
    the_id INT,
    show_time TIMESTAMP,
    FOREIGN KEY(mov_id) REFERENCES movies(id),
    FOREIGN KEY(the_id) REFERENCES theater(id)
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







