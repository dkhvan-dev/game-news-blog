ALTER TABLE game_ratings ADD COLUMN avg FLOAT DEFAULT 0.0;
UPDATE game_ratings SET avg = 3.8 WHERE id = 1;
UPDATE game_ratings SET avg = 4.3 WHERE id = 2;