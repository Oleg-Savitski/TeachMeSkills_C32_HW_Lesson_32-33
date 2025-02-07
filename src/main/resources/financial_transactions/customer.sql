create table customer
(
    id             bigserial
        primary key,
    account_number varchar(45) not null
        unique,
    balance        numeric(10, 2) default 0.00,
    first_name     varchar(45) not null,
    last_name      varchar(45) not null,
    created_at     timestamp      default CURRENT_TIMESTAMP
);

alter table customer
    owner to postgres;

INSERT INTO public.customer (id, account_number, balance, first_name, last_name, created_at) VALUES (1, '1111', 9100.00, 'Oleg', 'Savitski', '2025-02-07 14:06:15.351347');
INSERT INTO public.customer (id, account_number, balance, first_name, last_name, created_at) VALUES (2, '2222', -6100.00, 'Sandra', 'Savitskaya', '2025-02-07 14:06:40.458973');
INSERT INTO public.customer (id, account_number, balance, first_name, last_name, created_at) VALUES (4, '4444', 29000.00, 'Oleg', 'Savage', '2025-02-07 17:36:03.841814');
INSERT INTO public.customer (id, account_number, balance, first_name, last_name, created_at) VALUES (3, '3333', 4000.00, 'Oleg', 'Sava', '2025-02-07 17:35:04.654990');
INSERT INTO public.customer (id, account_number, balance, first_name, last_name, created_at) VALUES (5, '2222-3456-5443-2222', 100000.00, 'Alex', 'Rubkovski', '2025-02-07 17:55:33.354778');
