SELECT * FROM workers;

SELECT * FROM workhours;

INSERT INTO `persons`.`workers` (`login`, `password`, `firstName`, `lastName`, `phoneNumber`, `pesel`) VALUES ('test', 'test', 'Bob', 'Furen', '111555999', '78787878744');

INSERT INTO `persons`.`workhours` (`address`, `data`, `start_time`, `end_time`, `comment`, `user_id`) VALUES ('address', '2023-01-01', '07:00:00', '15:00:00', 'example comment', '1');

