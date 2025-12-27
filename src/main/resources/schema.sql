create table shop (
                      id bigint auto_increment primary key,
                      identifier varchar(255) not null,
                      status varchar(50) not null,
                      date_shop date
);

create table shop_item (
                           id bigint auto_increment primary key,
                           product_identifier varchar(100) not null,
                           amount int not null,
                           price decimal(10,2) not null,
                           shop_id bigint,
                           constraint fk_shop_item_shop
                               foreign key (shop_id) references shop(id)
);