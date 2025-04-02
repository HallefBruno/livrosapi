alter table if exists tb_author
    add column if not exists user_id uuid;