
drop table if exists users;
CREATE TABLE users (
  username varchar(50) NOT NULL,
  password varchar(128) DEFAULT NULL,
  regist_time datetime DEFAULT NULL,
  login_time datetime DEFAULT NULL,
  is_locked tinyint(2) DEFAULT '0',
  lock_time datetime DEFAULT NULL,
  id bigint(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index index_user_name on users(username);
insert into users(id, username, password, regist_time) values(1, 'admin', '9aa75c4d70930277f59d117ce19188b0', NOW());

drop table if exists menu;
CREATE TABLE menu (
  name varchar(50) NOT NULL,
  parent_id bigint NOT NULL,
  create_time datetime NOT NULL,
  url varchar(50) DEFAULT 'javascript:;',
  sort int NOT NULL,
  id bigint NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index index_menu_name on menu(name);
insert into menu(id, name, parent_id, create_time, sort) values(2, '系统设置', 0, NOW(), 3);
insert into menu(id, name, parent_id, create_time, sort, url) values(3, '个人中心', 0, NOW(), 1, '/module/index');
insert into menu(id, name, parent_id, create_time, sort, url) values(4, '权限菜单', 0, NOW(), 2, '/role/index');
insert into menu(id, name, parent_id, create_time, sort, url) values(5, '用户管理', 0, NOW(), 0, '/user/index');
insert into menu(id, name, parent_id, create_time, url, sort) values(6, '菜单管理', 2, NOW(), '/menu/index', 0);

drop table if exists role;
CREATE TABLE role (
  name varchar(50) NOT NULL,
  descr varchar(50) NOT NULL,
  create_time datetime NOT NULL,
  id bigint NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index index_role_name on role(name);
create index index_role_create_time on role(create_time);

insert into role(id, name, descr, create_time) values(0, 'admin', '管理员', NOW());
insert into role(id, name, descr, create_time) values(1, 'staff', '员工', NOW());
insert into role(id, name, descr, create_time) values(2, 'tourist', '游客', NOW());

drop table if exists role_menu;
CREATE TABLE role_menu (
  menu_id bigint NOT NULL,
  create_time datetime NOT NULL,
  id bigint NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index index_role_menu_menu_id on role_menu(menu_id);
create index index_role_create_time on role_menu(create_time);

drop table if exists role_user;
CREATE TABLE role_user (
  user_id bigint NOT NULL,
  create_time datetime NOT NULL,
  id bigint NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create index index_role_user_user_id on role_user(user_id);
create index index_role_user_create_time on role_user(create_time);
