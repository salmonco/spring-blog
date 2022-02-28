CREATE TABLE reply (
	reply_no INT NOT NULL AUTO_INCREMENT,
	board_no INT NOT NULL,
	reply_writer VARCHAR(100),
	reply_content VARCHAR(100),
	reply_date DATETIME DEFAULT NOW(),
	PRIMARY KEY(reply_no, board_no)
);