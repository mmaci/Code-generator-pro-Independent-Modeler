
DROP TABLE test_class;
CREATE TABLE test_class (
value double,
pk_id test_class,
PRIMARY KEY (pk_id)
);

DROP TABLE second_class_test_class;
CREATE TABLE second_class_test_class (
second_class_jmeno_pk_1 typ_pk1
test_class_jmeno_pk_2 typ_pk2
);

ALTER TABLE test_class ADD (

PRIMARY KEY (pk_id)
);

DROP TABLE second_class;
CREATE TABLE second_class (
pk_id int,
PRIMARY KEY (pk_id)
);

DROP TABLE second_class_test_class;
CREATE TABLE second_class_test_class (
second_class_jmeno_pk_1 typ_pk1
test_class_jmeno_pk_2 typ_pk2
);

DROP TABLE test_child;
CREATE TABLE test_child (
pk_id int,
PRIMARY KEY (pk_id)
);

ALTER TABLE test_class ADD (

PRIMARY KEY (pk_id)
);
