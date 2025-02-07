create table transaction_log
(
    log_id         bigserial
        primary key,
    transaction_id bigint                              not null
        references transactions,
    log_date       timestamp default CURRENT_TIMESTAMP not null,
    log_message    varchar(255)
);

alter table transaction_log
    owner to postgres;

INSERT INTO public.transaction_log (log_id, transaction_id, log_date, log_message) VALUES (11, 14, '2025-02-07 15:53:36.793134', 'Перевод 100.0 от 1111 к 2222');
INSERT INTO public.transaction_log (log_id, transaction_id, log_date, log_message) VALUES (12, 15, '2025-02-07 17:01:14.756645', 'Перевод 1000.0 от 1111 к 2222');
INSERT INTO public.transaction_log (log_id, transaction_id, log_date, log_message) VALUES (13, 16, '2025-02-07 17:36:18.089915', 'Перевод 1000.0 от 4444 к 3333');
