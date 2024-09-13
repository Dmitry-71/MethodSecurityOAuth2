create table if not exists persons
(
    name           varchar(20) not null,
    surname        varchar(30) not null,
    age            int         not null,
    phone_number   varchar(12),
    city_of_living varchar(30),
    primary key (name, surname, age)
    );