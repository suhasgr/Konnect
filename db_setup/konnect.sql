-- Database: konnect

-- DROP DATABASE konnect;

CREATE DATABASE konnect
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
 
CREATE TABLE UserInformation(
   Email varchar(25) PRIMARY KEY NOT NULL UNIQUE,	
   UserID varchar(25) NOT NULL UNIQUE,
   Pass varchar(12) NOT NULL,
   Friends varchar(25)[],
   pending varchar(25)[],
   sentRequests varchar(25)[],
   occupation varchar(20),
   location varchar(20),
   gender varchar(6),
   hobbies text,
   name varchar(25),
   lastname varchar(25),
   image bytea,
   blockeduser varchar(25)[],
   notification varchar(100)[]
);

create table ChatSession(

	Uuid varchar(50) PRIMARY KEY NOT NULL UNIQUE,
	SenderID varchar(20) references UserInformation(UserID),
	ReceiverID varchar(20) references UserInformation(UserID),
	Active smallint
);


create table Messages(

	MessageID SERIAL PRIMARY KEY,
	ChatID varchar(50) references ChatSession(Uuid),
	Messages varchar(160),
	MsgTime timestamp,
	Sender varchar(50)
);

CREATE TABLE post
(
  userid varchar(25) references Userinformation(UserID),
  postid integer primary key not null,
  posttext character varying,
  posttime timestamp without time zone,
  likes integer,
 image bytea,
  lastname character varying(25),
  firstname character varying(25),
  likedusers character varying(25)[]
);

CREATE TABLE blockedposts
(
  indx serial NOT NULL,
  blockedpost integer NOT NULL,
  userid varchar(25) NOT NULL references userinformation(userid),
  CONSTRAINT blockedposts_blockedpost_key UNIQUE (blockedpost)
);

CREATE TABLE comments
(
  commentid serial NOT NULL,
  commenttext character varying,
  userid varchar(25) NOT NULL references userinformation(userid),
  postid integer references post(postid),
  commenttime timestamp without time zone,
  type integer
);

CREATE TABLE Forums(
   ForumID varchar(100) NOT NULL UNIQUE PRIMARY KEY,
   Category varchar(50) NOT NULL,
   UserID varchar(100) NOT NULL REFERENCES UserInformation(UserID),
   Description varchar(256) NOT NULL,
   Time timestamp WITHOUT TIME ZONE,
   CommentCount int,
   Forumname varchar(50)
);

CREATE TABLE CommentsForum(
   CommentID varchar(100) NOT NULL UNIQUE PRIMARY KEY,
   ForumID varchar(100) NOT NULL  REFERENCES Forums(ForumID) ON DELETE CASCADE,
   Description varchar(256) NOT NULL,
   UserID varchar(100) NOT NULL REFERENCES UserInformation(UserID),
   Time timestamp WITHOUT TIME ZONE,
   BlockCount integer,
   BlockedList character varying(100)[]
);

CREATE TABLE forumrequests(
   RequestID varchar(100) PRIMARY KEY NOT NULL UNIQUE,
   ForumID varchar(100) NOT NULL  REFERENCES Forums(ForumID) ON DELETE CASCADE,
   OwnerUserID varchar(100) NOT NULL  REFERENCES UserInformation(UserID),
   RequestUserID varchar(100),
   ApproveFlag boolean,
   PendingFlag boolean,
   MemberFlag boolean,
   OwnerFlag boolean
);
