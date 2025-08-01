alter table if exists tb_book
    add column if not exists user_id uuid;