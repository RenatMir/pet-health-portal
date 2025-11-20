CREATE TABLE IF NOT EXISTS pet
(
    id              BIGSERIAL PRIMARY KEY,
    owner_id        BIGINT      NULL,
    name            VARCHAR     NOT NULL,
    species         VARCHAR     NOT NULL,
    gender          VARCHAR     NOT NULL,
    birth_date      DATE        NOT NULL,
    microchip_id    VARCHAR     NULL UNIQUE,
    weight_in_grams INTEGER     NOT NULL,
    version         INTEGER     NOT NULL DEFAULT 0,
    created_at      timestamptz NOT NULL,
    updated_at      timestamptz NOT NULL,

    CONSTRAINT "pet__owner_id__fk"
        FOREIGN KEY (owner_id) REFERENCES "user" (id)
);