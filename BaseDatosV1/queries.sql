select * from autor
select * from categoria
select * from libro
select * from libro_has_categoria
select * from pedido


insert into Autor values ("1","Mario Vargas Llosa")
insert into Autor values ("2","Ricardo Palma")
insert into Autor values ("4","Jaime Baily")
insert into Autor values ("4","Martin Adan")
insert into Autor values ("5","Cesar Vallejo")
insert into Autor values ("6","J.K Rowling")
insert into Autor values ("7","Victor Hugo")
insert into Autor values ("8","Stephenie Meyer")

insert into Categoria values ("1","Terror")
insert into Categoria values ("2","Policial")
insert into Categoria values ("3","Ciencia Ficcion")
insert into Categoria values ("4","Filosofia")
insert into Categoria values ("5","Derecho")
insert into Categoria values ("6","Ciencia Ficcion")
insert into Categoria values ("7","Literatura")


insert into Libro values ("1", "Tradiciones Peruanas"  , "1870",20.00, "2","Este libro trata de diversos relatos ...", 0 , null)
insert into Libro values ("2", "La ciudad y los perros", "1990",40.00, "1","Este libro cuenta la historia de los alumnos del colegio militar Leoncio Prado ...", 0 , null)
insert into Libro values ("3", "Maria", "1990",40.00, "1","Este libro cuenta la historia de los alumnos del colegio militar Leoncio Prado ...", 0 , null)
insert into Libro values ("4", "Los perros hambrientos", "1990",40.00, "1","Este libro cuenta la historia de los alumnos del colegio militar Leoncio Prado ...", 0 , null)
insert into Libro values ("5", "La casa de papel", "1990",40.00, "1","Este libro cuenta la historia de los alumnos del colegio militar Leoncio Prado ...", 0 , null)



insert into libro_categoria values ("1","1")
insert into libro_categoria values ("2","2")
insert into libro_categoria values ("3","3")
insert into libro_categoria values ("4","4")
insert into libro_categoria values ("5","5")
insert into libro_categoria values ("2","1")
insert into libro_categoria values ("2","1")
insert into libro_categoria values ("3","1")
select * from libro 
select * from libro_categoria 


insert into Pedido values ("1", "Daniel", "939389455", "Av. Los ALisos 123", "1.0.0.3", "yape", "2023-06-22", 20, "Respuesta de pago correcta", "Cerrado")
select * from Pedido

















select from Libro where idLibro = "52613a8c-da4e-46b3-a383-88392c06020b"
select * from Libro where id = "cf8df209-917b-440e-809b-1a80ac5332fc"
select * from librocategorias where idLibro = "15594dbf-f5ab-48d2-945e-b3be56d195e7"
select * from libro where id = "9ef82524-c532-421a-802d-54c3756c5de5"

delete from libro_categoria where id_libro = "1";

delete from librocategorias where idLibro = "e817eb93-1718-4a53-8819-452b9a997ad7"
UPDATE  librocategorias SET categorias = '["Derecho"]'  WHERE idLibro = "4"
UPDATE  librocategorias SET categoriasId = '["1"]'  WHERE idLibro = "4"
call updateCategories("Derecho","1");
DELETE FROM categoria where id = "3";
select * from categoria

select * from autor where id in (select idLibro from librocategorias where categorias LIKE '%Derecho%')
update librocategorias set categoriasId = "[]",  categorias = "[]" where idLibro = "cf8df209-917b-440e-809b-1a80ac5332fc"
delete from Categoria where id = "7"
call Autor_get_by_id("1")

call Autor_save("3", "Cesar Vallejo")





