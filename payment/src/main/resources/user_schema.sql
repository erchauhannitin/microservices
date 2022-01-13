create table users(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    enabled boolean not null,
);

create table authorities(
    username varchar_ignorecase(50) not null primary key,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_user foreign key(username) references(username)
);

create unique index ix_auth+_username on authorities (username, authorities);
