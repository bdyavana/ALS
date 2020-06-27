DROP TABLE IF EXISTS user;
CREATE TABLE user ( id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(20) NOT NULL, password VARCHAR(20) NOT NULL);  
INSERT INTO user (username, password) VALUES
('userone', 'userone'),
('usertwo', 'usertwo');