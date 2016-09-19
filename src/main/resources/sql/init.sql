create sequence if not exists account_seq
increment by 1
start with 1;

create table if not exists account(
    id varchar(36) primary key,
    username varchar(36) unique,
    password varchar(256),
    admin boolean,
    enabled boolean,
    updated_at timestamp,
    created_at timestamp
);

create table if not exists article(
    article_id varchar(36) primary key,
    file blob not null,
    description varchar(100) not null,
    filename varchar(256) not null,
    content_type varchar(100) not null,
    updated_at timestamp,
    created_at timestamp
);

create sequence if not exists todo_seq
increment by 1
start with 1;

create table if not exists todo(
    todo_id varchar(36) primary key,
    todo_title varchar(30),
    todo_category varchar(2),
    todo_detail varchar(100),
    finished boolean, 
    updated_at timestamp,
    created_at timestamp
);

create sequence if not exists todo_category_seq
increment by 1
start with 1;


create table if not exists todo_category(
    todo_category_id varchar(2) primary key,
    todo_category_name varchar(50) not null,
    updated_at timestamp not null,
    created_at timestamp not null
);

create sequence if not exists item_seq
increment by 1
start with 1;

create table if not exists item(
    item_code varchar(36) primary key,
    item_name varchar(30),
    item_category varchar(2),
    finished boolean, 
    updated_at timestamp,
    created_at timestamp
);

create sequence if not exists item_category_seq
increment by 1
start with 1;


create table if not exists item_category(
    item_category_id varchar(2) primary key,
    item_category_name varchar(50) not null,
    updated_at timestamp not null,
    created_at timestamp not null
);

create sequence if not exists cart_seq
increment by 1
start with 1;

create sequence if not exists cart_item_seq
increment by 1
start with 1;

create table if not exists cart(
    cart_id integer not null,
    user_id varchar(36) not null,
    updated_at timestamp,
    created_at timestamp,
    primary key (cart_id, user_id)
);

create table if not exists cart_item(
    cart_id integer not null,
    user_id varchar(36) not null,
    item_id integer not null,
    item_code varchar(30) not null,
    quantity integer not null,
    updated_at timestamp,
    created_at timestamp,
    primary key (cart_id, user_id, item_id, item_code)
);

create sequence if not exists order_seq
increment by 1
start with 1;

create sequence if not exists order_item_seq
increment by 1
start with 1;

create table if not exists orders(
    order_id integer not null,
    user_id varchar(36) not null,
    updated_at timestamp,
    created_at timestamp,
    primary key (order_id, user_id)
);

create table if not exists order_items(
    order_id integer not null,
    user_id varchar(36) not null,
    item_id integer not null,
    item_code varchar(30) not null,
    quantity integer not null,
    updated_at timestamp,
    created_at timestamp,
    primary key (order_id, user_id, item_id, item_code)
);

-- todo list
insert into todo (todo_id, todo_title, todo_category, todo_detail, finished, updated_at, created_at) values (todo_seq.nextval, 'init_todo', '1', 'init_detail', false, '2016-09-18 01:16:12.643', '2016-09-17 01:16:12.643');
insert into todo (todo_id, todo_title, todo_category, todo_detail, finished, updated_at, created_at) values (todo_seq.nextval, 'init_todo', '2', 'init_detail', false, '2016-09-18 01:16:12.643', '2016-09-17 01:16:12.643');
insert into todo (todo_id, todo_title, todo_category, todo_detail, finished, updated_at, created_at) values (todo_seq.nextval, 'init_todo', '3', 'init_detail', false, '2016-09-18 01:16:12.643', '2016-09-17 01:16:12.643');
insert into todo (todo_id, todo_title, todo_category, todo_detail, finished, updated_at, created_at) values (todo_seq.nextval, 'init_todo', '4', 'init_detail', true, '2016-09-18 01:16:12.643', '2016-09-17 01:16:12.643');
insert into todo (todo_id, todo_title, todo_category, todo_detail, finished, updated_at, created_at) values (todo_seq.nextval, 'init_todo', '1', 'init_detail', false, '2016-09-18 01:16:12.643', '2016-09-17 01:16:12.643');
insert into todo (todo_id, todo_title, todo_category, todo_detail, finished, updated_at, created_at) values (todo_seq.nextval, 'init_todo', '2', 'init_detail', false, '2016-09-18 01:16:12.643', '2016-09-17 01:16:12.643');

-- todo category
insert into todo_category (todo_category_id, todo_category_name, updated_at, created_at) values (todo_category_seq.nextval, 'business', current_timestamp, current_timestamp);
insert into todo_category (todo_category_id, todo_category_name, updated_at, created_at) values (todo_category_seq.nextval, 'shopping', current_timestamp, current_timestamp);
insert into todo_category (todo_category_id, todo_category_name, updated_at, created_at) values (todo_category_seq.nextval, 'books', current_timestamp, current_timestamp);
insert into todo_category (todo_category_id, todo_category_name, updated_at, created_at) values (todo_category_seq.nextval, 'others', current_timestamp, current_timestamp);

-- item list
insert into item (item_code, item_name, item_category, finished, updated_at, created_at) values (item_seq.nextval, 'item_1', '1', false, current_timestamp, current_timestamp);
insert into item (item_code, item_name, item_category, finished, updated_at, created_at) values (item_seq.nextval, 'item_2', '2', false, current_timestamp, current_timestamp);
insert into item (item_code, item_name, item_category, finished, updated_at, created_at) values (item_seq.nextval, 'item_3', '3', false, current_timestamp, current_timestamp);
insert into item (item_code, item_name, item_category, finished, updated_at, created_at) values (item_seq.nextval, 'item_4', '2', false, current_timestamp, current_timestamp);
insert into item (item_code, item_name, item_category, finished, updated_at, created_at) values (item_seq.nextval, 'item_5', '3', false, current_timestamp, current_timestamp);

-- item category
insert into item_category (item_category_id, item_category_name, updated_at, created_at) values (item_category_seq.nextval, 'books', current_timestamp, current_timestamp);
insert into item_category (item_category_id, item_category_name, updated_at, created_at) values (item_category_seq.nextval, '家電', current_timestamp, current_timestamp);
insert into item_category (item_category_id, item_category_name, updated_at, created_at) values (item_category_seq.nextval, 'インテリア', current_timestamp, current_timestamp);





