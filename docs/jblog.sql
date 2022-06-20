-- sheme
show tables;
desc user;
desc blog;
desc category;
desc post;

select * from user;

delete from user where name = "name";

insert all
	into user(name, id, password)
		values ("이름", "아이디", "패스워드")
    into blog(id, title, logo)
		values("이름", "타이틀", "로고경로")
	into category(blog_id)
		values("아이디")
        select * from dual;
        
insert into blog values("1","관리자 블로그", "D:/jblog-upload");
