DROP TABLE IF EXISTS users;

CREATE TABLE users(
id INT AUTO_INCREMENT PRIMARY KEY,
no_hp VARCHAR,
ktp VARCHAR,
email VARCHAR,
password VARCHAR,
counter INT
);

DROP TABLE IF EXISTS loker;

CREATE TABLE loker(
id INT AUTO_INCREMENT PRIMARY KEY,
jumlah_loker INT,
lama_peminjaman INT,
deposit FLOAT,
denda FLOAT,
user_id INT,
bayar_denda FLOAT,
FOREIGN KEY (user_id) REFERENCES users(id)
);

