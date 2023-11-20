CREATE TABLE IF NOT EXISTS sounds
(
    id                    SERIAL      PRIMARY KEY,
    title                 VARCHAR     NOT NULL,
    bpm                   INTEGER     NOT NULL,
    genres                VARCHAR[]   NOT NULL,
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
    PRIMARY KEY (playlist_id, sound_id),
    CONSTRAINT fk_playlist_id FOREIGN KEY(playlist_id)  REFERENCES playlists(id),
    CONSTRAINT fk_sound_id    FOREIGN KEY(sound_id)     REFERENCES sounds(id)
);

CREATE TABLE IF NOT EXISTS credits
(
  id        SERIAL          PRIMARY KEY,
  name      VARCHAR         NOT NULL,
  role      VARCHAR         NOT NULL
);

CREATE TABLE IF NOT EXISTS sound_credits
(
  credit_id INTEGER NOT NULL,
  sound_id  INTEGER NOT NULL,
  PRIMARY KEY (credit_id, sound_id),
  CONSTRAINT fk_credits_id  FOREIGN KEY(credit_id) REFERENCES credits(id),
  CONSTRAINT fk_sound_id    FOREIGN KEY(sound_id)   REFERENCES sounds(id)
);
