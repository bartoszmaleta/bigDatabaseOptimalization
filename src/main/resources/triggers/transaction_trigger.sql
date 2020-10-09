DROP TRIGGER IF EXISTS transaction_guard ON "Transactions";
DROP FUNCTION IF EXISTS reverseTransactionType(transactionTypeId int);
DROP FUNCTION IF EXISTS transaction_guard_trigger();

CREATE OR REPLACE FUNCTION transaction_guard_trigger()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
DECLARE
    lastRow "Transactions"%ROWTYPE;
BEGIN
    SELECT *
    INTO lastRow
    FROM "Transactions"
    ORDER BY "Transaction_Date" DESC
    LIMIT 1;

    raise notice 'lala';

    INSERT INTO "Transactions" ("Value", "Transaction_Date", "Transaction_Type_Id",
                                "Account_Id_From", "Account_Id_To",
                                "Card_Id_From", "Card_Id_To")
    VALUES (lastRow."Value",
            lastRow."Transaction_Date",
            reverseTransactionType(lastRow."Transaction_Type_Id"),
            lastRow."Account_Id_To",
            lastRow."Account_Id_From",
            lastRow."Card_Id_To",
            lastRow."Card_Id_From");

    raise notice 'lalala';

    RETURN NEW;
END ;
$$;

CREATE TRIGGER transaction_guard
    AFTER INSERT
    ON "Transactions"
    FOR EACH ROW
    WHEN (pg_trigger_depth() < 1)
EXECUTE PROCEDURE transaction_guard_trigger();


CREATE OR REPLACE FUNCTION reverseTransactionType(transactionTypeId int)
    RETURNS int
    LANGUAGE plpgsql
AS
$$
BEGIN
    IF transactionTypeId = 1
    THEN
        RETURN 2;
    ELSE
        RETURN 1;
    end if;
end;
$$;

INSERT INTO "Transactions" ("Value", "Transaction_Date", "Transaction_Type_Id",
                            "Account_Id_From", "Account_Id_To",
                            "Card_Id_From", "Card_Id_To")
VALUES (7700, now(), 1, 20, 21, 20, 21);


