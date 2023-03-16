drop table if exists iiit_projects;

/*==============================================================*/
/* Table: iiit_projects                                                */
/*==============================================================*/
create table iiit_projects
(
   id                   int unsigned not null auto_increment comment '主键',
   name             varchar(50) comment '用户昵称',   
   primary key (id)
);
