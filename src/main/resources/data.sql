INSERT INTO public.customer(id, first_name, last_name,created_at, updated_at)
VALUES
(1,'NoBankAccount', 'Surname1',current_timestamp,current_timestamp),
(2,'HasBankAccount', 'Debit',current_timestamp,current_timestamp),
(3,'HasBankAccount', 'Credit',current_timestamp,current_timestamp),
(4,'HasNoLimit', 'Debit',current_timestamp,current_timestamp),
(5,'HasNoLimit', 'Credit',current_timestamp,current_timestamp);

INSERT INTO public.bank_account(id, customer_id, iban, current_balance,created_at, updated_at)
VALUES
(1,2, 'IBAN1', 100.0 , current_timestamp,current_timestamp),
(2,3, null, 100.0 , current_timestamp,current_timestamp),
(3,4, 'IBAN1', 5.0 , current_timestamp,current_timestamp),
(4,5, null, 5.0 , current_timestamp,current_timestamp);