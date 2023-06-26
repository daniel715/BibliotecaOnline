DELIMITER $$
DROP PROCEDURE IF EXISTS updateCategories$$
CREATE PROCEDURE updateCategories(IN categoria varchar(45), categoriaId varchar(45))
BEGIN
DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
/*
SET @arreglo :=  (SELECT JSON_ARRAYAGG(JSON_OBJECT('categorias', categorias,'categoriasId', categoriasId)) FROM librocategorias);
*/
SELECT COUNT(*) FROM librocategorias INTO n;
SET @i=0;
WHILE ( @i<  (JSON_LENGTH(@arreglo) -1) )  DO 
	/*
	SET @update := JSON_REMOVE( JSON_EXTRACT(JSON_EXTRACT(@arreglo, '$[0]'), '$.categorias'), CONCAT('$[', categoria , ']')  );
    UPDATE librocategorias SET categorias =  @update WHERE idLibro = '516c946d-22a6-4688-afa8-4bc3e2ca35dd';
    SELECT @update;
	*/
	SET @categoriasUpdated := JSON_REMOVE( JSON_EXTRACT(JSON_EXTRACT(@arreglo, CONCAT('$[', @i, ']')) , '$.categorias'), CONCAT('$[',categoria,']'));
	UPDATE librocategorias SET categorias =  @categoriasUpdated WHERE idLibro = JSON_EXTRACT(JSON_EXTRACT(@arreglo, CONCAT('$[', @i, ']')) , '$.idLibro');
    SELECT @categoriasUpdated;
  SET @i = @i + 1;
END WHILE;
End;