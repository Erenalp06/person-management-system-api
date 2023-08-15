
-- Insert data into the tables

-- INSERT PERSONS
INSERT INTO person (first_name, last_name, email, birth_date)
VALUES
    ('Alice', 'Johnson', 'alice@example.com', '1995-03-10'),
    ('Michael', 'Williams', 'michael@example.com', '1987-11-28'),
    ('Emily', 'Brown', 'emily@example.com', '1992-08-15'),
    ('Daniel', 'Miller', 'daniel@example.com', '1998-05-03'),
    ('Olivia', 'Jones', 'olivia@example.com', '2000-02-20');

-- INSERT USERS AND AUTHORITIES
INSERT INTO users (username, password, enabled)
VALUES
    ('user2', '$2a$12$pYx1952b1pYSGQ3AwsCw/eHTQZyZw/K9NOJIt8SMJXHn.x/mwn7Qi', true),
    ('admin2', '$2a$12$pYx1952b1pYSGQ3AwsCw/eHTQZyZw/K9NOJIt8SMJXHn.x/mwn7Qi', true),
    ('guest', '$2a$12$pYx1952b1pYSGQ3AwsCw/eHTQZyZw/K9NOJIt8SMJXHn.x/mwn7Qi', false),
    ('user3', '$2a$12$pYx1952b1pYSGQ3AwsCw/eHTQZyZw/K9NOJIt8SMJXHn.x/mwn7Qi', true),
    ('user4', '$2a$12$pYx1952b1pYSGQ3AwsCw/eHTQZyZw/K9NOJIt8SMJXHn.x/mwn7Qi', true);

INSERT INTO authorities (role, username)
VALUES
    ('ROLE_USER', 'user2'),
    ('ROLE_ADMIN', 'admin2'),
    ('ROLE_MODERATOR', 'guest'),
    ('ROLE_USER', 'user3'),
    ('ROLE_USER', 'user4');
