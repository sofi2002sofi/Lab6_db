create database if not exists lab_5_db;
use lab_5_db;

DROP TABLE IF EXISTS test;
DROP TABLE IF EXISTS module;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS course_info;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS security;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS gender;
DROP TABLE IF EXISTS answer;

CREATE TABLE answer (
	id INT NOT NULL AUTO_INCREMENT,
    answer_name LONGTEXT NOT NULL,
    PRIMARY KEY (id),
	UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE
) ENGINE = INNODB;

CREATE TABLE course (
	id INT NOT NULL AUTO_INCREMENT,
	course_name VARCHAR(50) NOT NULL,
	duration_in_hours FLOAT NOT NULL,
	PRIMARY KEY (id),
	UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
	UNIQUE INDEX course_name_UNIQUE (course_name ASC) VISIBLE
) ENGINE = InnoDB;

CREATE TABLE gender (
	id INT NOT NULL AUTO_INCREMENT,
	gender_name VARCHAR(6) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE
) ENGINE = InnoDB;

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(50) NOT NULL,
    forums_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    birth_year INT NULL DEFAULT NULL,
    gender_id INT NULL DEFAULT NULL,
    PRIMARY KEY (id),
	UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
    UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE,
    INDEX fk_user_gender1_idx (gender_id ASC) VISIBLE
) ENGINE = InnoDB;

