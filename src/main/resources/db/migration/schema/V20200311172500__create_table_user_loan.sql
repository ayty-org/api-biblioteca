CREATE TABLE public.user_loan (
	user_app_id int8 NOT NULL,
loan_id int8 NOT NULL
);


-- public.user_loan foreign keys

ALTER TABLE user_loan ADD CONSTRAINT fk2smxk6cl6w7e4cflbllnnep8o FOREIGN KEY (user_app_id) REFERENCES user_app(id);
ALTER TABLE user_loan ADD CONSTRAINT fkaor37c4wjm37v84huo5ymwusd FOREIGN KEY (loan_id) REFERENCES loan(id);