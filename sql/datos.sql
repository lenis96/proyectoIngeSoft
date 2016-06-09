insert into usuarios values('lenis',SHA('prueba'),'R');
insert into usuarios values('carlos',SHA('carlitos'),'A');
insert into inmuebles (direccion,lugarReferencia,tamano,estrato,tipo,habitaciones,usuario,precio) values('cra 45#24-65','cerca centro comercial chipichape',120,5,'A',3,'lenis',480000);
insert into inmuebles (direccion,lugarReferencia,tamano,estrato,tipo,habitaciones,usuario,precio) values('calle 14N #34-50','cerca universidad Santiago de Cali',145,5,'A',4,'carlos',560000);
insert into inmuebles (direccion,lugarReferencia,tamano,estrato,tipo,habitaciones,usuario,precio) values('cra 34#5-60','',90,4,'A',3,'carlos',320000);
insert into inmuebles (direccion,lugarReferencia,tamano,estrato,tipo,habitaciones,usuario,precio) values('cra 101#34-78','',160,5,'C',3,'carlos',1200000);
insert into inmuebles (direccion,lugarReferencia,tamano,estrato,tipo,habitaciones,usuario,precio) values('cra 45#24-65','cerca al terminal',110,4,'A',2,'lenis',450000);

insert into inventarios (idInmueble) values (1);
insert into inventarios (idInmueble) values (2);

insert into espaciosDefault values(1,'sala','salas');
insert into espaciosDefault values(2,'cocina','cocinas');
insert into espaciosDefault values(3,'habiticion','habitaciones');
insert into espaciosDefault values(4,'baño',"baños");

insert into objetos (descripcion) values('bombilla');
insert into objetos (descripcion) values('puerta');

insert into objetosdefault values(1,1);
insert into objetosdefault values(1,2);
insert into objetosdefault values(1,3);
insert into objetosdefault values(1,4);
insert into objetosdefault values(2,1);
insert into objetosdefault values(2,2);
insert into objetosdefault values(2,3);
insert into objetosdefault values(2,4);

insert into materiales (descripcion) values ('vidrio')
insert into materiales (descripcion) values ('madera cedro')

insert into materialesObjetos values(1,1);
insert into materialesObjetos values(2,2);