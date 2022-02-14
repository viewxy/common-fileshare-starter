CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE image (
    id uuid DEFAULT uuid_generate_v4(),
    category VARCHAR NOT NULL,
    content BYTEA NOT NULL,
    extension VARCHAR NOT NULL,
    PRIMARY KEY (id)
);
GRANT ALL PRIVILEGES ON TABLE image TO myuser;
