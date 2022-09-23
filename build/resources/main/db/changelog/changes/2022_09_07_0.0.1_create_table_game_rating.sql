CREATE TABLE game_ratings (
    id SERIAL PRIMARY KEY NOT NULL,
    gameplay FLOAT NOT NULL,
    graphics FLOAT NOT NULL,
    difficulty FLOAT NOT NULL
);

INSERT INTO game_ratings (gameplay, graphics, difficulty) VALUES
    (4.5, 4.0, 3),
    (4.5, 4.2, 4.1);

ALTER TABLE games ADD COLUMN game_ratings_id INT REFERENCES game_ratings (id);
UPDATE games SET game_ratings_id = 1 WHERE id = 1;
UPDATE games SET game_ratings_id = 2 WHERE id = 2;