CREATE TABLE news (
    id SERIAL PRIMARY KEY NOT NULL,
    title VARCHAR DEFAULT '',
    description TEXT DEFAULT '',
    create_date DATE DEFAULT CURRENT_DATE,
    game_id INT NOT NULL REFERENCES games (id),
    author_id INT NOT NULL REFERENCES users (id)
);