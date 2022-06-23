-- sheme
show tables;
desc user;
desc blog;
desc category;
desc post;

select * from user;
select * from blog;
select * from category;
select * from post;

delete from user where id = "1";
delete from blog where id = "test01";
delete from category where blog_id = "test01";

select name from user where id='3' and password='3';

insert
		  into post
		values (null, "제목1", "내용1", null);

update blog set title="Test Blog"
				where id =id;