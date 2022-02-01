CREATE TABLE "accountings" (
    "id" SERIAL PRIMARY KEY NOT NULL,
    "date" VARCHAR(16) NOT NULL,
    "description" VARCHAR(255) NOT NULL,
    "user_id" INTEGER NOT NULL,
    "type_id" INTEGER NOT NULL,
    "period_id" INTEGER NOT NULL,
    FOREIGN KEY ("user_id") REFERENCES "users"("id"),
    FOREIGN KEY ("type_id") REFERENCES "accounting_types"("id"),
    FOREIGN KEY ("period_id") REFERENCES "periods"("id")
);