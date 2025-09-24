-- liquibase formatted sql

-- changeset nfrimerman:1
CREATE TABLE IF NOT EXISTS faculty (
id SERIAL,
name TEXT,
color TEXT
)
-- changeset nfrimerman:2
CREATE TABLE IF NOT EXISTS student (
 id SERIAL,
 name TEXT,
 age INTEGER
 )
-- changeset nfrimerman:3
 CREATE INDEX student_by_name ON student (name);
 -- changeset nfrimerman:4
 CREATE INDEX faculty_by_name_color ON faculty (name, color);



