ALTER TABLE loan ADD user_app_id int8;

ALTER TABLE public.loan ADD CONSTRAINT fkq2oere85npv33c3if0kxbn7tb FOREIGN KEY (user_app_id) REFERENCES user_app(id);