CREATE TABLE incident (
  id IDENTITY NOT NULL,
  title VARCHAR(255) UNIQUE NOT NULL,
  description TEXT,
  source_url VARCHAR(255) NOT NULL,
  incident_date TIMESTAMP NOT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by BIGINT,
  modified_at TIMESTAMP,
  modified_by BIGINT,

  PRIMARY KEY(id)
);