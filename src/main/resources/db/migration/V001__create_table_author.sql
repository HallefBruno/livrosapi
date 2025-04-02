create table IF NOT EXISTS tb_author (
    id uuid not null,
    date_birth date,
    name varchar(255),
    nationality varchar(255),
    primary key (id)
)