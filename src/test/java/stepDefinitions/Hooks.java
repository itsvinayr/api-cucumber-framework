package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlaceAPI")
    public void beforeScenario() throws IOException {
        if(StepDefinitions.place_id==null){
            System.out.println("Running Before Hook");
            StepDefinitions stepDefinitions = new StepDefinitions();
            stepDefinitions.add_place_payload("vinay1", "French-IN", "local address");
            stepDefinitions.user_calls_with_post_http_call("addPlaceAPI", "POST");
            stepDefinitions.api_call_is_success_with_status_code(200);
        }
    }
}
