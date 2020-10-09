SELECT * FROM "Transactions" WHERE "Value" = '4';

EXPLAIN SELECT * FROM "Transactions" WHERE "Value" = '4';

CREATE INDEX firstname_idx ON "Customers"("First_Name");

CREATE OR REPLACE FUNCTION explain_select_transaction_by_value()
    RETURNS TEXT AS $explain_select_transaction_by_value$
    DECLARE
        num TEXT;
    BEGIN
        EXPLAIN SELECT * INTO num FROM "Transactions" WHERE "Value" = '4';
        RETURN num;
END $explain_select_transaction_by_value$ LANGUAGE plpgsql;

SELECT explain_select_transaction_by_value();