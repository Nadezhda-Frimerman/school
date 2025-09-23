ALTER TABLE student
ADD CONSTRAINT chk_age CHECK (age >= 16),
ALTER COLUMN name SET NOT NULL,
ADD CONSTRAINT unique_name UNIQUE (name),
ALTER age SET DEFAULT 20;

ALTER TABLE faculty ADD CONSTRAINT uniqueFaculty_name UNIQUE (name,color);