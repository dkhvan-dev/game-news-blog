CREATE TABLE comments (
    id SERIAL PRIMARY KEY NOT NULL,
    comment TEXT DEFAULT '',
    create_date DATE DEFAULT CURRENT_DATE,
    blog_id INT NOT NULL REFERENCES blogs (id),
    author_id INT NOT NULL REFERENCES users (id)
);

ALTER TABLE users ADD COLUMN avatar VARCHAR DEFAULT '';