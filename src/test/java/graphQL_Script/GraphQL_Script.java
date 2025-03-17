package graphQL_Script;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQL_Script {

	public static void main(String[] args) {

		// Query
		int characterId = 13212;
		
		String response = given().log().all().header("Content-Type", "application/json").body(
				"{\"query\":\"query($characterId :Int! , $episodeId : Int!, $locationId : Int!)\\n{\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    status\\n    id\\n    type\\n  }\\n  location(locationId: $locationId) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId) {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name: \\\"Rahul\\\"}) {\\n    info {\\n      count\\n    }\\n    result {\\n      name\\n      type\\n    }\\n  }\\n  episodes(filters: {episode: \\\"hulu\\\"}) {\\n    result {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":"+characterId+",\"episodeId\":13504,\"locationId\":19203}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asPrettyString();

		System.out.println("response: " + response);

		JsonPath js = new JsonPath(response);
		String characterName = js.getString("data.character.name");
		Assert.assertEquals(characterName, "Robin");

	

	//Mutations
	
	
	String newcharacterName = "RobinHood";
	
	String Mutation_response = given().log().all().header("Content-Type", "application/json").body(
			"{\"query\":\"mutation ($locationName: String!, $characterName: String!, $episodeName: String!) {\\n  createLocation(location: {name: $locationName, type: \\\"Southzone\\\", dimension: \\\"234\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name: $characterName, type: \\\"Macho\\\", status: \\\"dead\\\", species: \\\"fantasy\\\", gender: \\\"Male\\\", image: \\\"png\\\", originId: 19198, locationId: 19198}) {\\n    id\\n  }\\n  createEpisode(episode: {name: $episodeName, air_date: \\\"1990 June\\\", episode: \\\"Netflix\\\"}) {\\n    id\\n  }\\n  deleteLocations(locationIds: [19200, 19201]) {\\n    locationsDeleted\\n  }\\n}\\n\",\"variables\":{\"locationName\":\"NewZealand\",\"characterName\":\""+newcharacterName+"\",\"episodeName\":\"Manifest\"}}")
			.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asPrettyString();

	System.out.println("Mutation_response: " + Mutation_response);

	JsonPath js1 = new JsonPath(Mutation_response);
	
}

}

// Note: Body is copied from Chrome inspect --> network tab https://rahulshettyacademy.com/gq/graphql --> Select Name from left: graphql --> Payload - On Request Payload click on view source & copy the json 
