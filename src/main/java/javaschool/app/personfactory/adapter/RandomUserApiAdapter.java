package javaschool.app.personfactory.adapter;

import javaschool.app.Person;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RandomUserApiAdapter implements IApiAdapter {
    private static final String url = "https://randomuser.me/api/";

    @Override
    public List<Person> generate(Integer count) {
        List<Person> result = new ArrayList<>();
        JSONObject response;
        try (InputStream inputStream = new URL(RandomUserApiAdapter.url + "?results=" + count).openStream()) {
            response = new JSONObject(new JSONTokener(inputStream));
        } catch (IOException e) {
            return result;
        }
        response.getJSONArray("results").forEach(o -> result.add(this.fromJSON((JSONObject) o)));
        return result;
    }

    @Override
    public Person fromJSON(JSONObject json) {
        JSONObject address = json.getJSONObject("location");
        JSONObject name = json.getJSONObject("name");
        Person person = new Person(
                        name.getString("first") + " " +
                        name.getString("last")
        );

        person.setAddress(
                address.getString("city") + ", " +
                        address.getString("state") + ", " +
                        address.getString("street")
        );
        person.setEmail(json.getString("email"));
        person.addPhone(json.getString("cell"));

        return person;
    }
}
