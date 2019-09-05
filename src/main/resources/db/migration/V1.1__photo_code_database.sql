
drop table if exists photo_code;
CREATE TABLE photo_code (
  name char(10) NOT NULL,
  create_time datetime DEFAULT NOW(),
  id bigint(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into photo_code(name, create_time, id) values ('衣柜', NOW(), 1);


drop table if exists photo;
CREATE TABLE photo (
  name char(10) NOT NULL,
  create_time datetime DEFAULT NOW(),
  url varchar(255) NOT NULL,
  photo_code_id bigint(20) NOT NULL,
  id bigint(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into photo(name, create_time, id, url, photo_code_id) values ('大床', NOW(), 1, 'http://img0.imgtn.bdimg.com/it/u=2854956166,1658664264&fm=26&gp=0.jpg', 1);

