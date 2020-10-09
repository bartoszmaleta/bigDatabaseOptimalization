SELECT ROUND(MAX("Balance"), 2) FROM "Accounts";

CREATE OR REPLACE FUNCTION max_balance()
    RETURNS INTEGER AS $max_balance$
    DECLARE
        num INTEGER;
    BEGIN
        SELECT ROUND(MAX("Balance"), 2) INTO num FROM "Accounts";
        RETURN num;
END $max_balance$ LANGUAGE plpgsql;

SELECT max_balance();