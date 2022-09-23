CREATE TABLE roles_color(
    id SERIAL PRIMARY KEY NOT NULL,
    color TEXT NOT NULL
);

CREATE TABLE roles(
    id SERIAL PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    role_color_id INT NOT NULL REFERENCES roles_color (id)
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    surname TEXT NOT NULL,
    birthdate DATE NOT NULL
);

CREATE TABLE users_roles(
    user_id INT NOT NULL REFERENCES users (id),
    roles_id INT NOT NULL REFERENCES roles (id)
);

CREATE TABLE application_blogger_status(
   id SERIAL PRIMARY KEY NOT NULL,
   status TEXT NOT NULL,
   color TEXT DEFAULT '#000000'
);

CREATE TABLE application_form_blogger(
    id SERIAL PRIMARY KEY NOT NULL,
    description TEXT DEFAULT '-',
    receipt_date DATE NOT NULL,
    update_date DATE NOT NULL,
    application_blogger_status_id INT NOT NULL REFERENCES application_blogger_status (id),
    users_id INT NOT NULL REFERENCES users (id)
);

CREATE TABLE games(
    id SERIAL PRIMARY KEY NOT NULL,
    name varchar(255) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE blogs(
    id SERIAL PRIMARY KEY NOT NULL,
    title varchar(255) NOT NULL,
    description TEXT DEFAULT '-',
    create_date DATE NOT NULL,
    update_date DATE NOT NULL,
    games_id INT NOT NULL REFERENCES games (id),
    users_id INT NOT NULL REFERENCES users (id)
);

CREATE TABLE feedback_status(
    id SERIAL PRIMARY KEY NOT NULL,
    status varchar(255) NOT NULL,
    color varchar(255) DEFAULT '#d2b107'
);

CREATE TABLE feedback(
    id SERIAL PRIMARY KEY NOT NULL,
    name varchar(255) DEFAULT 'Anonymous',
    email varchar(255) NOT NULL,
    subject varchar(255) DEFAULT '-',
    description TEXT DEFAULT '-',
    receipt_date DATE NOT NULL,
    update_date DATE NOT NULL,
    feedback_status_id INT NOT NULL REFERENCES feedback_status (id)
);

