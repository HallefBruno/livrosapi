create table IF NOT EXISTS tb_book (
    id uuid not null,
    date_publish date,
    genero varchar(50) check (genero in ('NARRATIVO','LIRICO','DRAMATICO','MEMORIAS','AUTOBIOGRAFIA','BIOGRAFIA','POESIA','SATIRA','HISTORIAS','FANFICTION')),
    isbn varchar(20),
    name varchar(255),
    price numeric(19,2),
    title varchar(50),
    book_id uuid,
    CONSTRAINT fk_book_author FOREIGN KEY (book_id) REFERENCES tb_author(id)
)