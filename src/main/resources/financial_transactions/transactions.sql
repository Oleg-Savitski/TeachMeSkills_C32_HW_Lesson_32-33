create table transactions
(
    transaction_id   bigserial
        primary key,
    account_number   varchar(45)                         not null
        references customer (account_number),
    transaction_date timestamp default CURRENT_TIMESTAMP not null,
    transaction_type varchar(10)
        constraint transactions_transaction_type_check
            check ((transaction_type)::text = ANY
                   ((ARRAY ['Deposit'::character varying, 'Withdrawal'::character varying, 'Transfer'::character varying])::text[])),
    amount           numeric(10, 2)                      not null
);

alter table transactions
    owner to postgres;

INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (1, '2222', '2025-02-07 14:07:04.926416', 'Transfer', 1000.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (2, '2222', '2025-02-07 14:11:34.123278', 'Transfer', 100.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (3, '1111', '2025-02-07 14:14:08.752531', 'Transfer', 1000.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (4, '2222', '2025-02-07 14:17:25.699540', 'Transfer', 50.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (5, '2222', '2025-02-07 14:28:11.157199', 'Transfer', 50.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (6, '1111', '2025-02-07 14:37:53.432185', 'Transfer', 100.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (7, '2222', '2025-02-07 14:40:35.083266', 'Transfer', 200.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (8, '1111', '2025-02-07 14:46:10.156118', 'Transfer', 100.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (9, '2222', '2025-02-07 14:56:02.377074', 'Transfer', 10000.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (10, '1111', '2025-02-07 14:58:41.423591', 'Transfer', 1000.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (11, '1111', '2025-02-07 15:06:10.016413', 'Transfer', 1000.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (12, '1111', '2025-02-07 15:07:17.469824', 'Transfer', 1000.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (13, '2222', '2025-02-07 15:11:38.354336', 'Transfer', 1000.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (14, '1111', '2025-02-07 15:53:36.792136', 'Transfer', 100.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (15, '1111', '2025-02-07 17:01:14.756645', 'Transfer', 1000.00);
INSERT INTO public.transactions (transaction_id, account_number, transaction_date, transaction_type, amount) VALUES (16, '4444', '2025-02-07 17:36:18.089915', 'Transfer', 1000.00);
