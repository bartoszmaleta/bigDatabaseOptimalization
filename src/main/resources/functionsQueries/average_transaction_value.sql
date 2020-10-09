SELECT ROUND(AVG("Value"), 2) FROM "Transactions";

CREATE OR REPLACE FUNCTION average_transaction_value()
    RETURNS INTEGER AS $average_transaction_value$
    DECLARE
        num INTEGER;
    BEGIN
        SELECT ROUND(AVG("Value"), 2) INTO num FROM "Transactions";
        RETURN num;
END $average_transaction_value$ LANGUAGE plpgsql;

SELECT average_transaction_value();