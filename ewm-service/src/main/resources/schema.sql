DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS categories CASCADE;


CREATE TABLE users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  email VARCHAR(254) NOT NULL UNIQUE,
  name VARCHAR(250) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE categories (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

