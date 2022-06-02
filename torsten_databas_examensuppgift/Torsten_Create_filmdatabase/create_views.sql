create or replace view directorview as
select director_name as "Name", director_age as "Age", city_name as "Hometown", group_concat(movies.title separator ', ') as "Films"
from director, movies
where director.director_id = movies.director_id
group by director.director_name;

-- Combines genres on same row
create or replace view movieview as
select movies.title as "Title", director.director_name as "Director", releasedate.releasedate as "Release Year" , group_concat(genre.genre separator ', ') as "Genre"
from movies, director, releasedate, moviegenres, genre
where movies.director_id = director.director_id
and movies.releasedate_id = releasedate.releasedate_id
and moviegenres.genre_id = genre.genre_id
and movies.movie_id = moviegenres.movie_id
group by movies.title;

-- Show movies that has genre
create or replace view genreview as
select genre.genre as "Genre", movies.title as "Title"
from genre, movies, moviegenres
where movies.movie_id = moviegenres.movie_id
and moviegenres.genre_id = genre.genre_id
and movies.movie_id = moviegenres.movie_id;

create or replace view actorview as
select actor.actor_name as "Name", actor.hometown as "Hometown", actor.actor_age as "Age", address.address as "Address", address.postalcode as "Postal Code", address.state as "State"
from actor, address
where actor.address_id = address.address_id;