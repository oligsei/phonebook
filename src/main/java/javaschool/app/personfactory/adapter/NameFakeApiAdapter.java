package javaschool.app.personfactory.adapter;

import javaschool.app.Person;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NameFakeApiAdapter implements IApiAdapter {
    private static final String url = "http://api.namefake.com/latvian-latvia";

    @Override
    public List<Person> generate(Integer count) {
        List<Person> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            try (InputStream inputStream = new URL(NameFakeApiAdapter.url).openStream()) {
                result.add(this.fromJSON(new JSONObject(new JSONTokener(inputStream))));
            } catch (IOException e) {
            }
        }
        return result;
    }

    @Override
    public Person fromJSON(JSONObject json) {
        Person person = new Person(json.getString("name"));

        person.setAddress(json.getString("address").replace("\n", "; "));
        person.setEmail(json.getString("email_u").toLowerCase() + "@" + json.getString("email_d"));
        person.addPhone(json.getString("phone_h"));
        person.addPhone(json.getString("phone_w"));

        return person;
    }
}
