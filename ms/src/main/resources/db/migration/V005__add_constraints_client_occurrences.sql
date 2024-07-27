

--
ALTER TABLE tb_occurrences ADD COLUMN client_id INT;


--
ALTER TABLE tb_occurrences ADD CONSTRAINT fk_clients FOREIGN KEY (client_id) REFERENCES tb_clients(id);