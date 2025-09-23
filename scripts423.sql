SELECT student.name, student.age, faculty.name
FROM student
INNER JOIN faculty ON faculty.id = student.faculty_id;

SELECT student.name,avatar.id
FROM student
LEFT JOIN avatar ON student.id=avatar.student_id
WHERE avatar.id IS NOT NULL;
