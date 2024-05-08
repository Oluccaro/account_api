CREATE TABLE IF NOT EXISTS account (
  account_id varchar(30) NOT NULL PRIMARY KEY,
  balance NUMERIC(15,2)
);

CREATE TABLE IF NOT EXISTS account_event (
  account_event_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  type VARCHAR(30) NOT NULL CHECK (type IN ('deposit', 'withdraw', 'transfer')),
  amount NUMERIC(15,2) NOT NULL,
  account_origin varchar(30),
  account_destination varchar(30),
  FOREIGN KEY (account_origin) REFERENCES account(account_id),
  FOREIGN KEY (account_destination) REFERENCES account(account_id)
);
