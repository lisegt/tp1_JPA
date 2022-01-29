-- Initialisation spécifiques pour un jeu de test
INSERT INTO Country(id,code, name) VALUES
    (4, 'IT','Italia');

INSERT INTO CITY(name, population, country_id) VALUES
    ('Rome', 10, SELECT id FROM Country WHERE code = 'IT'),
    ('Milan', 9, SELECT id FROM Country WHERE code = 'IT');