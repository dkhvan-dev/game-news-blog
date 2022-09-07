INSERT INTO users (email, password, first_name, surname, birthdate) VALUES
    ('denis@mail.ru', '$2y$10$b/nH1N1cA7OO305PmBJycum995/Z1PvHIwJ8z98NPFvLBYeifqvti', 'Denis', 'Khvan', '2000.03.26'),
    ('ilyas@gmail.com', '$2y$10$z0jsrvna.SKyDm3A4tVPfeOyn17PnPXts5hS/ep4mDakrr/iIXX/u', 'Ilyas', 'Zhuanyshev', '1989.05.23'),
    ('alisher@mail.ru', '$2y$10$YarGqZBW2AA8X9Pp7me/ie/RDyhuDMsn80oV/XRj4ZWT3y/yLkKXC', 'Alisher', 'Bekkalpenov', '2000.05.23');

INSERT INTO roles_color VALUES
    (1, '#000000'),
    (2, '#00067A'),
    (3, '#117212'),
    (4, '#750001');

INSERT INTO roles VALUES
                                            (1, 'ROLE_USER', 1),
                                            (2, 'ROLE_BLOGGER', 2),
                                            (3, 'ROLE_EDITOR', 3),
                                            (4, 'ROLE_ADMIN', 4);

INSERT INTO users_roles VALUES
                            (1, 4),
                            (2, 1),
                            (3, 2);

INSERT INTO games VALUES
    (1, 'Counter Strike:Global Offensive', 13),
    (2, 'Dota 2', 13);

INSERT INTO application_blogger_status VALUES
                                                           (1, 'IN WAITING', '#A58707'),
                                                           (2, 'APPROVED', '#117212'),
                                                           (3, 'DENIED', '#750001');

INSERT INTO feedback_status VALUES
                                                (1, 'IN WAITING', '#A58707'),
                                                (2, 'DONE', '#117212');

INSERT INTO blogs VALUES
                                                                                         (1, 'SOURCE 2 IN CS:GO?', 'Gabe announced Source 2', '2022.03.09', '2022.03.09', 1, 1),
                                                                                         (2, 'New character is imbalance. Oh my god! WTF?', 'New character was added month ago, but he is imbalance!!! Valve fix him!', '2022.03.09', '2022.03.09', 2, 1);