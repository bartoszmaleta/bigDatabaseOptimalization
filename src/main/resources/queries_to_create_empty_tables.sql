CREATE TABLE "Customers"
(
    "Customer_Id" BIGSERIAL NOT NULL,
    "Login"       INT       NOT NULL,
    "Password"    TEXT      NOT NULL,
    "First_Name"  TEXT      NOT NULL,
    "Second_Name" TEXT      NOT NULL,
    "Surname"     TEXT      NOT NULL,
    "Birthday"    DATE      NOT NULL,
    PRIMARY KEY ("Customer_Id")
);

CREATE TABLE "Customers_Addresses"
(
    "Customer_Address_Id" BIGSERIAL NOT NULL,
    "Street"              TEXT      NOT NULL,
    "Postcode"            TEXT      NOT NULL,
    "City"                TEXT      NOT NULL,
    "State"               TEXT      NOT NULL,
    "Customer_Id"         BIGINT    NOT NULL REFERENCES "Customers" ("Customer_Id"),
    PRIMARY KEY ("Customer_Address_Id")
);


CREATE TABLE "Credits_Types"
(
    "Credit_Type_Id"   SMALLSERIAL NOT NULL,
    "Credit_Type_Name" TEXT        NOT NULL,
    PRIMARY KEY ("Credit_Type_Id")
);

CREATE TABLE "Accounts_Types"
(
    "Account_Type_Id"   SMALLSERIAL NOT NULL,
    "Account_Type_Name" TEXT        NOT NULL,
    PRIMARY KEY ("Account_Type_Id")
);

CREATE TABLE "Transactions_Types"
(
    "Transaction_Type_Id"   SMALLSERIAL NOT NULL,
    "Transaction_Type_Name" TEXT        NOT NULL,
    PRIMARY KEY ("Transaction_Type_Id")
);

CREATE TABLE "Cards_Types"
(
    "Card_Type_Id"   SMALLSERIAL NOT NULL,
    "Card_Type_Name" TEXT        NOT NULL,
    PRIMARY KEY ("Card_Type_Id")
);

CREATE TABLE "Accounts"
(
    "Account_Id"      BIGSERIAL NOT NULL,
    "Balance"         BIGINT    NOT NULL,
    "Account_Number"  NUMERIC   NOT NULL,
    "Account_Type_Id" SMALLINT  NOT NULL REFERENCES "Accounts_Types" ("Account_Type_Id"),
    PRIMARY KEY ("Account_Id")
);

CREATE TABLE "Credits"
(
    "Credit_Id"      BIGSERIAL     NOT NULL,
    "Value"          INT           NOT NULL,
    "Interest"       DECIMAL(5, 2) NOT NULL,
    "Credit_Type_Id" SMALLINT      NOT NULL REFERENCES "Credits_Types" ("Credit_Type_Id"),
    "Account_Id"     BIGINT        NOT NULL REFERENCES "Accounts" ("Account_Id"),
    PRIMARY KEY ("Credit_Id")
);

CREATE TABLE "Cards"
(
    "Card_Id"         BIGSERIAL NOT NULL,
    "Card_Number"     BIGINT    NOT NULL,
    "Expiration_Date" DATE      NOT NULL,
    "Customer_Id"     BIGINT    NOT NULL REFERENCES "Customers" ("Customer_Id"),
    "Card_Type_Id"    SMALLINT  NOT NULL REFERENCES "Cards_Types" ("Card_Type_Id"),
    PRIMARY KEY ("Card_Id")
);

CREATE TABLE "Transactions"
(
    "Transaction_Id"      BIGSERIAL NOT NULL,
    "Value"               BIGINT    NOT NULL,
    "Transaction_Date"    TIMESTAMP NOT NULL,
    "Transaction_Type_Id" SMALLINT  NOT NULL REFERENCES "Transactions_Types" ("Transaction_Type_Id"),
    "Account_Id_From"     BIGINT    NOT NULL REFERENCES "Accounts" ("Account_Id"),
    "Account_Id_To"       BIGINT    NOT NULL REFERENCES "Accounts" ("Account_Id"),
    "Card_Id_From"        BIGINT    NOT NULL REFERENCES "Cards" ("Card_Id"),
    "Card_Id_To"          BIGINT    NOT NULL REFERENCES "Cards" ("Card_Id"),
    PRIMARY KEY ("Transaction_Id"),
    CHECK ("Value" > 0)
);

CREATE TABLE "Customers_Accounts"
(
    "Customers_Accounts_Id" BIGSERIAL NOT NULL,
    "Account_Id"            BIGINT    NOT NULL REFERENCES "Accounts" ("Account_Id"),
    "Customer_Id"           BIGINT    NOT NULL REFERENCES "Customers" ("Customer_Id"),
    PRIMARY KEY ("Customers_Accounts_Id")
);

CREATE TABLE "Accounts_Cards"
(
    "Account_Card_Id" BIGSERIAL NOT NULL,
    "Card_Id"         BIGINT    NOT NULL REFERENCES "Cards" ("Card_Id"),
    "Account_Id"      BIGINT    NOT NULL REFERENCES "Accounts" ("Account_Id"),
    PRIMARY KEY ("Account_Card_Id")
);

