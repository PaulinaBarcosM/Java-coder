INSERT INTO CLIENT (id, name, lastname, dni, birth)
VALUES
(1, 'Marcelo', 'Acosta', 123456789, '2004-04-08'),
(2, 'Santiago', 'Schmidt', 234567891, '1974-09-18'),
(3, 'Carlos', 'Dupont', 345678912, '1999-07-24'),
(4, 'Lorena', 'Beltran', 456789123, '2000-09-30');

INSERT INTO ADDRESS (id, street, number, floor, apartment, cp, province, city)
VALUES
(1, 'Santiago del Estero', 3054, 0, 'none', 3000, 'Santa Fe', 'Santo Tomé' ),
(2, 'Castelli', 465, 3, '7E', 4670, 'Buenos Aires', 'Palermo Soho'),
(3, 'San Martin', 2515, 7, '18F', 4001 ,'Buenos Aires', 'Tigre'),
(4, 'Sin registrar', 0, 0, 'none', 0, 'none', 'none');

INSERT INTO PRODUCT (id, description, stock, price, colors, sizes)
VALUES
('P001', 'Bermuda Clothes', 10, 79999, 'AZUL-NEGRO-OXIDO', 'S-M-L-XL'),
('P002', 'Camisa con rayas azul', 47, 57000, 'RAYAS FINITAS-RAYAS GRUESAS', 'TALLE UNICO'),
('P003', 'Short con rayas azul', 35, 38900, 'RAYAS FINITAS-RAYAS GRUESAS', 'S-M-L');

INSERT INTO SALE (sale_id, fecha, product_id, amount, total)
VALUES
(39, '2024-10-01', 'P001', 2, 29.99),
(40, '2024-10-02', 'P002', 1, 15.50),
(43, '2024-10-03', 'P003', 3, 45.00);
