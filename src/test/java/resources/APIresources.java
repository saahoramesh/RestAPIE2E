package resources;

public enum APIresources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	UpdatePlaceAPI("/maps/api/place/update/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resourse;
	
 APIresources(String resourse)
 {
	 this.resourse=resourse;
 }
 public String getResourse()
 {
	 return resourse;
 }
}
