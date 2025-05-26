CREATE TABLE developer (developer_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, developer_name VARCHAR(100) NOT NULL, country VARCHAR(50), founded_date DATE);

CREATE TABLE publisher (publisher_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, publisher_name VARCHAR(100) NOT NULL, country VARCHAR(50));

CREATE TABLE game (game_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, title VARCHAR(100) NOT NULL, price DECIMAL(10, 2), release_date DATE, developer_id INT NOT NULL, publisher_id INT NOT NULL, CONSTRAINT fk_GAME_DEVELOPER FOREIGN KEY (developer_id) REFERENCES developer(developer_id),  CONSTRAINT fk_GAME_PUBLISHER FOREIGN KEY (publisher_id) REFERENCES publisher(publisher_id));