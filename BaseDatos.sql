CREATE TABLE person (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  gender ENUM('M','F'),
  age INT UNSIGNED,
  id_card_number INT UNSIGNED,
  address VARCHAR(100),
  phone VARCHAR(50)
) ENGINE = InnoDB;


CREATE TABLE client (
  id INT UNSIGNED NOT NULL PRIMARY KEY,
  client_id INT UNSIGNED UNIQUE NOT NULL,
  status_password INT(4) NOT NULL,
  status Tinyint(1) DEFAULT 1,
  CONSTRAINT `fk_person_client`
    FOREIGN KEY (id) REFERENCES person (id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
) ENGINE = InnoDB;


CREATE TABLE account (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  acc_number INT UNSIGNED UNIQUE NOT NULL,
  type ENUM('Ahorros','Corriente') NOT NULL,
  balance DECIMAL(20,2) DEFAULT 0.00,
  status Tinyint(1) DEFAULT 1,
  client_id INT UNSIGNED NOT NULL,
  CONSTRAINT `fk_client_account`
    FOREIGN KEY (client_id) REFERENCES client (client_id)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
) ENGINE = InnoDB;

CREATE TABLE transaction (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  txn_date Datetime,
  type ENUM('Ahorros','Corriente'),
  init_balance DECIMAL(20,2) NOT NULL,
  post_balance DECIMAL(20,2) NOT NULL,
  value DECIMAL(20,2) NOT NULL,
  acc_number INT UNSIGNED NOT NULL,
  CONSTRAINT `fk_account_transaction`
    FOREIGN KEY (acc_number) REFERENCES account (acc_number)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
) ENGINE = InnoDB;


/* PROCEDURES */
DROP PROCEDURE IF EXISTS transaction_report_get;

DELIMITER $$
$$
CREATE PROCEDURE `transaction_report_get`(
	IN $client_id INT, 
	IN $start_date DATE, 
	IN $end_date DATE
)
BEGIN
	
	SELECT 
		DATE(t.txn_date) AS `date`, 
		p.name AS client,
		acc.acc_number AS accountNumber,
		t.type AS `type`,
    t.init_balance AS initialBalance,
		acc.status AS status,
    t.value AS transaction,
    t.post_balance AS availableBalance
	FROM transaction t
	LEFT JOIN account acc on t.acc_number = acc.acc_number
  LEFT JOIN client c ON acc.client_id = c.client_id
  LEFT JOIN person p ON c.id = p.id
	WHERE c.client_id = $client_id AND (DATE(t.txn_date) BETWEEN $start_date AND $end_date);
END;
$$
