CREATE OR REPLACE FUNCTION created_at_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.created_at := now() AT TIME ZONE 'UTC';
END;
$$ LANGUAGE plpgsql;

-------------------------------------------------

CREATE OR REPLACE FUNCTION updated_at_timestamp()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at := now() AT TIME ZONE 'UTC';
END;
$$ LANGUAGE plpgsql;