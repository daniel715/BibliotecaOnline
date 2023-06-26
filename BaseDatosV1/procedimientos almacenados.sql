DELIMITER $$
DROP PROCEDURE IF EXISTS Autor_update$$
CREATE PROCEDURE Autor_update(IN id VARCHAR(20), IN nombre VARCHAR(40))
BEGIN
update Autor set nombre = nombre where id_autor= id;
End;
call Autor_update("1","Mario");

DELIMITER $$
DROP PROCEDURE IF EXISTS Autor_get_by_nombre$$
CREATE PROCEDURE Autor_get_by_nombre(IN nombre VARCHAR(20))
BEGIN
SELECT * FROM autor WHERE nombre LIKE '%h%';
End;
call Autor_get_by_nombre("H");
SELECT * FROM autor WHERE nombre LIKE '%h%';

DELIMITER $$
DROP PROCEDURE IF EXISTS Autor_list$$
CREATE PROCEDURE Autor_list()
BEGIN
select * from autor;
End;
call Autor_list()

//save
DELIMITER $$
DROP PROCEDURE IF EXISTS Autor_save$$
CREATE PROCEDURE Autor_save(IN id VARCHAR(20), IN nombre VARCHAR(40))
BEGIN
insert into autor (id_autor, nombre) values (id, nombre);
End;


//delete
DELIMITER $$
DROP PROCEDURE IF EXISTS Autor_delete$$
CREATE PROCEDURE Autor_delete(IN id VARCHAR(20))
BEGIN
DELETE FROM autor where id_autor = id;
End;
select * from autor
call Autor_delete("3");



===================================================
CATEGORIAS SP

DELIMITER $$
DROP PROCEDURE IF EXISTS Categoria_find_by_id$$
CREATE PROCEDURE Categoria_find_by_idcategoria(IN id VARCHAR(20))
BEGIN
select * FROM categoria where id_categoria = id;
End;
select * from categoria;
call Categoria_find_by_id("1");


DELIMITER $$
DROP PROCEDURE IF EXISTS Categoria_list$$
CREATE PROCEDURE Categoria_list()
BEGIN
select * FROM categoria;
End;
select * from categoria;
categoriacall Categoria_list();


DELIMITER $$
DROP PROCEDURE IF EXISTS Categoria_save$$
CREATE PROCEDURE Categoria_save(IN id VARCHAR(20), IN nombre VARCHAR(40))
BEGIN
insert into categoria (id_categoria, nombre) values (id,nombre);
End;
select * from categoria;
call Categoria_save("3", "Filosofia");


DELIMITER $$
DROP PROCEDURE IF EXISTS Categoria_update$$
CREATE PROCEDURE Categoria_update(IN id VARCHAR(20), IN nombre VARCHAR(40))
BEGIN
update categoria set nombre = nombre where id_categoria = id;
End;
select * from categoria;
select * from autor
call Categoria_update("1", "Ciencia");

DELIMITER $$
DROP PROCEDURE IF EXISTS Categoria_delete$$
CREATE PROCEDURE Categoria_delete(IN id VARCHAR(20))
BEGIN
DELETE FROM categoria where id_categoria = id;
End;
select * from categoria
call Categoria_delete("4");

=========================================================
Libro SP

DELIMITER $$
DROP PROCEDURE IF EXISTS Libro_list$$
CREATE PROCEDURE Libro_list()
BEGIN
SELECT * FROM libro;
End;
select * from libro
call Libro_list();


DELIMITER $$
DROP PROCEDURE IF EXISTS Libro_get_by_id$$
CREATE PROCEDURE Libro_get_by_id(IN id VARCHAR(20))
BEGIN
select * from Libro where id_libro = id;
End;
select * from libro
call Libro_get_by_id("1");


