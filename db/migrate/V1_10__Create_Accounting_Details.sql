CREATE TABLE "accounting_details" (
    "id" SERIAL PRIMARY KEY NOT NULL,
    "name" VARCHAR(100) NOT NULL,
    "value" DECIMAL(10,2) NOT NULL,
    "accounting_id" INTEGER NOT NULL,
    FOREIGN KEY ("accounting_id") REFERENCES "accountings"("id")
);