
-- CREATE SEQUENCES AND TABLES

-- PERSON
DROP SEQUENCE IF EXISTS person_sequence CASCADE;
CREATE SEQUENCE person_sequence START 1 INCREMENT 1;

DROP TABLE IF EXISTS person CASCADE;
CREATE TABLE person (
                        id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('person_sequence'),
                        first_name VARCHAR(255) NOT NULL,
                        last_name VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        birth_date DATE NOT NULL
);

-- USER AND AUTHORITY

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
                       username VARCHAR(255) PRIMARY KEY,
                       password VARCHAR(68) NOT NULL,
                       enabled BOOLEAN NOT NULL
);

DROP SEQUENCE IF EXISTS authority_sequence CASCADE;
CREATE SEQUENCE authority_sequence START 1 INCREMENT 1;

DROP TABLE IF EXISTS authorities CASCADE;
CREATE TABLE authorities (
                             id BIGINT PRIMARY KEY DEFAULT nextval('authority_sequence'),
                             role VARCHAR(255),
                             username VARCHAR(255),
                             FOREIGN KEY (username) REFERENCES users(username)
);






