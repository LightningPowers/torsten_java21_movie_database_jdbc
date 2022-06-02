package core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import helpers.databaseHelper;
import helpers.jsonHelper;
import objectLists.Actors;
import objectLists.ActorView;
import objectLists.Addresses;
import objectLists.DirectorView;
import objectLists.Directors;
import objectLists.GenreView;
import objectLists.Genres;
import objectLists.MovieView;
import objectLists.Movies;
import objectLists.Releasedates;

public class MainClass {
	public static void main(String[] args) throws SQLException {
		Connection conn = databaseHelper.DbConnect("movies");
				
		getMovieview(conn);
		
		createAddress(conn, "12th Monk's Avenue", 51515, "Greater London");
		createActor(conn, "Sam Worthington", "Godalming", 45, 51515);
		assignActor(conn, "Avatar", "Sam Worthington");
		
		createGenre(conn, "Adventure");
		assignGenre(conn, "Avatar", "Adventure");
		assignGenre(conn, "Interstellar", "Adventure");
		assignGenre(conn, "Star Wars", "Adventure");
		removeGenre(conn, "Avatar", "Sci-Fi");
		
		updateDirector(conn, "James Cameron", 67, "Kapuskasing", "James Cameroon");
		
		createMovie(conn, "Avatar: Water's Way", "George Lucas", 2022);
		assignGenre(conn, "Avatar: Water's Way", "Action");
		assignGenre(conn, "Avatar: Water's Way", "Fantasy");
		assignActor(conn, "Avatar: Water's Way", "Sam Worthington");
		assignActor(conn, "Avatar: Water's Way", "Zoe Saldaña");
		updateMovie(conn, "Avatar: Water's Way", "Avatar: The Way of Water", "James Cameron");
		
		getMovieview(conn);
		
		
		conn.close();
	}
	
	private static void displayAllTables(Connection conn) {
		
		Actors myActors = new Actors(conn);
		Addresses myAddresses = new Addresses(conn);
		ActorView myActorviewList = new ActorView(conn);
		Directors myDirectors = new Directors(conn);
		Releasedates myReleasedates = new Releasedates(conn);
		Genres myGenres = new Genres(conn);
		Movies myMovies = new Movies(conn);
		DirectorView myDirectorViewList = new DirectorView(conn);
		GenreView myGenreViewList = new GenreView(conn);
		MovieView myMovieViewList = new MovieView(conn);
		
		//Formats and presents all the objects into JSON
		ArrayList<String> document = new ArrayList<String>();
		document.add(myActors.toJson());
		document.add(myAddresses.toJson());
		document.add(myActorviewList.toJson());
		document.add(myDirectors.toJson());
		document.add(myReleasedates.toJson());
		document.add(myGenres.toJson());
		document.add(myMovies.toJson());
		document.add(myDirectorViewList.toJson());
		document.add(myGenreViewList.toJson());
		document.add(myMovieViewList.toJson());

		String jsonDoc = jsonHelper.toJsonDocument(document);
		
		System.out.println(jsonDoc);
		
	}
	
	private static void getMovieview(Connection conn) {
		MovieView mv = new MovieView(conn);
		System.out.println(mv.toJson());
	}
	
	private static void createAddress(Connection conn, String address, int postalcode, String state) {
		Addresses a = new Addresses(conn);
		System.out.println(a.createAddress(address, postalcode, state));
	}
	
	private static void createActor(Connection conn, String name, String hometown, int age, int postalcode) {
		Actors a = new Actors(conn);
		System.out.println(a.createActor(name, hometown, age, postalcode));
	}
	
	private static void assignActor(Connection conn, String movieTitle, String actorName) {
		Actors a = new Actors(conn);
		System.out.println(a.assignActor(movieTitle, actorName));
	}

	private static void createGenre(Connection conn, String genrename) {
		Genres g = new Genres(conn);
		System.out.println(g.createGenre(genrename));
	}
	
	private static void assignGenre(Connection conn, String movietitle, String genrename) {
		Genres g = new Genres(conn);
		System.out.println(g.assignGenre(movietitle, genrename));
	}
	
	private static void removeGenre(Connection conn, String movietitle, String genrename) {
		Genres g = new Genres(conn);
		System.out.println(g.removeGenre(movietitle, genrename));
	}

	private static void updateDirector(Connection conn, String newName, int newAge, String newHometown, String name) {
		Directors d = new Directors(conn);
		System.out.println(d.updateDirector(newName, newAge, newHometown, name));
	}

	private static void createMovie(Connection conn, String title, String director, int year) {
		Movies m = new Movies(conn);
		System.out.println(m.createMovie(title, director, year));
	}
	
	private static void updateMovie(Connection conn, String movieTitle, String newTitle, String newDirector) {
		Movies m = new Movies(conn);
		System.out.println(m.updateMovie(movieTitle, newTitle, newDirector));
	}
}
