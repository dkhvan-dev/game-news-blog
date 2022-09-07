ALTER TABLE os_requirements ADD COLUMN games_id INT REFERENCES games (id);
ALTER TABLE processor_requirements ADD COLUMN games_id INT REFERENCES games (id);
ALTER TABLE memory_requirements ADD COLUMN games_id INT REFERENCES games (id);
ALTER TABLE graphics_requirements ADD COLUMN games_id INT REFERENCES games (id);
ALTER TABLE storage_requirements ADD COLUMN games_id INT REFERENCES games (id);

UPDATE os_requirements SET games_id = 1 WHERE id = 1;
UPDATE os_requirements SET games_id = 2 WHERE id = 2;
UPDATE os_requirements SET games_id = 1 WHERE id = 3;
UPDATE os_requirements SET games_id = 2 WHERE id = 4;