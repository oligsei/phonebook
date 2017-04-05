package javaschool.app.personfactory.adapter;

import javaschool.app.Person;
import org.json.JSONObject;

import java.util.List;

public interface IApiAdapter {
    List<Person> generate(Integer count);
    Person fromJSON(JSONObject json);
}
