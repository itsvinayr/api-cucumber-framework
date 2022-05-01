package testData;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static AddPlace getAddPlacePayload(String name, String language, String address){
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy("50");
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress(address);
        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setTypes(types);
        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage(language);
        Location location = new Location();
        location.setLat("-38.383494");
        location.setLng("33.427362");
        addPlace.setLocation(location);
        return addPlace;
    }

    public static String getDeletePlacePayload(String place_id){
        return "{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}";
    }
}
