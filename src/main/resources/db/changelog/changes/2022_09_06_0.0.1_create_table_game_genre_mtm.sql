CREATE TABLE genres (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

INSERT INTO genres (name) VALUES
    ('Online'),
    ('Adventure'),
    ('Strategy'),
    ('Racing'),
    ('Shooter');

CREATE TABLE genres_games (
    genre_id INT NOT NULL REFERENCES genres (id),
    games_id INT NOT NULL REFERENCES games (id)
);