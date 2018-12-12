CREATE TABLE `waterdrop`.`t_trader` (
  `f_trader_id` INT NOT NULL,
  `f_trader_name` VARCHAR(45) NOT NULL,
  `f_seat_id` VARCHAR(10) NOT NULL,
  `f_password` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`f_trader_id`, `f_password`))
COMMENT = '����Ա����Ž���Ա��Ϣ';


CREATE TABLE `waterdrop`.`t_seat` (
  `f_seat_id` INT NOT NULL,
  `f_seat_name` VARCHAR(45) NULL,
  PRIMARY KEY (`f_seat_id`))
COMMENT = 'ϯλ��';


CREATE TABLE `waterdrop`.`t_trader_login` (
  `f_trader_id` INT NOT NULL,
  `f_is_login` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`f_trader_id`, `f_is_login`))
COMMENT = '����Ա��¼��Ϣ��';
