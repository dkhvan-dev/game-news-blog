CREATE TABLE blog_status (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR DEFAULT ''
);

INSERT INTO blog_status (name) VALUES
    ('IN WAITING'),
    ('APPROVED'),
    ('REJECTED');

ALTER TABLE blogs ADD COLUMN blog_status_id INT REFERENCES blog_status (id);
UPDATE blogs SET blog_status_id = 2 WHERE blogs.id = 1;
UPDATE blogs SET blog_status_id = 2 WHERE blogs.id = 2;