DELIMITER $$
DROP PROCEDURE IF EXISTS Libro_save$$
CREATE PROCEDURE Libro_save(IN idLibro VARCHAR(20),IN nombre VARCHAR(40), IN year VARCHAR(10), IN precio float, IN id_autor VARCHAR(20),IN resumen VARCHAR(200), IN is_selled tinyint, IN id_pedido VARCHAR(20), IN categorias JSON)
BEGIN
DECLARE categoriaId VARCHAR(50);
DECLARE i INT DEFAULT 0;
insert into Libro (id_libro, nombre, year, precio, id_autor, resumen, is_selled, id_pedido) values (idLibro,nombre,year,precio,id_autor,resumen,is_selled,id_pedido);
WHILE i < JSON_LENGTH(categorias) DO
    SELECT JSON_EXTRACT(categorias,CONCAT('$[',i,']')) INTO categoriaId;
	select categoriaId;
    call libro_categoria_save(idLibro, categoriaId);
    SELECT i + 1 INTO i;
END WHILE;
End;


select * from libro
select * from libro_categoria
select * from categoria
delete from libro_categoria where id_libro in ('18')
call Libro_list();
call Libro_save("22" , "aaaa", "1920", 20, "9", "Este es el resumen ...", false, null, '["3","4"]');
insert into libro_categoria values ("10" , "6");
insert into libro_categoria values ("1" , "3");

DELIMITER $$
DROP PROCEDURE IF EXISTS Libro_update$$
CREATE PROCEDURE Libro_update(IN idLibro VARCHAR(20),IN nombre VARCHAR(40), IN year VARCHAR(10), IN precio float, IN id_autor VARCHAR(20),IN resumen VARCHAR(200), IN is_selled tinyint, IN id_pedido VARCHAR(20), IN categorias JSON)
BEGIN
update Libro set nombre = nombre, year = year, precio = precio, id_autor = id_autor ,resumen = resumen, is_selled = is_selled, id_pedido = id_pedido  where id_libro = idLibro;
call libro_categoria_update( idLibro , categorias);
end;
select * from libro
call Libro_update("1" , "a", "2020", 20, "1", "Este es el resumen ...", false, null);
	

DELIMITER $$
DROP PROCEDURE IF EXISTS Libro_delete$$
CREATE PROCEDURE Libro_delete(IN idLibro VARCHAR(20))
BEGIN
delete from libro_categoria where id_libro  = idLibro;
call libro_categoria_delete(idLibro);
End;
select * from libro
call Libro_delete("4");



DELIMITER $$
DROP PROCEDURE IF EXISTS buscar_libro_by_categoriaId$$
CREATE PROCEDURE buscar_libro_by_categoriaId(IN categoriaId VARCHAR(20))
BEGIN
select * from libro where id_libro in (select id_libro from libro_categoria where id_categoria = categoriaId);
End;
select * from libro
call buscar_libro_by_categoriaId("1");


=========================================================
libro_categoria SP
DELIMITER $$
DROP PROCEDURE IF EXISTS libro_categoria_list$$
CREATE PROCEDURE libro_categoria_list()
BEGIN
select * from libro_categoria;
End;
call libro_categoria_list()

DELIMITER $$
DROP PROCEDURE IF EXISTS libro_categoria_save$$
CREATE PROCEDURE libro_categoria_save( IN libroId VARCHAR(20),IN categoriaId VARCHAR(20))
BEGIN
insert into libro_categoria values (libroId , categoriaId);
End;
call libro_categoria_save()

DELIMITER $$
DROP PROCEDURE IF EXISTS libro_categoria_update$$
CREATE PROCEDURE libro_categoria_update( IN libroId VARCHAR(20),IN  categorias JSON)
BEGIN
DECLARE categoriaId VARCHAR(50);
DECLARE i INT DEFAULT 0;
delete from libro_categoria where id_libro = libroId;
WHILE i < JSON_LENGTH(categorias) DO
    SELECT JSON_EXTRACT(categorias,CONCAT('$[',i,']')) INTO categoriaId;
    insert into libro_categoria values (libroId , categoriaId);
    SELECT i + 1 INTO i;
END WHILE;
End;
call libro_categoria_update();

DELIMITER $$
DROP PROCEDURE IF EXISTS libro_categoria_delete$$
CREATE PROCEDURE libro_categoria_delete( IN libroId VARCHAR(20))
BEGIN
delete from libro_categoria where id_libro  = libroId;
End;







