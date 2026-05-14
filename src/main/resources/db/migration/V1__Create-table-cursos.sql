CREATE table cursos(
    id bigint auto_increment not null,
    nome varchar(100) not null unique,
    periodo varchar(20),
    ativo tinyint not null default 1,

    primary key (id)
);


