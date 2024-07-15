# Quality-Control-Management-System

This is a Java-based Quality Control Management System for managing products, defects, and quality checks. The application interacts with a MySQL database to perform CRUD operations.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [Project Setup](#project-setup)
- [Running the Application](#running-the-application)
- [Usage](#usage)
- [Contributing](#contributing)

## Features

- Add, view, update, and delete products.
- Add, view, update, and delete defects.
- Perform and view quality checks on products.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL database
- MySQL JDBC Driver

## Database Setup

1. Create a MySQL database named `quality_control`:
    ```sql
    CREATE DATABASE quality_control;
    ```

2. Switch to the `quality_control` database:
    ```sql
    USE quality_control;
    ```

3. Create the necessary tables:
    ```sql
    CREATE TABLE Products (
        product_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255),
        description TEXT,
        production_date DATE,
        quantity_produced INT
    );

    CREATE TABLE Defects (
        defect_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255),
        description TEXT
    );

    CREATE TABLE Quality_Check (
        check_id INT AUTO_INCREMENT PRIMARY KEY,
        product_id INT,
        defect_id INT,
        check_date DATE,
        result VARCHAR(50),
        FOREIGN KEY (product_id) REFERENCES Products(product_id),
        FOREIGN KEY (defect_id) REFERENCES Defects(defect_id)
    );
    ```

## Project Setup

1. Clone the repository or download the source code.

2. Add the MySQL JDBC driver to your project classpath. You can download it from [here](https://dev.mysql.com/downloads/connector/j/).

## Running the Application

1. Open a terminal or command prompt.
2. Navigate to the `src` directory:
    ```sh
    cd path/to/src
    ```

3. Compile the code:
    ```sh
    javac qualitymanagementsystem/*.java
    ```

4. Run the application:
    ```sh
    QualityControlManagementSystem.java
    ```

## Usage

- **Manage Products**: Add, view, update, and delete products.
- **Manage Defects**: Add, view, update, and delete defects.
- **Manage Quality Checks**: Perform and view quality checks.

Follow the on-screen prompts to navigate through the menu and perform the desired operations.

## Contributing

Contributions are welcome! Please fork this repository and submit pull requests with your changes.

