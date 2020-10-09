DROP INDEX idx_transaction_date;

CREATE INDEX idx_transaction_date
    ON "Transactions" ("Transaction_Date");

-- SELECT *
-- FROM "Transactions"
-- WHERE "Transaction_Date" > now() - interval '20 year';

SELECT *
FROM "Transactions"
WHERE "Transaction_Date" between (now() - interval '30 year')
          and (now() - interval '20 year');