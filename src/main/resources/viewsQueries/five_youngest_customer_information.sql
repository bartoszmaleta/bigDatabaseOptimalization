CREATE OR REPLACE VIEW five_youngest_customer_information AS
SELECT "Customer_Id"       AS "ID",
       "First_Name"        AS "First name",
       "Second_Name"       AS "Second name",
       "Surname"           AS "Surname",
       "Birthday"          AS "Birthday",
       "Street"            AS "Street",
       "Postcode"          AS "Postcode",
       "City"              AS "City",
       "State"             AS "State",
       "Account_Number"    AS "AccNumber",
       "Account_Type_Name" AS "AccType"
FROM "Customers" cu
         INNER JOIN "Customers_Addresses" USING ("Customer_Id")
         INNER JOIN "Customers_Accounts" USING ("Customer_Id")
         INNER JOIN "Accounts" USING ("Account_Id")
         INNER JOIN "Accounts_Types" USING ("Account_Type_Id")
ORDER BY "Birthday" DESC
LIMIT 5;

SELECT * FROM five_youngest_customer_information;