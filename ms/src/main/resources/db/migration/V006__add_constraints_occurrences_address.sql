-- 
ALTER TABLE tb_occurrences ADD COLUMN address_id INT;

-- 
ALTER TABLE tb_occurrences ADD CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES tb_address(id);
