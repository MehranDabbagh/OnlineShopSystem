create table if not exists admins(id serial primary key ,username varchar(50),password varchar (50));
create table if not exists customer(id serial primary key ,firstname varchar (50),lastname varchar(50),
    username varchar(50),password varchar (50),nationalcode BIGINT ,email varchar (50),address varchar (50),phonenumber BIGINT);
create table if not exists category(id serial primary key,label varchar (50),parentcategory varchar (50));
create table if not exists products (id serial primary key,label varchar (50),price int ,category varchar(50));
create table if not exists cart(id serial primary key, address varchar (50),phonenumber BIGINT,done boolean,customerId integer,
    CONSTRAINT fk_customer
    FOREIGN KEY (customerId) REFERENCES customer(id));
create table if not exists cartProducts(id serial primary key ,cartId integer,productId integer,
constraint fk_customer foreign key (cartId) references cart(id) ,
foreign key (productId) references products(id));
