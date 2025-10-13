create table users
(
    id       uuid primary key,
    username varchar(255) not null unique,
    password varchar(255) not null,
    email    varchar(255) not null,
    picture  varchar(255) not null
);

create table tokens
(
    access_token  varchar(255) primary key,
    refresh_token varchar(255) not null,
    expired_at    timestamp    not null,
    metadata      jsonb
);

create table chat
(
    id          uuid primary key,
    type        int          not null,
    name        varchar(255) not null,
    picture     varchar(255) not null,
    description varchar(512),
    link_name   varchar(512)
)