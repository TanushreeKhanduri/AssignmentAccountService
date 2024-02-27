CREATE TABLE customer (
	id bigserial NOT NULL PRIMARY KEY,
	first_name text NOT NULL,
	last_name text NOT NULL,
	email text NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL
);

CREATE TABLE bank_account (
	id bigserial NOT NULL PRIMARY KEY,
	iban text NULL,
	current_balance decimal NOT NULL,
	customer_id bigint NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL
);

ALTER TABLE bank_account
    ADD CONSTRAINT fk_bank_account_customer_id FOREIGN KEY (customer_id) REFERENCES customer(id);