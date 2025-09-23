CREATE TABLE Car (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INT
);

CREATE TABLE Person (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT,
    age INT,
    license BOOLEAN,
    car_id INT,
    FOREIGN KEY (car_id) REFERENCES Car(id)
);