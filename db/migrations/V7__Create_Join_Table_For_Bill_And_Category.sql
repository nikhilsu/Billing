CREATE TABLE bill_bill_category (
  bill_id int NOT NULL,
  bill_category_id int NOT NULL,
  PRIMARY KEY (bill_id, bill_category_id),
  CONSTRAINT bill_bill_category_fk_1
  FOREIGN KEY (bill_id) REFERENCES bill (id),
  CONSTRAINT bill_bill_category_fk_2
  FOREIGN KEY (bill_category_id) REFERENCES bill_category (id)
);