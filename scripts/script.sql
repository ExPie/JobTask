CREATE USER test IDENTIFIED BY 'test';
CREATE SCHEMA studentdb;
GRANT ALL ON studentdb.* TO test;
USE studentdb;

CREATE TABLE students (
	studentID bigint not null auto_increment primary key,
	name varchar(64),
	surname varchar(64),
	hash varchar(512),
	salt varchar(32)
);

CREATE TABLE subjects (
	subjectID bigint not null auto_increment primary key,
	name varchar(64)
);

CREATE TABLE enrollment (
	enrollID bigint not null auto_increment primary key,
	studentID bigint,
	subjectID bigint,
	foreign key (studentID) references students(studentID),
	foreign key (subjectID) references subjects(subjectID) 
);


INSERT INTO subjects(name) 
VALUES("Programiranje"), 
	("Matematika"), 
	("Podatkovne baze"), 
	("Spletne tehnologije"), 
	("Prevajalniki"), 
	("Programiranje 2"), 
	("Racunalniska grafika");

INSERT INTO students(studentID, name, surname, hash, salt) 
VALUES(2, "Dodo", "Dodovic", "7fc167de923fe8aed1b76e680eced1c0d1e7ede604c08d00b6f8dcdaaa46f058add348721a708923af0cfdae88a6f49ce3272172f75a2e12fa85d07a5ed4547e", "GKyY1FZS"),
	(3, "Test", "Test", "4e98da5bf16bad939a8c20a9cd4a0f474f30aff15c3f74410f4c558ca956b0a3e65e5d1c64a8cde10ceda0f9d4748aae1e7c0b92eea7cf89c1134c5d5d13d2d7", "f4QMRem1");


