CREATE DATABASE sales;
USE sales;

CREATE TABLE clients(
	id INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(75),
    lastname VARCHAR(75),
    document_number VARCHAR(11)
);

CREATE TABLE invoices(
	id INT PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME,
    total DOUBLE,
    client_id INT,

    CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE products(
	id INT PRIMARY KEY AUTO_INCREMENT,
	product_code VARCHAR(50),
    product_description VARCHAR(150),
    stock INT,
    unit_price DECIMAL(10,2)
);

CREATE TABLE invoice_details(
	id INT PRIMARY KEY AUTO_INCREMENT,
	invoice_id INT,
	product_id INT,
    product_amount INT,
    subtotal DOUBLE,

    CONSTRAINT fk_invoice_id FOREIGN KEY (invoice_id) REFERENCES invoices(id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products(id)
);

