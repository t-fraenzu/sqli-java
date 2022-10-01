CREATE DATABASE IF NOT EXISTS jakartajpa;
CREATE DATABASE IF NOT EXISTS jakartajdbc;
GRANT ALL ON jakartajpa.* TO 'jakartaUser'@'%';
GRANT ALL ON jakartajdbc.* TO 'jakartaUser'@'%';
use jakartajpa;

DROP TABLE if exists Employee;
CREATE TABLE Employee
(
    eid    INT          NOT NULL,
    ename  VARCHAR(255) NULL,
    salary DOUBLE       NOT NULL,
    workfunction    VARCHAR(255) NULL,
    CONSTRAINT pk_employee PRIMARY KEY (eid)
);
INSERT INTO Employee (eid, ename, salary, workfunction)
VALUES
	(1, 'Thomas', 800, 'SW-Engineer'),
	(12, 'Anchel', 80, 'SW-Architect'),
	(15, 'Lorena', 20, 'student'),
	(21, 'David', 70, 'teacher'),
	(25, 'jenseon', 5, 'no duty');

commit;

use jakartajdbc;

DROP TABLE if exists Employee;
CREATE TABLE Employee
(
    eid    INT          NOT NULL,
    ename  VARCHAR(255) NULL,
    salary DOUBLE       NOT NULL,
    workfunction    VARCHAR(255) NULL,
    CONSTRAINT pk_employee PRIMARY KEY (eid)
);
INSERT INTO Employee (eid, ename, salary, workfunction)
VALUES
	(1, 'Thomas', 800, 'SW-Engineer'),
	(12, 'Anchel', 80, 'SW-Architect'),
	(15, 'Jara', 10, 'dr.'),
	(21, 'Lorena', 20, 'student'),
	(25, 'David', 70, 'teacher');

commit;