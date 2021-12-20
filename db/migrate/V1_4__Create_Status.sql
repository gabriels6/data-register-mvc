CREATE TABLE "status" (
    "id" SERIAL PRIMARY KEY NOT NULL,
    "name" VARCHAR(40) NOT NULL
);

INSERT INTO "status" ("name") VALUES
('Iniciado'),
('Em desenvolvimento'),
('Validado'),
('Com erro'),
('Necessita Melhorias'),
('Sem solução definita (não mergear)');