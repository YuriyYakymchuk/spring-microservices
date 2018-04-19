CREATE TABLE tables (
  id INT AUTO_INCREMENT PRIMARY KEY,
  free BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE orders (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  table_id INT NOT NULL
);

CREATE TABLE bills (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  table_id INT NOT NULL,
  order_id INT NOT NULL UNIQUE
);

CREATE TABLE menu_items (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE stock (
  id INT AUTO_INCREMENT PRIMARY KEY,
  menu_item_id INT NOT NULL UNIQUE,
  count INT NOT NULL DEFAULT 0
);

CREATE TABLE events (
  id INT AUTO_INCREMENT PRIMARY KEY,
  payload TEXT NOT NULL
);

CREATE TABLE events (
  id INT AUTO_INCREMENT PRIMARY KEY,
  payload TEXT NOT NULL,
  type TEXT NOT NULL
);
