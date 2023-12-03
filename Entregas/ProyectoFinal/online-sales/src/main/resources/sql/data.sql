USE sales;

INSERT INTO clients (firstname, lastname, document_number)
VALUES ("Alejandro","Rodriguez",98765432);
INSERT INTO clients (firstname, lastname, document_number)
VALUES ("Sofia ","Gomez",98765432);
INSERT INTO clients (firstname, lastname, document_number)
VALUES ("Valentina","Mendoza",56789012);

INSERT INTO products (product_code, product_description, stock, unit_price)
VALUES (75392014,"Yerba Mate Tradicional Canarias 250g",50,1000);
INSERT INTO products (product_code, product_description, stock, unit_price)
VALUES (46821537,"Fideos Spaghetti Matarazzo 500g",150,750);
INSERT INTO products (product_code, product_description, stock, unit_price)
VALUES (90287456,"Nutella pasta de avellana 350g",100,1500);

INSERT INTO invoices (created_at, total, client_id)
VALUES("2023-05-20",3500,2);
INSERT INTO invoices (created_at, total, client_id)
VALUES("2023-06-29",5000,1);
INSERT INTO invoices (created_at, total, client_id)
VALUES("2023-09-15",3000,3);

INSERT INTO invoice_details (invoice_id, product_id, product_amount,subtotal)
VALUES(1,1,2,2000);
INSERT INTO invoice_details (invoice_id, product_id, product_amount,subtotal)
VALUES(1,2,2,1500);

INSERT INTO invoice_details (invoice_id, product_id, product_amount,subtotal)
VALUES(2,3,2,3000);
INSERT INTO invoice_details (invoice_id, product_id, product_amount,subtotal)
VALUES(2,1,2,2000);

INSERT INTO invoice_details (invoice_id, product_id, product_amount,subtotal)
VALUES(3,2,4,3000);
