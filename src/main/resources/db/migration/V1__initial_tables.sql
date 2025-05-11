create table if not exists vehicle
(
    id           bigserial primary key,
    plate_number varchar(250) not null,
    name         varchar(250) not null,
    type         varchar(250) not null
);

create table if not exists gps_log
(
    id              bigserial primary key,
    vehicle_id      bigint           not null,
    latitude        double precision not null,
    longitude       double precision not null,
    speed           integer          not null,
    time_stamp      timestamptz      not null,
    speed_violation boolean          not null,
    foreign key (vehicle_id) references vehicle (id)
);

CREATE INDEX idx_gps_log_vehicle_id_time_stamp
    ON public.gps_log (vehicle_id, time_stamp);
