CREATE table order_service (
    id serial not null,
    client_id bigint not null,
    description text not null,
    price decimal(10, 2) not null,
    status varchar(20) not null,
    open_date timestamp without time zone default (now()),
    end_date timestamp without time zone,

    primary key(id)
);

ALTER TABLE order_service add CONSTRAINT fk_order_service_client
FOREIGN KEY (client_id) references client (id);
