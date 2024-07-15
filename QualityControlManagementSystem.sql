create database quality_control;

USE quality_control;

CREATE TABLE Products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    production_date DATE NOT NULL,
    quantity_produced INT NOT NULL
);

CREATE TABLE Defects (
    defect_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE Quality_Checks (
    check_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL,
    defect_id INT NOT NULL,
    check_date DATE NOT NULL,
    result ENUM('pass', 'fail') NOT NULL,
    FOREIGN KEY (product_id) REFERENCES Products(product_id),
    FOREIGN KEY (defect_id) REFERENCES Defects(defect_id)
);
