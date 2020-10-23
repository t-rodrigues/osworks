CREATE TABLE comments (
    id serial not null,
    order_service_id bigint not null,
    description text not null,
    date timestamp not null,

    primary key (id)
);

ALTER TABLE comments ADD CONSTRAINT fk_comments_order_service
FOREIGN KEY (order_service_id) REFERENCES order_service(id);
