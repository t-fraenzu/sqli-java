CREATE DATABASE IF NOT EXISTS jakartajpa;
CREATE DATABASE IF NOT EXISTS jakartajdbc;
CREATE DATABASE IF NOT EXISTS jakartaauth;
GRANT ALL ON jakartajpa.* TO 'jakartaUser'@'%';
GRANT ALL ON jakartajdbc.* TO 'jakartaUser'@'%';
GRANT ALL ON jakartaauth.* TO 'jakartaUser'@'%';
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

use jakartaauth;

DROP TABLE if exists Groups;
DROP TABLE if exists Users;
CREATE TABLE Users
(
    id    INT          NOT NULL,
    username    VARCHAR(255) NULL,
    password    VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);
INSERT INTO Users (id, username, password)
VALUES
	(1, 'admin', 'admin'),
	(12, 'special', 'special'),
	(15, 'user', 'user');

CREATE TABLE Groups
(
    id    INT          NOT NULL,
    username    VARCHAR(255) NULL,
    groupname    VARCHAR(255) NULL,
    CONSTRAINT pk_usergroups PRIMARY KEY (id)
);
INSERT INTO Groups (id, username, groupname)
VALUES
	(1, 'admin', 'admin_role'),
	(2, 'admin', 'special_forces'),
	(12, 'special', 'special_forces'),
	(15, 'user', 'user_basic');

commit;
