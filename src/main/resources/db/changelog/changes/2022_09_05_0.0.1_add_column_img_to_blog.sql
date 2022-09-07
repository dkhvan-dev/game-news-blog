ALTER TABLE blogs ADD COLUMN image VARCHAR;
UPDATE blogs SET image = './img/blog/1.jpg' WHERE id = 1;
UPDATE blogs SET image = './img/blog/2.jpg' WHERE id = 2;