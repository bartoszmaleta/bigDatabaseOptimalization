SELECT ROUND(MIN("Balance"), 2) FROM "Accounts";

CREATE OR REPLACE FUNCTION min_balance()
    RETURNS INTEGER AS $min_balance$
    DECLARE
        num INTEGER;
    BEGIN
        SELECT ROUND(MIN("Balance"), 2) INTO num FROM "Accounts";
        RETURN num;
END $min_balance$ LANGUAGE plpgsql;

SELECT min_balance();