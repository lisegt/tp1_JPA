-- Initialisation des tables
-- Solution 1 : requêtes imbriquées
/*
INSERT INTO Country(code, name) VALUES
    ('FR', 'France'), -- Les clés sont auto-générées
    ('UK', 'United Kingdom'),
    ('US', 'United States of America');

INSERT INTO CITY(name, population, country_id) VALUES
    ('Paris', 12, SELECT id FROM Country WHERE code = 'FR'),
    ('London', 18, SELECT id FROM Country WHERE code = 'UK'),
    ('New York', 27, SELECT id FROM Country WHERE code = 'US');
*/

-- Solution 2 : Fixer les clés primaire auto-générées
-- Initialisation des tables
INSERT INTO Country(id, code, name) VALUES
    (1, 'FR', 'France'), -- Les clés sont auto-générées
    (2, 'UK', 'United Kingdom'),
    (3, 'US','United States of America');

INSERT INTO CITY(name, population, country_id) VALUES
    ('Paris', 12, 1),
    ('London', 18, 2),
    ('New York', 27, 3);