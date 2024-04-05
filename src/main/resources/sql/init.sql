CREATE TABLE person(
    id BIGSERIAL PRIMARY KEY,
    full_Name VARCHAR(128) NOT NULL,
    year_birth INT NOT NULL
);


CREATE TABLE book(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    author VARCHAR(128) NOT NULL,
    year INT NOT NULL,
    person_id BIGINT REFERENCES person
);