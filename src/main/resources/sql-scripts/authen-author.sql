-- Drop tables if they exist (useful for resetting the database)
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

-- Create roles table
CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(20) NOT NULL
);

-- Create users table
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create user_roles table to map users to roles (Many-to-Many relationship)
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Insert initial roles data
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- Optional: Insert initial user data (for testing)
-- Passwords should be hashed, below is just an example with plain text (DO NOT USE IN PRODUCTION)
INSERT INTO users (username, email, password) VALUES ('testuser', 'testuser@example.com', 'password');
INSERT INTO users (username, email, password) VALUES ('admin', 'admin@example.com', 'password');

-- Assign roles to users
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- Assign ROLE_USER to testuser
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- Assign ROLE_ADMIN to admin
