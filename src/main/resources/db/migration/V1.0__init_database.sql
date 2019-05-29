
drop table if exists users;
CREATE TABLE users (
  username varchar(50) NOT NULL,
  password varchar(128) DEFAULT NULL,
  regist_time datetime DEFAULT NULL,
  login_time datetime DEFAULT NULL,
  is_locked tinyint(2) DEFAULT '0',
  lock_time datetime DEFAULT NULL,
  id bigint(20) NOT NULL,
  salt varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index index_user_name on users(username);