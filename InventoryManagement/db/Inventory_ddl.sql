SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

/* Create Tables */

CREATE TABLE categories
(
	category_id int(11) NOT NULL AUTO_INCREMENT,
	category_name varchar(255) NOT NULL,
	PRIMARY KEY (category_id),
	UNIQUE (category_id),
	UNIQUE (category_name)
);


CREATE TABLE products
(
	product_id varchar(20) NOT NULL,
	name varchar(255) NOT NULL,
	category_id int(11) NOT NULL,
	last_updated_dateTime datetime,
	last_updated_username varchar(255) NOT NULL,
	deleted char(1) DEFAULT 'N' NOT NULL,
	PRIMARY KEY (product_id),
	UNIQUE (product_id),
	UNIQUE (category_id)
);


CREATE TABLE roles
(
	role_id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(45) NOT NULL,
	PRIMARY KEY (role_id)
);


CREATE TABLE users
(
	user_id int(11) NOT NULL AUTO_INCREMENT,
	username varchar(45) NOT NULL,
	password varchar(64) NOT NULL,
	enabled tinyint(4) DEFAULT NULL,
	PRIMARY KEY (user_id)
);


CREATE TABLE users_roles
(
	user_id int(11) NOT NULL,
	role_id int(11) NOT NULL
);


/* Create Foreign Keys */

ALTER TABLE products
	ADD FOREIGN KEY (category_id)
	REFERENCES categories (category_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE users_roles
	ADD FOREIGN KEY (role_id)
	REFERENCES roles (role_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE users_roles
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
