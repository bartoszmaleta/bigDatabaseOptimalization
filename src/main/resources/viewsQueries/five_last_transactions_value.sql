CREATE OR REPLACE VIEW five_last_transactions_value AS
    SELECT "Transaction_Id" AS "id",
           "Value" AS "value",
           "Transaction_Date" AS "date"
    FROM "Transactions" tr
    ORDER BY "Transaction_Date" DESC
    LIMIT 5;

SELECT * FROM five_last_transactions_value;