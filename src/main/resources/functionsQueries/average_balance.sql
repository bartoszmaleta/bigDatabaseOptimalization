CREATE OR REPLACE FUNCTION average_balance()
    RETURNS INTEGER AS $average_balance$
    DECLARE
        num INTEGER;
    BEGIN
        SELECT ROUND(AVG("Balance"), 2) INTO num FROM "Accounts";
        RETURN num;
END $average_balance$ LANGUAGE plpgsql;

SELECT average_balance();