DROP TABLE pets IF EXISTS;
DROP TABLE types IF EXISTS;

CREATE TABLE types (
                       id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                       name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);


CREATE TABLE pets (
                      id         INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                      name       VARCHAR(30),
                      birth_date DATE,
                      type_id    INTEGER NOT NULL,
                      owner_id   INTEGER
);
ALTER TABLE pets ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name ON pets (name);

CREATE TABLE pet_visit (
                              id          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                              pet_id      INTEGER,
                              visit_date  DATE,
                              description VARCHAR(255)
);
ALTER TABLE pet_visit ADD CONSTRAINT fk_pet_visit_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
CREATE INDEX pet_visit_pet_id ON pet_visit (pet_id);















