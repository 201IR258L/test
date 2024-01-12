CREATE DATABASE product_management;

CREATE TABLE "user" (
  id                serial,
  name              varchar(150),
  login_id          varchar(100),
  password          char(128),
  deleted           boolean,
  create_user_id    int,
  create_timestamp  timestamp,
  update_user_id    int,
  update_timestamp  timestamp,
  version_no        int,
  PRIMARY KEY(id)
);

CREATE TABLE product (
  id                   serial        NOT NULL,
  product_id           char(4)       NOT NULL,
  name                 varchar(300)  NOT NULL,
  category_code        char(2)       NOT NULL,
  unit_price           int,
  purchase_unit_price  int,
  registration_date    date,
  deleted              boolean       NOT NULL,
  create_user_id       int           NOT NULL,
  create_timestamp     timestamp     NOT NULL,
  update_user_id       int           NOT NULL,
  update_timestamp     timestamp     NOT NULL,
  version_no           int           NOT NULL,
  PRIMARY KEY(id)
);