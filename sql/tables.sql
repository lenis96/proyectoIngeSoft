drop table if exists usuarios;
create table usuarios(
	usuario varchar(20),
	password char(33) not null,
	primary key(usuario)
);