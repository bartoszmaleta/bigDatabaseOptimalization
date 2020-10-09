CREATE TRIGGER value_check
    BEFORE UPDATE
    ON "Transactions"
    FOR EACH ROW
EXECUTE PROCEDURE value_trigger();


CREATE FUNCTION value_trigger()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF "Value" <= 0
    THEN
        delete from "Transactions" WHERE "Value" <= 0;
    end if;
END;
$$