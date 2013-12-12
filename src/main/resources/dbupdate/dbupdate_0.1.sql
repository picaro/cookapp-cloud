alter table shoplist add column coordinates varchar (255);
CREATE TABLE slist2circle (id int(11) NOT NULL auto_increment, shoplistid int(11) NOT NULL, circleid int(11) NOT NULL, PRIMARY KEY (id));
