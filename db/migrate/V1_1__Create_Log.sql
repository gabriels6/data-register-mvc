CREATE TABLE IF NOT EXISTS "logs" (
    "id" SERIAL PRIMARY KEY NOT NULL,
    "description" VARCHAR(100) NOT NULL,
    "date" VARCHAR(16) NOT NULL,
    "user_id" int not null,
    FOREIGN KEY ("user_id") REFERENCES "users"("id")
);