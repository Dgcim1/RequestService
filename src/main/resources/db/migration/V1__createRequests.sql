CREATE TABLE IF NOT EXISTS requests(
    id INTEGER NOT NULL,
    name VARCHAR(64) NOT NULL,
    email VARCHAR(64) NOT NULL,
    comment VARCHAR(16384) NOT NULL,
    active BOOLEAN NOT NULL default true,
    creation_timestamp timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
    )