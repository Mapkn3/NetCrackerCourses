CREATE TABLE child_activities
(
    a_id NUMBER NOT NULL,
    ca_id NUMBER NOT NULL,
    FOREIGN KEY (a_id) REFERENCES activities(a_id),
    FOREIGN KEY (ca_id) REFERENCES activities(a_id),
    PRIMARY KEY (a_id, ca_id)
);

INSERT INTO child_activities VALUES (0, 2);
INSERT INTO child_activities VALUES (2, 3);