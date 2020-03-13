--CREATE TABLE public.book_loan (
--	loan_id int8 NOT NULL,
--book_id int8 NOT NULL
--);


-- public.book_loan foreign keys
--ALTER TABLE book DROP COLUMN loan_id;
--ALTER TABLE book_loan ADD CONSTRAINT fkacqrmnstaxmdmuq03sw9amepw FOREIGN KEY (loan_id) REFERENCES book(id);
--ALTER TABLE book_loan ADD CONSTRAINT fki9cxu5x1aa73iuvkp4gqofjoc FOREIGN KEY (book_id) REFERENCES loan(id);