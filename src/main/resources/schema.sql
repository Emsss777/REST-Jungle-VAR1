CREATE SCHEMA jungle;

CREATE TABLE jungle.foods
(
    id INT,
    name VARCHAR (50),
    PRIMARY KEY (id)
);

CREATE TABLE jungle.animal_family
(
    id INT,
    name VARCHAR (50),
    PRIMARY KEY (id)
);

CREATE TABLE jungle.animals
(
    id INT,
    name VARCHAR (50),
    legs INT,
    id_food INT,
    id_family INT,
    FOREIGN KEY (id_family) REFERENCES animal_family (id),
    FOREIGN KEY (id_food) REFERENCES foods (id),
    PRIMARY KEY (id)
);

CREATE TABLE jungle.study_excluded
(
    id_animal_excluded INT,
    PRIMARY KEY (id_animal_excluded)
);

INSERT INTO jungle.animal_family VALUES (0,'mammal');
INSERT INTO jungle.animal_family VALUES (1,'reptile');
INSERT INTO jungle.animal_family VALUES (2,'birds');
INSERT INTO jungle.animal_family VALUES (3,'arthropod');

INSERT INTO jungle.foods VALUES (0, 'carrot');
INSERT INTO jungle.foods VALUES (1, 'leaves');
INSERT INTO jungle.foods VALUES (2, 'insects');
INSERT INTO jungle.foods VALUES (3, 'vermin');
INSERT INTO jungle.foods VALUES (4, 'birdseed');

INSERT INTO jungle.animals VALUES (0, 'Rabbit', 4, 0, 0);
INSERT INTO jungle.animals VALUES (1, 'Ape', 2, 1, 0);
INSERT INTO jungle.animals VALUES (2, 'Deer', 4, 1, 0);
INSERT INTO jungle.animals VALUES (3, 'Snake', 0, 2, 1);
INSERT INTO jungle.animals VALUES (4, 'Crocodile', 4, 3, 1);
INSERT INTO jungle.animals VALUES (5, 'Chicken', 2, 4, 2);
INSERT INTO jungle.animals VALUES (6, 'Spider', 8, 2, 3);

INSERT INTO jungle.study_excluded VALUES (3);