CREATE TABLE security (
	id INT NOT NULL AUTO_INCREMENT,
	password VARCHAR(8) NOT NULL,
    user_id INT NOT NULL UNIQUE,
	PRIMARY KEY (id),
	UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
	UNIQUE INDEX fk_password_user1_idx (user_id ASC) VISIBLE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS course_info (
	  id INT NOT NULL AUTO_INCREMENT,
	  start_date VARCHAR(45) NOT NULL,
	  completion_in_percents DECIMAL(4,2) NOT NULL,
	  available_message TINYINT NOT NULL,
	  course_id INT NOT NULL,
	  user_id INT NOT NULL,
	  PRIMARY KEY (id),
	  INDEX fk_course_info_course_idx (course_id ASC) VISIBLE,
	  INDEX fk_course_info_user1_idx (user_id ASC) VISIBLE
) ENGINE = InnoDB;

CREATE TABLE message (
	  id INT NOT NULL AUTO_INCREMENT,
	  message_content LONGTEXT NOT NULL,
	  count_of_overdued_tests INT NOT NULL,
	  count_of_overdued_modules INT NOT NULL,
	  course_info_id INT NOT NULL,
	  PRIMARY KEY (id),
	  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
	  INDEX fk_message_course_info1_idx (course_info_id ASC) VISIBLE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS module (
	  id INT NOT NULL AUTO_INCREMENT,
	  module_name VARCHAR(45) NOT NULL,
	  deadline_datetime VARCHAR(45) NOT NULL,
	  completed TINYINT NOT NULL,
	  overdue TINYINT NOT NULL,
	  course_info_id INT NOT NULL,
	  PRIMARY KEY (id),
	  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
	  INDEX fk_module_course_info1_idx (course_info_id ASC) VISIBLE
) ENGINE = InnoDB;

CREATE TABLE test (
	  id INT NOT NULL AUTO_INCREMENT,
	  test_name LONGTEXT NOT NULL,
	  deadline_datetime VARCHAR(45) NOT NULL,
	  completed TINYINT NOT NULL,
	  overdue TINYINT NOT NULL,
	  course_info_id INT NOT NULL,
	  PRIMARY KEY (id),
	  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
	  INDEX fk_test_course_info1_idx (course_info_id ASC) VISIBLE
) ENGINE = InnoDB;

ALTER TABLE user
ADD CONSTRAINT fk_user_gender1
    FOREIGN KEY (gender_id)
    REFERENCES gender (id);

ALTER TABLE security
ADD CONSTRAINT fk_password_user1
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE course_info
ADD CONSTRAINT fk_course_info_course
    FOREIGN KEY (course_id)
    REFERENCES course (id),
ADD CONSTRAINT fk_course_info_user1
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE message
ADD CONSTRAINT fk_message_course_info1
    FOREIGN KEY (course_info_id)
    REFERENCES course_info (id);

ALTER TABLE module
ADD CONSTRAINT fk_module_course_info1
    FOREIGN KEY (course_info_id)
    REFERENCES course_info (id);

ALTER TABLE test
ADD CONSTRAINT fk_test_course_info1
    FOREIGN KEY (course_info_id)
    REFERENCES course_info (id);

INSERT INTO gender (gender_name) VALUES
	 ('MALE'),
     ('FEMALE');

INSERT INTO user (full_name, forums_name, email, birth_year, gender_id) VALUES
	 ('Sofiia Didula', 'sofiii',	'so.d@gmail.com', 2002,	2),
     ('Volodymyr Kasaraba', 'humbleman_mo', 'volod@gmail.com', 2001, 1),
     ('Taras Leshchyshyn', 'lyndexter', 'tes@gmail.com',	2002, 1),
     ('Rostyslav Oryshchak', 'bigWave', 'rost@gmail.com', 2002, 1),
     ('Andriiy Peleno', 'andriiPeleno', 'andr@gmail.com', 2001, 1),
     ('Maxym Zvarych', 'megatashcher', 'megatashcher@gmail.com', 2002, 1),
     ('Dmytro Buchkovsky', 'dimaster', 'dima@gmail.com', null, 1),
     ('Marko Topolevsky', 'marko', 'marko@gmail.com', null, 1),
     ('Nadiia Zakcharchyn', 'nadiika', 'nadia@gmail.com', 2002, 2),
     ('Natalia Kost', 'nklighted', 'natalia@gmail.com',	2002, 2);

INSERT INTO security (password, user_id) VALUES
	 ('12345678', 5),
     ('09876543', 4),
     ('r7h8j3m9', 8),
     ('yenotyk', 7),
     ('edcrfvji', 1),
     ('plkywnca', 2),
     ('maleijn', 9),
     ('werr', 3),
     ('really', 6),
     ('wxwxwxwx', 10);

INSERT INTO course (course_name, duration_in_hours) VALUES
	 ('English for beginners', 20),
     ('English for business', 14),
     ('Java for beginners', 22),
     ('Python for beginners', 18),
     ('C# for beginners', 25),
     ('C++ for beginners', 21),
     ('HTML/CSS for beginners', 4),
     ('JavaScript for beginners', 10),
     ('Numeric methods', 8),
     ('Solfeggio', 19);

INSERT INTO course_info (start_date, completion_in_percents, available_message, course_id, user_id) VALUES
	 ('2020-10-15', 34.65, 0, 1, 1),
	 ('2020-10-14', 64.00, 1, 3, 9),
	 ('2020-10-01', 30.50, 0, 2, 5),
	 ('2020-09-29', 39.65, 1, 8, 4),
	 ('2020-09-15', 40.00, 1, 8, 8),
	 ('2020-10-05', 87.00, 0, 2, 7),
	 ('2020-10-24', 10.75, 1, 2, 3),
	 ('2020-10-22', 55.55, 0, 3, 2),
	 ('2020-10-11', 13.15, 1, 1, 6),
	 ('2020-09-27', 75.95, 1, 1, 10),
     ('2020-09-26', 95.25, 0, 10, 10);

INSERT INTO message (message_content, count_of_overdued_tests, count_of_overdued_modules, course_info_id) VALUES
	 ('You overdued Java for beginners course', 0, 1, 2),
     ('You overdued JavaScript for beginners course', 1, 0, 4),
	 ('You overdued JavaScript for beginners course', 1, 0, 5),
     ('You overdued English for business course', 1, 0, 7),
     ('You overdued English for beginners course', 0, 2, 9),
     ('You overdued English for beginners course', 0, 1, 10);

INSERT INTO module (module_name, deadline_datetime, completed, overdue, course_info_id) VALUES
	 ('Past Simple', '2020-10-15 00:00:00', 1, 0, 1),
     ('Past Simple', '2020-10-18 00:00:00', 1, 0, 3),
     ('Past Simple', '2020-10-14 00:00:00', 1, 0, 6),
	 ('Past Simple', '2020-10-16 00:00:00', 0, 1, 7),
	 ('Past Simple', '2020-10-12 00:00:00', 1, 1, 9),
	 ('Past Simple', '2020-10-19 00:00:00', 1, 1, 10),
     ('Present Simple', '2020-10-21 00:00:00', 1, 0, 1),
     ('Present Simple', '2020-10-21 00:00:00', 1, 0, 3),
     ('Present Simple', '2020-10-22 00:00:00', 1, 0, 6),
     ('Present Simple', '2020-10-22 00:00:00', 1, 0, 9),
     ('Present Simple', '2020-10-23 00:00:00', 1, 0, 10),
     ('Data types', '2020-10-17 00:00:00', 1, 1, 2),
     ('Data types', '2020-10-14 00:00:00', 1, 1, 4),
     ('Data types', '2020-10-16 00:00:00', 1, 1, 5),
     ('Data types', '2020-10-18 00:00:00', 1, 0, 8),
     ('Methods', '2020-10-18 00:00:00', 1, 0, 2),
     ('Methods', '2020-10-09 00:00:00', 1, 0, 4),
     ('Methods', '2020-10-07 00:00:00', 1, 0, 5),
     ('Methods', '2020-10-19 00:00:00', 1, 0, 8),
     ('Acords', '2020-10-26 00:00:00', 1, 0, 8);

INSERT INTO test (test_name, deadline_datetime, completed, overdue, course_info_id) VALUES
	('Present simple is the same as present continious?', '2020-10-18 00:00:00', 1, 0, 1),
	('Present simple is the same as present continious?', '2020-10-16 00:00:00', 1, 0, 9),
	('Present simple is the same as present continious?', '2020-10-19 00:00:00', 1, 0, 10),
	('Present simple is the same as present perfect?', '2020-10-16 00:00:00', 1, 0, 3),
	('Present simple is the same as present perfect?', '2020-10-17 00:00:00', 1, 0, 6),
	('Present simple is the same as present perfect?', '2020-10-21 00:00:00', 0, 1, 7),
	('INT is the same as DOUBLE?', '2020-10-21 00:00:00', 1, 0, 2),
	('INT is the same as DOUBLE?', '2020-10-20 00:00:00', 0, 0, 8),
	('NULL is the same as UNDEFINED?', '2020-10-20 00:00:00', 1, 0, 4),
	('NULL is the same as UNDEFINED?', '2020-10-20 00:00:00', 1, 1, 5),
	('What can be method`s output?', '2020-10-20 00:00:00', 1, 0, 2),
	('What can be method`s output?', '2020-10-21 00:00:00', 1, 0, 8);

INSERT INTO answer (answer_name) VALUES
	('yes'),
    ('no'),
    ('string'),
    ('null'),
    ('int'),
    ('another function');
