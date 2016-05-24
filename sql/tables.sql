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
	direccion varchar(40) not null,
	lugarReferencia varchar(40),
	tamano int,
	estrato int,
	tipo char,
	habitaciones int,
	idUsuario varchar(20),
	precio int not null,
	check (estrato>=0 and estrato<=6),
	foreign key (idUsuario) references usuarios(usuario)
);