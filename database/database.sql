CREATE DATABASE QuickBank;
USE QuickBank;


CREATE TABLE roles(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(15),
    status VARCHAR(10),
	PRIMARY KEY (`id`)
);

CREATE TABLE bank(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30),
    address VARCHAR(40),
    phone VARCHAR(15),
    description VARCHAR(40),
    bank_branch VARCHAR(30) NOT NULL,
    bank_section VARCHAR(30) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE user
(
	user_id INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    username VARCHAR(10) NOT NULL,
    gender VARCHAR(6) ,
    bank_branch VARCHAR(30) ,
    bank_section VARCHAR(30) ,
    birth_date VARCHAR(11),
    password VARCHAR(50) NOT NULL,
    role_id INT,
    bank_id INT,
    created_date DATE NOT NULL,
	PRIMARY KEY (`user_id`),
    FOREIGN KEY(role_id) REFERENCES roles(id),
    FOREIGN KEY(bank_id) REFERENCES bank(id)
);


CREATE TABLE card(
	id INT NOT NULL AUTO_INCREMENT,
    card_number VARCHAR(20),
    duedate DATE NOT NULL,
    client_id INT NOT NULL,
    card_type INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE clients(
	id INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    birth_date DATE NOT NULL,
    passport VARCHAR(30),
    address VARCHAR(40) NOT NULL,
    card_id INT,
    PRIMARY KEY (`id`),
    FOREIGN KEY(card_id) REFERENCES card(id)
);

CREATE TABLE cardtype(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(10),
    PRIMARY KEY (`id`)	
);

CREATE TABLE cardstatus(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(10),
    PRIMARY KEY (`id`)	
);

CREATE TABLE transactions(
	id INT NOT NULL AUTO_INCREMENT,
    card_id INT NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    summary DECIMAL,
	FOREIGN KEY(card_id) REFERENCES card(id),
    PRIMARY KEY (`id`)
);

INSERT INTO roles(name, status)
			VALUES('manager','1');
            
INSERT INTO bank(name, address, phone, description, bank_branch, bank_section)
			VALUES('QuickBank','Tashkent','1234567','tez xizmat ','Tashkent','Processing');

INSERT INTO user( firstname, lastname, username, gender, bank_branch, bank_section, birth_date, password, role_id, bank_id, created_date)
				VALUES('john','cameron','johnny','male','Tashkent','Processing','2000-12-03','1234',1,1,'2022-03-17');



                
SELECT * FROM user;

SELECT * FROM bank;

