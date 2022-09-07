CREATE TABLE genres (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

INSERT INTO genres VALUES
    (1, 'Online'),
    (2, 'Adventure'),
    (3, 'Strategy'),
    (4, 'Racing'),
    (5, 'Shooter');

CREATE TABLE genres_games (
    genre_id INT NOT NULL REFERENCES genres (id),
    games_id INT NOT NULL REFERENCES games (id)
);