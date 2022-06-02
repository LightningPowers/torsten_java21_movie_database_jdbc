use movies;

-- Release Years
insert into releasedate (releasedate) values (1963); -- the silence
insert into releasedate (releasedate) values (1977); -- star wars
insert into releasedate (releasedate) values (1985); -- ran
insert into releasedate (releasedate) values (1994); -- pulp fiction
insert into releasedate (releasedate) values (2000); -- jalla! jalla!
insert into releasedate (releasedate) values (2002); -- spiderman
insert into releasedate (releasedate) values (2006); -- flags of our fathers
insert into releasedate (releasedate) values (2009); -- avatar
insert into releasedate (releasedate) values (2014); -- interstellar 
insert into releasedate (releasedate) values (2022); -- world war C#

-- Directors
insert into director (director_name, city_name, director_age) VALUES ('Unknown Director', 'Unknown', 0);
insert into director (director_name, city_name, director_age) VALUES ('James Cameroon', 'New York', 42);
insert into director (director_name, city_name, director_age) VALUES ('George Lucas', 'Modesto', 78);
insert into director (director_name, city_name, director_age) VALUES ('Johan Morgonmörker', 'Helsingborg', 55);
insert into director (director_name, city_name, director_age) VALUES ('Clint Eastwood', 'San Francisco', 91);
insert into director (director_name, city_name, director_age) VALUES ('Sam Raimi', 'Royal Oak', 62);
insert into director (director_name, city_name, director_age) VALUES ('Josef Fares', 'Beirut', 44);
insert into director (director_name, city_name, director_age) VALUES ('Quentin Tarantino', 'Knoxville', 59);
insert into director (director_name, city_name, director_age) VALUES ('Christopher Nolan', 'Westminster', 51);
insert into director (director_name, city_name, director_age) VALUES ('Akira Kurosawa', 'Shinagawa', 88);
insert into director (director_name, city_name, director_age) VALUES ('Ingmar Bergman', 'Uppsala', 89);

-- Genres
insert into genre (genre) values ('Action');
insert into genre (genre) values ('Drama');
insert into genre (genre) values ('Horror');
insert into genre (genre) values ('Fantasy');
insert into genre (genre) values ('Sci-Fi');
insert into genre (genre) values ('Comedy');
insert into genre (genre) values ('Thriller');
insert into genre (genre) values ('War');
insert into genre (genre) values ('Documentary');
insert into genre (genre) values ('History');

-- Addresses
insert into address (address, postalcode, state) values ('Mångatan 14A', 22132, 'Uppland'); -- Ingrid Thulin
insert into address (address, postalcode, state) values ('44th Spoon Street', 14442, 'California'); -- Mark Hamill
insert into address (address, postalcode, state) values ('Shibuya Square', 234123, 'Tokyo'); -- Tatsuya Nakadai
insert into address (address, postalcode, state) values ('Dark Oak Avenue 124', 624123, 'New Jersey'); -- John Travolta
insert into address (address, postalcode, state) values ('Soldatvägen 64', 21993, 'Uppland'); -- Fares Fares
insert into address (address, postalcode, state) values ('King Street 12', 17442, 'California'); -- Tobey Maguire
insert into address (address, postalcode, state) values ('Evergreen Grove 72S', 11347, 'Delaware'); -- Ryan Phillippe
insert into address (address, postalcode, state) values ('21st Bluestar Road', 22532, 'New Jersey'); -- Zoe Saldaña
insert into address (address, postalcode, state) values ('13th Warhorse Road', 40012, 'Texas'); -- Matthew McConaughey
insert into address (address, postalcode, state) values ('Leende Gåsens Väg 69', 13370, 'Blekinge'); -- Bellan Larsmård

-- Actors
insert into actor (actor_name, hometown, actor_age, address_id)
select 'Ingrid Thulin', 'Sollefteå', 77, address_id
from address
where postalcode = 22132; 

insert into actor (actor_name, hometown, actor_age, address_id)
select 'Mark Hamill', 'Oakland', 70, address_id
from address
where postalcode = 14442; 

insert into actor (actor_name, hometown, actor_age, address_id)
select 'Tatsuya Nakadai', 'Tokyo', 89, address_id
from address
where postalcode = 234123; 

insert into actor (actor_name, hometown, actor_age, address_id)
select 'John Travolta', 'Englewood', 68, address_id
from address
where address = 'Dark Oak Avenue 124'; 

insert into actor (actor_name, hometown, actor_age, address_id)
select 'Fares Fares', 'Beirut', 49, address_id
from address
where postalcode = 21993 and state = 'Uppland'; 

insert into actor (actor_name, hometown, actor_age, address_id)
select 'Tobey Maguire', 'Santa Monica', 46, address_id
from address
where address = 'King Street 12';

