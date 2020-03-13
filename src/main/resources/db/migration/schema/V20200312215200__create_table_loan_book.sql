CREATE TABLE public.loan_book (
	book_id int8 NOT NULL,
	loan_id int8 NOT NULL
);


-- public.loan_book foreign keys

ALTER TABLE public.loan_book ADD CONSTRAINT fk6msx7vl5qwvpv5clp79rplss8 FOREIGN KEY (book_id) REFERENCES loan(id);
ALTER TABLE public.loan_book ADD CONSTRAINT fka1o2xwo6026nxl1u65cwh9gkc FOREIGN KEY (loan_id) REFERENCES book(id);