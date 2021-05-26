drop table t_order_item;
drop table t_order;
drop table t_cart;
drop table t_book_picture;
drop table t_book;
drop table t_book_type;
drop table t_custom;
drop table t_account;


create table t_account(
username varchar(20) primary key,
password varchar(20) not null,
usertype int not null check(usertype between 0 and 1)
);

create table t_custom(
username varchar(20) primary key,
name varchar(50) not null,
nickname varchar(20) not null,
phone varchar(20) not null,
address varchar(50) not null,
registerdate date not null,
photo varchar(50) not null,
foreign key (username) references t_account(username)
);

create table t_book_type(
id int auto_increment primary key,
name varchar(20) not null,
detail varchar(100)
);

create table t_book(
id int auto_increment primary key,
name varchar(50) not null,
price double not null check(price >= 0),
press varchar(20) not null,
author varchar(50) not null,
publishdate date not null,
tid  int not null,
total int not null check(total>=0) default 0, -- MySQL5.7版本不支持检查约束与，默认约束写同一行
detail varchar(200),
ISBN char(13) not null unique,
foreign key (tid) references t_book_type(id)
);

create table t_order(
id int auto_increment primary key,
username varchar(20) not null,
orderdate date not null,
paydate date,
status int not null check(status between 0 and 5) ,-- 未付款、已付款、发货中、已签收、订单异常、已退款
foreign key (username) references t_custom(username)
);



create table t_cart(
id int auto_increment primary key,
username varchar(20) not null,
bid int not null,
total int not null check(total>0) default 0 ,
adddate date not null,
foreign key (bid) references t_book(id),
foreign key (username) references t_order(username)
);

 
create table t_book_picture(
id int auto_increment primary key,
bid int not null,
path varchar(50) not null,
pictureorder double not null,
foreign key (bid) references t_book(id)
);


create table t_order_item(
id int auto_increment primary key,
oid int not null,
bid int not null,
total int not null check(total>0) default 1,
price double not null check(price>0),
foreign key (oid) references t_order(id),
foreign key (bid) references t_book(id)
);