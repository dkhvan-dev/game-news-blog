CREATE TABLE favorites (
    id SERIAL NOT NULL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users (id),
    blogs_id INT NOT NULL REFERENCES blogs (id)
);