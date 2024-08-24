DROP TABLE IF EXISTS batch;
DROP TABLE IF EXISTS recipe;

CREATE TABLE IF NOT EXISTS recipe (
    id BIGINT NOT NULL AUTO_INCREMENT(100) PRIMARY KEY,
    brewfather_id VARCHAR(50) UNIQUE,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS batch (
    id BIGINT NOT NULL AUTO_INCREMENT(100) PRIMARY KEY,
    brewfather_id VARCHAR(50) UNIQUE,
    name VARCHAR(100),
    status VARCHAR(30),
    recipe BIGINT,
    FOREIGN KEY (recipe) REFERENCES recipe(id)
);
INSERT INTO recipe (id, brewfather_id, name) VALUES (100001, 'b1W72a2OuL9xsC1c1OTrgog6JiPP0b', 'Eldon');
INSERT INTO recipe (id, brewfather_id, name) VALUES (100002, 'oCJlXhR1iIdYgLMwem0V0mmYNBIIia', 'KIWI Pilsner');

INSERT INTO batch (id, brewfather_id, name, status, recipe) VALUES (10, 'LAXI2KWZXcU2pBpzrfg6B3Uy5940vQ', 'Eldon',            'COMPLETED', 100001);
INSERT INTO batch (id, brewfather_id, name, status, recipe) VALUES (11, '6TY4Hg2JfQA0zBnscwspPsVF29JAKM', 'KIWI Pilsner',     'COMPLETED', 100002);
INSERT INTO batch (id, brewfather_id, name, status)         VALUES (12, 'HY27A73dYWZNMxapgE4UdljPtNvDOL', 'MarisOtter SMASH', 'COMPLETED');
INSERT INTO batch (id, brewfather_id, name, status)         VALUES (13, 'NLltIcoo87foHbTz1N8rH0v0KXht6q', 'Juicy SafAle',     'COMPLETED');
INSERT INTO batch (id, brewfather_id, name, status)         VALUES (14, 'NLltIcoo87foHbTz1N8rH0v0KXht6P', 'Juicy NEIPA',      'COMPLETED');
INSERT INTO batch (id, brewfather_id, name, status)         VALUES (15, 'NLltIcoo87foHbTz1N8rH0v0KXht6A', 'NETTO',            'COMPLETED');