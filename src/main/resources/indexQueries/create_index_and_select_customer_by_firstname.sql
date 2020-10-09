SELECT * FROM "Customers" WHERE "First_Name" = 'lyne';

EXPLAIN SELECT * FROM "Customers" WHERE "First_Name" = 'lyne';

CREATE INDEX firstname_idx ON "Customers"("First_Name");

CREATE OR REPLACE FUNCTION explain_select_customer_by_first_name()
    RETURNS TEXT AS $explain_select$
    DECLARE
        num TEXT;
    BEGIN
        EXPLAIN SELECT * INTO num FROM "Customers" WHERE "First_Name" = 'aaniah';
        RETURN num;
END $explain_select$ LANGUAGE plpgsql;

SELECT explain_select_customer_by_first_name();