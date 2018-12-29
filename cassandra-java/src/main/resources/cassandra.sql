use mycas;
create table student(
    id int,
    address text,
    name text,
    age int,
    height int,
    primary key(id,address,name)
);

insert into student(id,address,name,age,height) values(1,'guangdong','lixiao',32,172);