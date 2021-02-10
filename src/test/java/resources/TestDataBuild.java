package resources;

public class TestDataBuild {

	
	public static String jsonBody(String name, String language, String address)
	{
		String json= "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \""+name+"\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \""+address+"\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \""+language+"\"\r\n" + 
				"}\r\n" + 
				"";
		return json;
	}
	public static String updatePlaceApi(String placeId)
	{
		String json="{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\"70 winter walk, USA\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"";
		return json;
	}
	public static String deletePlaceApi(String placeId)
	{
		String json="{\r\n" + 
				"    \"place_id\":\""+placeId+"\"\r\n" + 
				"}";
		return json;
	}
}
