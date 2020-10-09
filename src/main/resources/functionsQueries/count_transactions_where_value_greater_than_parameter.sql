CREATE OR REPLACE FUNCTION count_transactions_where_value_greater_than_parameter(n INTEGER)
    RETURNS INTEGER AS $count_transactions_where_value_greater_than_parameter$
    DECLARE
        num INTEGER;
    BEGIN
        SELECT COUNT(*) INTO num FROM "Transactions" WHERE "Value" > n;
        RETURN num;
END $count_transactions_where_value_greater_than_parameter$ LANGUAGE plpgsql;

SELECT count_transactions_where_value_greater_than_parameter(1000);