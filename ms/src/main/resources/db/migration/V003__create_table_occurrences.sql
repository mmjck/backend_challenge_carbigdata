
CREATE TYPE OccurrencesStatus AS ENUM ('ACTIVE', 'INACTIVE', 'PENDING');

CREATE TABLE IF NOT EXISTS tb_occurrences (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status OccurrencesStatus NOT NULL
);