create table if not exists persons (
    name varchar(20) not null ,
    surname varchar(30) not null ,
    age int not null ,
    phone_number varchar(12),
    city_of_living varchar(30),
    primary key (name, surname, age)
    );

insert into persons (name, surname, age, phone_number, city_of_living)
VALUES ('Иван', 'Иванов', 25, '79614005530', 'MOSCOW'),
       ('Петр', 'Петров', 45, '79223334455', 'MOSCOW'),
       ('Сидор', 'Сидоров', 17, '79376584421', 'VOLGOGRAD'),
       ('Алексей', 'Алексеев', 55, '79326355784', 'Vladivostok'),
       ('Сергей', 'Сергеев', 33, '79216320122', 'St.Petersburg');