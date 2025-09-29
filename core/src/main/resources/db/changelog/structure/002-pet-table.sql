CREATE TABLE IF NOT EXISTS pet
(
    id              BIGSERIAL PRIMARY KEY,
    owner_id        BIGINT       NULL REFERENCES "user" (id),
    name            VARCHAR(100) NOT NULL,
    breed           VARCHAR(50),
    gender          VARCHAR(10)  NOT NULL CHECK (gender IN ('MALE', 'FEMALE')),
    birth_date      DATE,
    microchip_id    VARCHAR(50) UNIQUE,
    weight_in_grams DECIMAL(5, 2),
    created_at      timestamptz  NOT NULL DEFAULT current_timestamp,
    updated_at      timestamptz  NOT NULL DEFAULT current_timestamp
);

DROP TRIGGER IF EXISTS created_at ON pet;
CREATE TRIGGER created_at
    BEFORE INSERT
    ON pet
    FOR EACH ROW
EXECUTE FUNCTION created_at_timestamp();

DROP TRIGGER IF EXISTS updated_at ON pet;
CREATE TRIGGER updated_at
    BEFORE UPDATE
    ON pet
    FOR EACH ROW
EXECUTE FUNCTION updated_at_timestamp();


