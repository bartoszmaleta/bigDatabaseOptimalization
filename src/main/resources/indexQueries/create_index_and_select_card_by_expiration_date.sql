SELECT * FROM "Cards" WHERE "Expiration_Date" = '1940-01-08';

EXPLAIN SELECT * FROM "Cards" WHERE "Expiration_Date" = '1940-01-08';

CREATE INDEX card_expiration_date_idx ON "Cards"("Expiration_Date");

CREATE OR REPLACE FUNCTION explain_select_card_by_expiration_date()
    RETURNS TEXT AS $explain_select_card_by_expiration_date$
    DECLARE
        num TEXT;
    BEGIN
        EXPLAIN SELECT * INTO num FROM "Cards" WHERE "Expiration_Date" = '1940-01-08';
        RETURN num;
END $explain_select_card_by_expiration_date$ LANGUAGE plpgsql;

SELECT explain_select_card_by_expiration_date()