CREATE TABLE IF NOT EXISTS "user"
(
    id          BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR(100) NOT NULL,
    second_name VARCHAR(100),
    birth_date  DATE,
    created_at  timestamptz  NOT NULL DEFAULT current_timestamp,
    updated_at  timestamptz  NOT NULL DEFAULT current_timestamp
);

DROP TRIGGER IF EXISTS created_at ON "user";
CREATE TRIGGER created_at
    BEFORE INSERT
    ON "user"
    FOR EACH ROW
EXECUTE FUNCTION created_at_timestamp();

DROP TRIGGER IF EXISTS updated_at ON "user";
CREATE TRIGGER updated_at
    BEFORE UPDATE
    ON "user"
    FOR EACH ROW
EXECUTE FUNCTION updated_at_timestamp();


