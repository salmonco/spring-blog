CREATE TABLE board (
    board_no INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    board_title VARCHAR(100) NOT NULL,
    board_content VARCHAR(500),
    user_id VARCHAR(100) NOT NULL,
    board_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    board_file1 VARCHAR(200),
    board_file2 VARCHAR(200),
    board_hit INT DEFAULT 0,
    board_like INT DEFAULT 0
);