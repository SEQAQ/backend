drop database if exists seqaq;
create database seqaq;
use seqaq;
create table users
(
    uid        BIGINT NOT null AUTO_INCREMENT,
    uname      varchar(30),
    account    varchar(30),
    `password` varchar(30),
    email      varchar(50),
    phone      varchar(30),
    sex        varchar(2),
    rname      varchar(30),
    cid        varchar(20),
    department varchar(20),
    role       varchar(20),
    permission varchar(50),
    stat       int default 1 null,
    exp        int default 0 null,
    lev        int default 1 null,
    follower   bigint,
    followed   bigint,
    PRIMARY KEY (uid)
)DEFAULT CHARSET=utf8;

create table questions
(
	qid				BIGINT NOT null AUTO_INCREMENT,
	tag				varchar(20),
	ctime			timestamp,
	stat  int,
	uid				bigint not null,
    follower  bigint,
	mtime			timestamp,
	title			varchar(1024),
	primary key (qid),
	foreign key (uid) references users(uid)
)DEFAULT CHARSET=utf8;


create table answers
(
	aid				BIGINT NOT null AUTO_INCREMENT,
	ctime			timestamp,
	stat  int,
	uid				bigint not null,
	mtime			timestamp,
    love    bigint,
    dislike   bigint,
    qid				bigint not null,
	primary key (aid),
	foreign key (uid) references users(uid),
	foreign key (qid) references questions(qid)
)DEFAULT CHARSET=utf8;


create table replies
(
	rid				BIGINT NOT null AUTO_INCREMENT,
	ctime			timestamp,
	stat  int,
	uid				bigint not null,
    did				bigint,
	love    bigint,
	dislike   bigint,
	dtype			int,
	primary key (rid),
	foreign key (uid) references users(uid)
)DEFAULT CHARSET=utf8;

create table userandques
(
	id				BIGINT NOT null AUTO_INCREMENT,
  uid       bigint not null,
  qid				bigint not null,
  primary key (id),
	foreign key (uid) references users(uid),
  foreign key (qid) references questions(qid)
)DEFAULT CHARSET=utf8;


create table followers
(
	id				BIGINT NOT null AUTO_INCREMENT,
  uid1      bigint not null,
  uid2			bigint not null,
  primary key (id),
	foreign key (uid1) references users(uid),
  foreign key (uid2) references users(uid)
)DEFAULT CHARSET=utf8;

create table confirmation_token
(
tid				BIGINT NOT null AUTO_INCREMENT,
token     VARCHAR(255),
created_date timestamp,
uid				BIGINT NOT NULL,
primary key (tid),
foreign key (uid) references users(uid)
)DEFAULT CHARSET=utf8;


create table hot
(
hid             BIGINT NOT null AUTO_INCREMENT,
qid             BIGINT NOT null,
uid             BIGINT NOT null,
created_date timestamp,
primary key (hid)
)DEFAULT CHARSET=utf8;

create table like_record
(
lid             BIGINT NOT null AUTO_INCREMENT,
aid             BIGINT NOT null,
uid             BIGINT NOT null,
primary key (lid)
)DEFAULT CHARSET=utf8;