alter table if exists tb_book
    add column if not exists author_id uuid;