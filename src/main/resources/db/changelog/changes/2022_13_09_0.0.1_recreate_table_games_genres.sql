DROP TABLE games_genres;
CREATE TABLE games_genres (
    game_id INT NOT NULL REFERENCES games (id),
    genres_id INT NOT NULL REFERENCES genres (id)
);

INSERT INTO games_genres VALUES
    (1, 1),
    (2, 1),
    (2, 3),
    (1, 5);