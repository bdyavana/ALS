DROP TABLE IF EXISTS user;
CREATE TABLE user ( id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(20) NOT NULL, password VARCHAR(70) NOT NULL);  
INSERT INTO user (username, password) VALUES
('ibmuser', '$2a$04$is6duUOagU1bocap/buWUumgC0gORhtz2//X6TOvpupJQEOQgd/fy'),
('useribm', '$2a$04$aZvJ2nhnxg3q/LruxejQTuSHWBedrsd70cMiXLNhdQ2gQSsQwNrEm');
