/*
All objects should have names from the start:
- primary keys
- unique keys
- ...
Referencing contraints in order to modify or drop them becomes near
impossible otherwise.

All foreign keys should have index create
https://sqlperformance.com/2012/11/t-sql-queries/benefits-indexing-foreign-keys
https://stackoverflow.com/questions/4127206/do-i-need-to-create-indexes-on-foreign-keys

 */

CREATE TABLE IF NOT EXISTS tree (
  id SERIAL,
  name VARCHAR(100) NOT NULL,
  version INT NOT NULL
);

ALTER TABLE tree ADD CONSTRAINT TREE_PKEY PRIMARY KEY (ID);

CREATE TABLE IF NOT EXISTS items (
  id SERIAL,
  name VARCHAR(100) NOT NULL,
  tree_id INT NOT NULL,
  parent_id INT,
  version INT NOT NULL
);

ALTER TABLE items ADD CONSTRAINT item_pkey PRIMARY KEY (id);

ALTER TABLE items
ADD CONSTRAINT tree_fk
FOREIGN KEY (tree_id)
REFERENCES tree(id)
ON DELETE CASCADE; --it could be strong, but business requirements is not clean yet... ;)

CREATE INDEX items_parent_idx ON items (parent_id);
CREATE INDEX items_tree_fk_idx ON items (tree_id);

CREATE SEQUENCE IF NOT EXISTS ITEM_VERSION_SEQ  MINVALUE 1 START 1;

