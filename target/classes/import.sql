INSERT INTO categorias(description,state) VALUES("Lacteos",true);
INSERT INTO categorias(description,state) VALUES("Golosinas",true);
INSERT INTO categorias(description,state) VALUES("Menestras",true);

INSERT INTO productos(name, id_categoria, codigo_barras, precio_venta, stock, state) VALUES("Yogurt", 1, "123456789", 7.20, 50, true);
INSERT INTO productos(name, id_categoria, codigo_barras, precio_venta, stock, state) VALUES("Leche Gloria", 1, "521430069", 5.50, 150, true);
INSERT INTO productos(name, id_categoria, codigo_barras, precio_venta, stock, state) VALUES("BomBom", 2, "300241387", 0.50, 300, true);
INSERT INTO productos(name, id_categoria, codigo_barras, precio_venta, stock, state) VALUES("Panamito", 3, "587466203", 5.50, 200, true);
INSERT INTO productos(name, id_categoria, codigo_barras, precio_venta, stock, state) VALUES("Lentejas", 3, "996874021", 1.20, 300, true);

INSERT INTO cliente(dni,name,last_name,phone,email) VALUES(25410036,"Pedro","Porro",935210822,"p.porro@gmail.com");
INSERT INTO cliente(dni,name,last_name,phone,email) VALUES(75953875,"Ruth","Ortiz",963577441,"r.ort@gmail.com");
INSERT INTO cliente(dni,name,last_name,phone,email) VALUES(85512003,"Diego","Cornejo",965233007,"d.cornejo@gmail.com");
INSERT INTO cliente(dni,name,last_name,phone,email) VALUES(09672583,"Jairo","Samir",147258369,"jaro@gmail.com");

INSERT INTO compras(fecha, state, id_cliente, comentario, medio_pago) VALUES('2024-05-20',true,1,"Comentario1","Efectivo");
INSERT INTO compras(fecha, state, id_cliente, comentario, medio_pago) VALUES('2024-09-21',true,2,"Comentario2","Tarjeta de crédito");
INSERT INTO compras(fecha, state, id_cliente, comentario, medio_pago) VALUES('2024-01-01',true,3,"Comentario3","Efectivo");

INSERT INTO compras_productos(id_compra, id_producto, cantidad, estado, total) VALUES(1,1,5,true,36);
INSERT INTO compras_productos(id_compra, id_producto, cantidad, estado, total) VALUES(1,3,2,true,1);
INSERT INTO compras_productos(id_compra, id_producto, cantidad, estado, total) VALUES(1,2,5,true,27.5);

INSERT INTO compras_productos(id_compra, id_producto, cantidad, estado, total) VALUES(2,1,1,true,7.2);
INSERT INTO compras_productos(id_compra, id_producto, cantidad, estado, total) VALUES(2,5,2,true,6);

INSERT INTO compras_productos(id_compra, id_producto, cantidad, estado, total) VALUES(3,3,1,true,0.5);

INSERT INTO usuario(username,password) VALUES("jairo","$2a$10$SOPTNagYeJJuW4EffCMJvusiTzeskyq2pXdF4.j0kMbxGXic8QhpO");
INSERT INTO rol(rol_name) VALUES("ADMIN");
INSERT INTO usuario_rol(id_rol,id_usuario) VALUES(1,1);