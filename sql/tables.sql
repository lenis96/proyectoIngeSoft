drop table if exists usuarios;
create table usuarios(
	usuario varchar(20) primary key,
	password char(40) not null,
	tipo char not null
	#primary key(usuario)
);