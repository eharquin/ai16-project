create table if not exists users
(
    id       int auto_increment primary key,
    name     varchar(255),
    mail     varchar(255),
    is_admin tinyint(1),
    password varchar(255)
);

create table if not exists channels
(
    id          int auto_increment primary key,
    owner_id    int,
    name        varchar(255),
    description varchar(255) null,
    date        varchar(255),
    duration    varchar(255),

    foreign key (owner_id) references users(id)
);

create table if not exists user_channel
(
    user_id    int,
    channel_id int,

    foreign key (user_id) references users(id),
    foreign key (channel_id) references channels(id)
);
