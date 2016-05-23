drop table if exists usuarios;
drop table if exists inmuebles;
create table usuarios(
	usuario varchar(20) primary key,
	password char(40) not null,
	tipo char not null
	#primary key(usuario)
);
create table inmuebles(
	id serial primary key,
	direccion varchar(50) not null,
	precio int not null,
	otro varchar(50)
);