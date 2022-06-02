package helpers;

import java.util.ArrayList;

public class jsonHelper {

	// Generic method that can be used by any type of database object
	// Encases list of objects (example Actors) with [] and arrayLabel is the name of array
	public static String toJsonArray(String arrayLabel, String content) {

		String lastCharacter = content.substring(content.length() - 1);
		if (lastCharacter.equals(",")) {
			content = content.substring(0, content.lastIndexOf(",")); // Removes last comma
		}

		String pattern = "\"%s\": [ %s ]";
		String returnString = String.format(pattern, arrayLabel, content);
		return returnString;
	}

	// Generic method used in beans to format any amount of variables to JSON
	public static String toJsonObject(ArrayList<keyvaluepair> content) {
		String jsonString = "";
		String itemPattern = "\"%s\" : \"%s\", ";
		for (keyvaluepair item : content) {
			jsonString += String.format(itemPattern, item.key, item.value);
		}
		jsonString = jsonString.substring(0, jsonString.lastIndexOf(",")); // Removes last comma

		String objectPattern = "{ %s }";

		return String.format(objectPattern, jsonString);
	}

	// Method that formats all given JSON objects (example Actors with Addresses)
	// into a document
	public static String toJsonDocument(ArrayList<String> content) {
		String jsonString = "";
		for (String item : content) {
			jsonString += item + ",";
		}
		jsonString = jsonString.substring(0, jsonString.lastIndexOf(",")); // Removes last comma

		String objectPattern = "{ %s }";

		return String.format(objectPattern, jsonString);
	}

	// Adds curly brackets to all listed objects in JSON file
	public static String toJsonDocument(String content) {
		return "{" + content + "}";
	}

}
