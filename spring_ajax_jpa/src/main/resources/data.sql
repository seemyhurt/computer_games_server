INSERT INTO developer (developer_name, country, founded_date) VALUES ('CD Projekt Red', 'Poland', DATE '2002-02-10' );
INSERT INTO developer (developer_name, country, founded_date) VALUES ('Rockstar North', 'UK', DATE '1988-01-01');
INSERT INTO developer (developer_name, country, founded_date) VALUES ('Naughty Dog', 'USA', DATE '1984-09-27');
INSERT INTO developer (developer_name, country, founded_date) VALUES ('Ubisoft Montreal', 'Canada', DATE '1997-04-01');
INSERT INTO developer (developer_name, country, founded_date) VALUES ('FromSoftware', 'Japan', DATE '1986-11-01');
INSERT INTO developer (developer_name, country, founded_date) VALUES ('Bethesda Game Studios', 'USA', DATE '2001-01-01');
INSERT INTO developer (developer_name, country, founded_date) VALUES ('Mojang Studios', 'Sweden', DATE '2009-05-01');

INSERT INTO publisher (publisher_name, country) VALUES ('CD Projekt', 'Poland');
INSERT INTO publisher (publisher_name, country) VALUES ('Rockstar Games', 'USA');
INSERT INTO publisher (publisher_name, country) VALUES ('Sony Interactive Entertainment', 'Japan');
INSERT INTO publisher (publisher_name, country) VALUES ('Ubisoft', 'France');
INSERT INTO publisher (publisher_name, country) VALUES ('Bandai Namco Entertainment', 'Japan');
INSERT INTO publisher (publisher_name, country) VALUES ('Bethesda Softworks', 'USA');
INSERT INTO publisher (publisher_name, country) VALUES ('Microsoft Game Studios', 'USA');

INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('The Witcher 3: Wild Hunt', DATE '2015-05-19', 39.99, 1, 1);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('Cyberpunk 2077', DATE '2020-12-10', 59.99, 1, 1);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('Grand Theft Auto V', DATE '2013-09-17', 29.99, 2, 2);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('Red Dead Redemption 2', DATE '2018-10-26', 59.99, 2, 2);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('The Last of Us Part II', DATE '2020-06-19', 49.99, 3, 3);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('Uncharted 4: A Thief End', DATE '2016-05-10', 19.99, 3, 3);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('Assassin''s Creed Valhalla', DATE '2020-11-10', 59.99, 4, 4);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('Far Cry 6', DATE '2021-10-07', 59.99, 4, 4);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('Elden Ring', DATE '2022-02-25', 59.99, 5, 5);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('Dark Souls III', DATE '2016-04-12', 39.99, 5, 5);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('The Elder Scrolls V: Skyrim', DATE '2011-11-11', 19.99, 6, 6);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('Fallout 4', DATE '2015-11-10', 29.99, 6, 6);
INSERT INTO game (title, release_date, price, developer_id, publisher_id) VALUES ('Minecraft', DATE '2011-11-18', 26.95, 7, 7);