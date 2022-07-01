create table measurement
(
    id        serial
        constraint measurement_pk
            primary key,
    value     double precision not null,
    raining   boolean          not null,
    sensor_id integer
        constraint measurement_sensor_id_fk
            references sensor
            on delete cascade,
    made_at   timestamp        not null
);

alter table measurement
    owner to postgres;

INSERT INTO sensor_proj.measurement (id, value, raining, sensor_id, made_at) VALUES (3, -25.383104957700127, false, 4, '2022-06-24 14:39:25.314000');
INSERT INTO sensor_proj.measurement (id, value, raining, sensor_id, made_at) VALUES (4, 9.877205387756149, false, 4, '2022-06-24 14:39:25.973000');
INSERT INTO sensor_proj.measurement (id, value, raining, sensor_id, made_at) VALUES (5, -31.417645411845015, true, 4, '2022-06-24 14:39:25.993000');
INSERT INTO sensor_proj.measurement (id, value, raining, sensor_id, made_at) VALUES (6, 48.75619908877454, false, 4, '2022-06-24 14:39:26.012000');
INSERT INTO sensor_proj.measurement (id, value, raining, sensor_id, made_at) VALUES (7, -79.93172134182043, true, 4, '2022-06-24 14:39:26.028000');
INSERT INTO sensor_proj.measurement (id, value, raining, sensor_id, made_at) VALUES (8, -18.572723579601572, true, 4, '2022-06-26 20:12:30.738000');
INSERT INTO sensor_proj.measurement (id, value, raining, sensor_id, made_at) VALUES (9, 89.82518151176956, false, 4, '2022-06-26 20:12:30.803000');
INSERT INTO sensor_proj.measurement (id, value, raining, sensor_id, made_at) VALUES (10, -98.14683477607682, false, 4, '2022-06-26 20:12:30.825000');
INSERT INTO sensor_proj.measurement (id, value, raining, sensor_id, made_at) VALUES (11, 69.15963715594029, true, 4, '2022-06-26 20:12:30.846000');
INSERT INTO sensor_proj.measurement (id, value, raining, sensor_id, made_at) VALUES (12, 93.62542854421713, false, 4, '2022-06-26 20:12:30.864000');