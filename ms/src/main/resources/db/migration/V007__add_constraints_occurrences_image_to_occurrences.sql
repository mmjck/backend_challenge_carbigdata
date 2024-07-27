
--
ALTER TABLE tb_occurrences_images ADD COLUMN occurrence_id INT;


--
ALTER TABLE tb_occurrences_images ADD CONSTRAINT fk_occurrence FOREIGN KEY (occurrence_id) REFERENCES tb_occurrences(id);