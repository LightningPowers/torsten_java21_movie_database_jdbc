use movies;

drop procedure if exists sp_addactorrelations;
delimiter $$
create procedure sp_addactorrelations(in movietitle varchar(50), in actorname varchar(50))
begin
	insert into actorrelations (movie_id, actor_id)
    select movie_id, actor_id
    from movies, actor
	where title = movietitle 
	and actor_name = actorname;
end $$
delimiter ;

drop procedure if exists sp_unassignactor;
delimiter $$
create procedure sp_unassignactor(in movietitle varchar(50), in actorname varchar(50))
begin
    delete from actorrelations
	where movie_id in (
		select movie_id
		from movies
		where title = movietitle
	) and actor_id = (
		select actor_id
		from actor
		where actor_name = actorname
	);
end $$
delimiter ;


drop procedure if exists sp_addactor;
delimiter $$
create procedure sp_addactor(in fullname varchar(50), in newhometown varchar(50), in age tinyint(3), in newpostalcode mediumint)
begin
	insert into actor (actor_name, hometown, actor_age, address_id)
	select fullname, newhometown, age, address_id
	from address
	where postalcode = newpostalcode; 
end $$
delimiter ;

drop procedure if exists sp_deleteactor;
delimiter $$
create procedure sp_deleteactor(in actorname varchar(50))
begin
    delete from actorrelations 
    where actor_id = (
		select actor_id
		from actor
		where actor_name = actorname
	);
    delete from actor where actor_name = actorname;
end $$
delimiter ;

drop procedure if exists sp_deleteaddress;
delimiter $$
create procedure sp_deleteaddress(in deletionaddress varchar(50), in movingaddress varchar(50))
begin
    update actor
	set address_id = (
		select address_id
        from address
		where address = movingaddress
    )
    where address_id = (
		select address_id
        from address
		where address = deletionaddress
    );
    delete from address where address = deletionaddress;
end $$
delimiter ;

drop procedure if exists sp_deletedirector;
delimiter $$
create procedure sp_deletedirector(in directorname varchar(50))
begin
    update movies
	set director_id = (
		select director_id
        from director
		where director_name = "Unknown Director"
    )
    where director_id = (
		select director_id
        from director
		where director_name = directorname
    );
    delete from director where director_name = directorname;
end $$
delimiter ;

drop procedure if exists sp_unassigngenre;
delimiter $$
create procedure sp_unassigngenre(in movietitle varchar(50), in genrename varchar(50))
begin
    delete from moviegenres
	where movie_id in (
		select movie_id
		from movies
		where title = movietitle
	) and genre_id = (
		select genre_id
		from genre
		where genre = genrename
	);
end $$
delimiter ;

drop procedure if exists sp_deletegenre;
delimiter $$
create procedure sp_deletegenre(in genrename varchar(50))
begin
    delete from moviegenres
    where genre_id = (
		select genre_id
        from genre
        where genre = genrename
    );
    delete from genre where genre = genrename;
end $$
delimiter ;

drop procedure if exists sp_updatemovie;
delimiter $$
create procedure sp_updatemovie(in movietitle varchar(50), in newtitle varchar(50), in director varchar(50))
begin
    update movies
	set director_id = (
		select director_id
        from director
		where director_name = director
    )
    where title = movietitle;
    update movies
    set title = newtitle
    where title = movietitle;
end $$
delimiter ;

drop procedure if exists sp_deletemovie;
delimiter $$
create procedure sp_deletemovie(in movietitle varchar(50))
begin
    delete from moviegenres 
    where movie_id in (
		select movie_id
		from movies
		where title = movietitle
	);
    delete from movies where title = movietitle;
end $$
delimiter ;