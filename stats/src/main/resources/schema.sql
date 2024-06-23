DROP TABLE IF EXISTS applications CASCADE;
DROP TABLE IF EXISTS records CASCADE;

CREATE TABLE applications (
  app_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  app VARCHAR(32) NOT NULL,
  PRIMARY KEY (app_id)
);

CREATE TABLE records (
  rec_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  app_id BIGINT NOT NULL,
  uri VARCHAR(128) NOT NULL,
  ip VARCHAR(16) NOT NUll,
  timestamp VARCHAR(64) NOT NULL,
  PRIMARY KEY (rec_id)
);

ALTER TABLE records ADD FOREIGN KEY (app_id) REFERENCES applications (app_id) ON DELETE CASCADE;
