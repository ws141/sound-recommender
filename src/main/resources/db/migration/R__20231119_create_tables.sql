CREATE TABLE IF NOT EXISTS sounds
(
    id                    SERIAL      PRIMARY KEY,
    title                 VARCHAR     NOT NULL,
    bpm                   INTEGER     NOT NULL,
    genres                VARCHAR     NOT NULL,
    duration_in_seconds   INTEGER     NOT NULL
);
