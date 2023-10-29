create table auto_parts
(
    id              bigserial,
    name            varchar(256),
    serial_number   varchar(512),
    manufacturer_id bigint,
    weight          double precision,

    primary key (id)
);

create table auto_parts_compatibility_mapping
(
    part_id            bigint references auto_parts (id),
    compatible_part_id bigint references auto_parts (id),

    primary key (part_id, compatible_part_id)
)