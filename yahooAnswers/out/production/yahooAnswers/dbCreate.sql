create database yahooAnswers CHARACTER set 'utf8' COLLATE 'utf8_general_ci';

use yahooAnswers;

CREATE TABLE urlmanager
(
	url varchar(1000),
	urlflag int,
	PRIMARY KEY(url)
) ENGINE=InnoDB default charset=utf8;

create index url_index on urlmanager(url);

#    //question Id
#    private String questionId;
#    //title
#    private String title;
#    //question description
#    private String description;
#    //question category
#    private String category;
#    //question follower persons
#    private int follower;
#    //question the number of answers
#    private int answers;

create table questions
(
	questionId varchar(21),
	title varchar(500),
	description text,
	category varchar(100),
	follower int,
	answers int
) ENGINE=InnoDB default charset=utf8;


#    //question id
#    private String questionId;
#    //answer content
#    private String content;
#    //answer user name
#    private String userName;
#    //answer date
#    private String date;
#    //answer thumbs Up
#    private int thumbsUp = 0;
#    //answer thumbs down
#    private int thumbsDown = 0;
#    //is best answer
#    private boolean isBest = false;
#    //number of comments
#    private int commentNum = 0;

create table answers
(
	questionId varchar(21),
	content text,
	userName varchar(100),
	answerdate varchar(100),
	thumbsUp int,
	thumbsDown int,
	isBest boolean,
	commentNum int
) ENGINE=InnoDB default charset=utf8;