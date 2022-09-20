CREATE TABLE Employee (
  empId int(10) unsigned NOT NULL,
  name varchar(10) DEFAULT NULL,
  PRIMARY KEY (empId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insert some sample data
INSERT INTO Employee (empId, name)
VALUES
	(1, 'Pankaj'),
	(2, 'David');

commit;