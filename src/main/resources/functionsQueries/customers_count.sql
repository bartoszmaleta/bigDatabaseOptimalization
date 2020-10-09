CREATE OR REPLACE FUNCTION customers_count()
    RETURNS INTEGER AS $customers_count$
    DECLARE
        num INTEGER;
    BEGIN
        SELECT COUNT(*) INTO num FROM "Customers";
        RETURN num;
END $customers_count$ LANGUAGE plpgsql;

SELECT customers_count();