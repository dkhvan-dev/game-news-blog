CREATE TABLE operation_system (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR DEFAULT ''
);

INSERT INTO operation_system (name) VALUES
    ('Windows'),
    ('MacOS'),
    ('Xbox');

CREATE TABLE os_requirements (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR DEFAULT '',
    operation_system_id INT NOT NULL REFERENCES operation_system (id)
);

INSERT INTO os_requirements (name, operation_system_id) VALUES
    ('Windows® 7/Vista/XP', 1),
    ('Windows 7', 1),
    ('MacOS X 10.11 (El Capitan)', 2),
    ('OS X Mavericks 10.9', 2);

CREATE TABLE processor_requirements (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR DEFAULT '',
    operation_system_id INT NOT NULL REFERENCES operation_system (id)
);

INSERT INTO processor_requirements (name, operation_system_id) VALUES
    ('Intel® Core™ 2 Duo E6600 или AMD Phenom™ X3 8750', 1),
    ('Двухъядерный процессор с тактовой частотой 2,80 ГГц от Intel или AMD', 1),
    ('Процессор Intel Core Duo 2 ГГц', 2),
    ('Двухъядерный процессор от Intel', 2);


CREATE TABLE memory_requirements (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR DEFAULT '',
    operation_system_id INT NOT NULL REFERENCES operation_system (id)
);

INSERT INTO memory_requirements (name, operation_system_id) VALUES
    ('2 GB ОЗУ', 1),
    ('4 GB ОЗУ', 1),
    ('2 GB ОЗУ', 2),
    ('4 GB ОЗУ', 2);

CREATE TABLE graphics_requirements (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR DEFAULT '',
    operation_system_id INT NOT NULL REFERENCES operation_system (id)
);

INSERT INTO graphics_requirements (name, operation_system_id) VALUES
    ('Видеокарта с 256 МБ памяти, совместимая с DirectX 9 с поддержкой Pixel Shader 3.0', 1),
    ('NVIDIA GeForce 8600/9600GT, ATI/AMD Radeon HD2600/3600', 1),
    ('ATI Radeon HD 2400 / NVIDIA 8600M', 2),
    ('NVIDIA 320M, Radeon HD 2400, Intel HD 3000', 2);

CREATE TABLE storage_requirements (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR DEFAULT '',
    operation_system_id INT NOT NULL REFERENCES operation_system (id)
);

INSERT INTO storage_requirements (name, operation_system_id) VALUES
    ('15 GB', 1),
    ('15 GB', 2);

ALTER TABLE games ADD COLUMN description TEXT DEFAULT '';
ALTER TABLE games ADD COLUMN windows_requirements_id INT REFERENCES windows_requirements (id);
ALTER TABLE games ADD COLUMN macos_requirements_id INT REFERENCES macos_requirements (id);

UPDATE games SET description = 'Counter-Strike: Global Offensive (CS:GO) расширяет границы ураганной командной игры, представленной ещё 19 лет назад. CS:GO включает в себя новые карты, персонажей, оружие и режимы игры, а также улучшает классическую составляющую CS (de_dust2 и т. п.). «Counter-Strike удивила всю игровую индустрию, когда ничем не примечательная модификация стала одним из самых популярных шутеров в мире почти сразу после выпуска в августе 1999 года, — говорит Даг Ломбарди из Valve. — Уже на протяжении 12 лет она продолжает быть одной из самых популярных игр в мире и возглавляет киберспортивные соревнования, а по всему миру продано более 25 миллионов игр этой серии. CS:GO обещает расширить границы заслужившего известность игрового процесса и предложить его игрокам не только на ПК, но и на консолях следующего поколения и компьютерах Mac».' WHERE id = 1;
UPDATE games SET description = 'Ежедневно миллионы игроков по всему миру сражаются от лица одного из более сотни героев Dota 2, и даже после тысячи часов в ней есть чему научиться. Благодаря регулярным обновлениям игра живёт своей жизнью: геймплей, возможности и герои постоянно преображаются. Игра может похвастаться бесконечным разнообразием героев, способностей и предметов, благодаря чему каждый матч уникален. Любой герой может исполнять множество ролей, и для каждой нужды найдутся подходящие предметы. Здесь вас ничто не ограничивает — вы сами выбираете, какой стратегии следовать. Равные соревновательные возможности — главная гордость игры. Чтобы все находились в одинаковом положении, её основа — в частности, обширный список героев — доступна каждому игроку. Если хотите, вы можете коллекционировать предметы, видоизменяющие как героев, так и мир вокруг них, но всё необходимое для вашего первого матча уже есть в игре. Dota 2 многогранна, и она не стоит на месте, но вступить в игру никогда не поздно. Научитесь основам в матче против ботов. Опробуйте возможности героев. А затем вступайте в битву с игроками вашего уровня и поведения: об этом позаботится система подбора игры.' WHERE id = 2;
UPDATE games SET windows_requirements_id = 2 WHERE id = 2;
UPDATE games SET macos_requirements_id = 2 WHERE id = 2;