CREATE TABLE IF NOT EXISTS sounds
(
    id                    SERIAL      PRIMARY KEY,
    title                 VARCHAR     NOT NULL,
    bpm                   INTEGER     NOT NULL,
    genres                VARCHAR     NOT NULL,
    duration_in_seconds   INTEGER     NOT NULL
);


CREATE TABLE IF NOT EXISTS playlists
(
    id      SERIAL    PRIMARY KEY,
    title   VARCHAR   NOT NULL
);

CREATE TABLE IF NOT EXISTS playlist_sounds
(
    playlist_id  INTEGER   NOT NULL,
    sound_id     INTEGER   NOT NULL,
    PRIMARY KEY (playlist_id, sound_id)
);
