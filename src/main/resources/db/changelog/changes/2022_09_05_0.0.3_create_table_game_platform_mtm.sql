CREATE TABLE platform (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

INSERT INTO platform (name) VALUES
    ('Windows'),
    ('MacOS'),
    ('Xbox 360'),
    ('Play Station'),
    ('Play Station VR');

CREATE TABLE games_platform (
    game_id INT NOT NULL REFERENCES games (id),
    platform_id INT NOT NULL REFERENCES platform (id)
);