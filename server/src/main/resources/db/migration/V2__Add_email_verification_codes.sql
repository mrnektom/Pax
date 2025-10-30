create table email_verification_codes
(
    id    uuid primary key,
    email varchar(255) not null,
    code  varchar(255) not null,
    type  int          not null
)