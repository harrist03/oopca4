DROP DATABASE IF EXISTS oopca4;
CREATE DATABASE oopca4;
USE oopca4;

DROP TABLE IF EXISTS expenses;
DROP TABLE IF EXISTS income;


CREATE TABLE expenses (
    expenseID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    amount DOUBLE NOT NULL,
    dateIncurred DATE NOT NULL
);

CREATE TABLE income (
    incomeID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    dateEarned DATE NOT NULL
);

INSERT INTO expenses (title, category, amount, dateIncurred) 
VALUES 
('Tesco', 'Groceries', 47.50, '2025-01-07'),
('Gym membership', 'Fitness', 30.00, '2025-01-09'),
('Dinner', 'Entertainment', 25.75, '2025-01-10'),
('Car fuel', 'Transport', 60.00, '2025-01-12'),
('Electricity bill', 'Utilities', 95.30, '2025-01-15');

INSERT INTO income (title, amount, dateEarned) 
VALUES 
('Babysitting', 60.00, '2025-01-05'),
('Bar work', 100.00, '2025-01-07'),
('Freelance work', 250.00, '2025-01-08'),
('Tutoring', 40.00, '2025-01-10'),
('Part-time job salary', 500.00, '2025-01-15');

