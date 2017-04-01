package javaschool.app.personfactory.adapter;

import javaschool.app.Person;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NameFakeApiAdapter implements IApiAdapter {
    private static final String url = "http://api.namefake.com/latvian-latvia";

    @Override
    public List<Person> generate(Integer count) {
        List<Person> result = new ArrayList<>();
        URL url;
        try {
            url = new URL(NameFakeApiAdapter.url);
        } catch (MalformedURLException e) {
            return result;
        }

        for (int i = 0; i < count; i++) {
            try {
                result.add(this.fromJSON(new JSONObject(new JSONTokener(url.openStream()))));
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

    private URL getURL() {
        try {
            return new URL(NameFakeApiAdapter.url);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
