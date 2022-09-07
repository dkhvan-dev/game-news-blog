ALTER TABLE games ADD COLUMN image VARCHAR;
UPDATE games SET image = 'img/games/1.jpg' WHERE id = 1;
UPDATE games SET image = 'img/games/2.jpg' WHERE id = 2;