insert into actor (actor_name, hometown, actor_age, address_id)
select 'Ryan Phillippe', 'New Castle', 47, address_id
from address
where postalcode = 11347 and state = 'Delaware'; 

insert into actor (actor_name, hometown, actor_age, address_id)
select 'Zoe Saldaña', 'Passaic', 43, address_id
from address
where postalcode = 22532; 

insert into actor (actor_name, hometown, actor_age, address_id)
select 'Matthew McConaughey', 'Uvalde', 52, address_id
from address
where address = '13th Warhorse Road' and state = 'Texas'; 

insert into actor (actor_name, hometown, actor_age, address_id)
select 'Bellan Larsmård', 'Karlskrona', 63, address_id
from address
where address = 'Leende Gåsens Väg 69'; 

-- Movies
insert into movies (title, director_id, releasedate_id)
select 'The Silence', director_id, releasedate_id
from director, releasedate
where director_name = "Ingmar Bergman" 
and releasedate = 1963;
    
insert into movies (title, director_id, releasedate_id)
select 'Star Wars', director_id, releasedate_id
from director, releasedate
where director_name = "George Lucas" 
and releasedate = 1977;

insert into movies (title, director_id, releasedate_id)
select 'Ran', director_id, releasedate_id
from director, releasedate
where director_name = "Akira Kurosawa" 
and releasedate = 1985;

insert into movies (title, director_id, releasedate_id)
select 'Pulp Fiction', director_id, releasedate_id
from director, releasedate
where director_name = "Quentin Tarantino" 
and releasedate = 1994;

insert into movies (title, director_id, releasedate_id)
select 'Jalla! Jalla!', director_id, releasedate_id
from director, releasedate
where director_name = "Josef Fares" 
and releasedate = 2000;

insert into movies (title, director_id, releasedate_id)
select 'Spider-Man', director_id, releasedate_id
from director, releasedate
where director_name = "Sam Raimi" 
and releasedate = 2002;

insert into movies (title, director_id, releasedate_id)
select 'Flags of Our Fathers', director_id, releasedate_id
from director, releasedate
where director_name = "Clint Eastwood" 
and releasedate = 2006;

insert into movies (title, director_id, releasedate_id)
select 'Avatar', director_id, releasedate_id
from director, releasedate
where director_name = "James Cameroon" 
and releasedate = 2009;

insert into movies (title, director_id, releasedate_id)
select 'Interstellar', director_id, releasedate_id
from director, releasedate
where director_name = "Christopher Nolan" 
and releasedate = 2014;

insert into movies (title, director_id, releasedate_id)
select 'World War C#', director_id, releasedate_id
from director, releasedate
where director_name = "Johan Morgonmörker" 
and releasedate = 2022;

insert into movies (title, director_id, releasedate_id)
select 'The Prestige', director_id, releasedate_id
from director, releasedate
where director_name = "Christopher Nolan" 
and releasedate = 2006;

-- Mapping movies and genres
-- Insert only one genre
insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'The Silence' 
and genre = 'Drama';

-- Insert multiple genre entries to a film
insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Star Wars' 
and genre = 'Action';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Star Wars' 
and genre = 'Fantasy';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Ran' 
and genre = 'Action';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Ran' 
and genre = 'Drama';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Ran' 
and genre = 'War';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Pulp Fiction' 
and genre = 'Drama';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Jalla! Jalla!' 
and genre = 'Comedy';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Spider-Man' 
and genre = 'Action';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Flags of Our Fathers' 
and genre = 'Action';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Flags of Our Fathers' 
and genre = 'Drama';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Flags of Our Fathers' 
and genre = 'War';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Avatar' 
and genre = 'Action';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Avatar' 
and genre = 'Sci-Fi';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Interstellar' 
and genre = 'Sci-Fi';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'Interstellar' 
and genre = 'Drama';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'World War C#' 
and genre = 'Action';

insert into moviegenres (movie_id, genre_id)
select movie_id, genre_id
from movies, genre
where title = 'World War C#' 
and genre = 'Horror';

-- Mapping movie to actor relation
call sp_addactorrelations('The Silence', 'Ingrid Thulin');
call sp_addactorrelations('Star Wars', 'Mark Hamill');
call sp_addactorrelations('Ran', 'Tatsuya Nakadai');
call sp_addactorrelations('Pulp Fiction', 'John Travolta');
call sp_addactorrelations('Jalla! Jalla', 'Fares Fares');
call sp_addactorrelations('Spider-Man', 'Tobey Maguire');
call sp_addactorrelations('Flags of Our Fathers', 'Ryan Phillippe');
call sp_addactorrelations('Avatar', 'Zoe Saldaña');
call sp_addactorrelations('Interstellar', 'Matthew McConaughey');
call sp_addactorrelations('World War C#', 'Bellan Larsmård');