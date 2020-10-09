CREATE OR REPLACE VIEW cards_types AS
    SELECT "Card_Type_Id" AS "id",
           "Card_Type_Name" AS "type"
FROM "Cards_Types";

SELECT * FROM cards_types;