drop table product_category;
drop table product;

create table product_category (
	category_no char(1),
	category_name varchar2(30) not null,
	constraint product_category_pk primary key(category_no)
);

create table product (
	prod_no varchar2(5),
	prod_name varchar2(30) not null,
	prod_price number(5) not null,
	prod_detail varchar2(50),
	prod_category char(1) not null,
	constraint product_pk primary key(prod_no),
	constraint product_chk check(prod_price >= 0),
	constraint product_fk foreign key(prod_category) references product_category(category_no)
);


insert into product_category (category_no, category_name)
values ('D', '음료');
insert into product_category (category_no, category_name)
values ('F', '음식');
insert into product_category (category_no, category_name)
values ('G', '상품');


insert into product (prod_no, prod_name, prod_price, prod_detail, prod_category)
values ('001', '아메리카노', 2500, '커피의 한 종류', 'D');
insert into product (prod_no, prod_name, prod_price, prod_detail, prod_category)
values ('002', '아이스 아메리카노', 2500, '시원함', 'D');
insert into product (prod_no, prod_name, prod_price, prod_detail, prod_category)
values ('003', '라떼', 3000, '우유가 들어갔음', 'D');
insert into product (prod_no, prod_name, prod_price, prod_detail, prod_category)
values ('004', '치즈케이크', 4000, '한 끼 식사', 'F');



drop table order_info;
drop table order_detail;

create table order_info (
	order_no number not null,
	order_id varchar2(16) not null,
	order_time date default sysdate,
	constraint order_info_pk primary key(order_no),
	constraint order_info_fk foreign key(order_id) references customer(id)
);

create table order_detail (
	order_no number not null,
	order_prod_no varchar2(5) not null,
	order_quantity number(3) not null,
	constraint order_detail_pk primary key(order_no, order_prod_no),
	constraint order_detail_no_fk foreign key(order_no) references order_info(order_no),
	constraint order_detail_prod_no_fk foreign key(order_prod_no) references product(prod_no)
);

drop sequence order_seq;

create sequence order_seq;


insert into order_info (order_no, order_id)
values (order_seq.nextVal, 'qwe');


insert into order_detail (order_no, order_prod_no, order_quantity)
values (order_seq.currVal, '003', 1);
insert into order_detail (order_no, order_prod_no, order_quantity)
values (order_seq.currVal, '002', 3);


