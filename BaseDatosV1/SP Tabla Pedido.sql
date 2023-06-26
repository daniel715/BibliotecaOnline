Pedido SP
=========================================================
DELIMITER $$
DROP PROCEDURE IF EXISTS Pedido_list$$
CREATE PROCEDURE Pedido_list()
BEGIN
select * from pedido;
End;
call Pedido_list();

DELIMITER $$
DROP PROCEDURE IF EXISTS Pedido_save$$
CREATE PROCEDURE Pedido_save(IN idPedido VARCHAR(20),IN nombreComprador VARCHAR(40), IN telefono VARCHAR(15), IN direccionEntrega varchar(50), IN direccionIp VARCHAR(20),IN appPago VARCHAR(15), IN fechaPedido Date, IN valorCompra float, IN respuestaPago varchar(100), IN status varchar(20))
BEGIN
insert into Pedido (id_pedido,nombre_comprador,telefono_comprador,direccion_entrega, direccion_ip, app_pago, fecha_pedido, valor_compra, respuesta_pago, status) values (idPedido, nombreComprador, telefono, direccionEntrega, direccionIp, appPago, fechaPedido, valorCompra, respuestaPago, status);
End;

DELIMITER $$
DROP PROCEDURE IF EXISTS Pedido_update$$
CREATE PROCEDURE Pedido_update(IN idPedido VARCHAR(20),IN nombreComprador VARCHAR(40), IN telefono VARCHAR(15), IN direccionEntrega varchar(50), IN direccionIp VARCHAR(20),IN appPago VARCHAR(15), IN fechaPedido Date, IN valorCompra float, IN respuestaPago varchar(100), IN status varchar(20))
BEGIN
update Pedido set nombre_comprador = nombreComprador, telefono_comprador = telefono, direccion_entrega = direccionEntrega , direccion_ip = direccionIp , app_pago= appPago, fecha_pedido = fechaPedido, valor_compra = valorCompra, respuesta_pago = respuestaPago, status = status where id_pedido = idPedido;
end;
select * from libro
call libro_update("1" , "a", "2020", 20, "1", "Este es el resumen ...", false, null);


DELIMITER $$
DROP PROCEDURE IF EXISTS Pedido_delete$$
CREATE PROCEDURE Pedido_delete(IN idPedido VARCHAR(20))
BEGIN
delete from Pedido where id_pedido  = idPedido;
End;
select * from libro
call Libro_delete("4");


DELIMITER $$
DROP PROCEDURE IF EXISTS Pedido_find_by_id$$
CREATE PROCEDURE Pedido_find_by_id(IN idPedido VARCHAR(20))
BEGIN
select * from Pedido where id_pedido = idPedido;
End;
select * from Pedido
call Pedido_find_by_id("4");