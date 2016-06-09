drop table if exists materialesObjetos;
drop table if exists objetosEspacio;
drop table if exists materiales;
drop table if exists espaciosInventario;
drop table if exists objetosDefault;
drop table if exists objetos;
drop table if exists espaciosDefault;
drop table if exists inventarios;
drop table if exists inmuebles;
drop table if exists usuarios;
create table usuarios(
	usuario varchar(20) primary key,
	password char(40) not null,
	tipo char not null
	#primary key(usuario)
);
create table inmuebles(
	id int auto_increment primary key,
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
create table inventarios(
	id int auto_increment primary key,
	idInmueble int,
	aprovado boolean default false,
	foreign key (idInmueble) references inmuebles(id)
);
create table espaciosDefault(
	id int primary key,
	descripcion varchar(20),
    prural varchar(20)
);
create table objetos(
	id int auto_increment primary key,
	descripcion varchar(20)
);
create table objetosDefault(
	idObjeto int,
	idEspacio int,
	foreign key (idObjeto) references objetos(id),
	foreign key (idEspacio) references espaciosDefault(id),
	primary key (idObjeto,idEspacio)
);
create table espaciosInventario(
	id int auto_increment primary key,
	idEspacio int,
	idInventario int,
	instancia int,
	foreign key (idEspacio) references espaciosDefault(id),
	foreign key (idInventario) references inventarios(id) 
);
create table materiales(
	id int auto_increment primary key,
	descripcion varchar(20)
);
create table objetosEspacio(
	idEspacioInventario int,
	idObjeto int,
	numero int,
	calificacion char,
	observacion varchar(50),
	idMaterial int,
	foreign key (idEspacioInventario) references espaciosInventario(id),
	foreign key (idMaterial) references materiales(id),
	foreign key (idObjeto) references objetos(id)
);
create table materialesObjetos(
	idMaterial int,
	idObjeto int,
	foreign key (idMaterial) references materiales(id),
	foreign key (idObjeto) references objetos(id)
);