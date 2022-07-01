create table sensor
(
    id   serial
        constraint sensor_pkey
            primary key,
    name varchar not null
);

alter table sensor
    owner to postgres;

INSERT INTO sensor_proj.sensor (id, name) VALUES (3, 'test 777');
INSERT INTO sensor_proj.sensor (id, name) VALUES (4, 'test 4');