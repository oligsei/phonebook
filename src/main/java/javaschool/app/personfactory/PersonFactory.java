package javaschool.app.personfactory;

import javaschool.app.Person;
import javaschool.app.personfactory.adapter.NameFakeApiAdapter;
import javaschool.app.personfactory.adapter.RandomUserApiAdapter;

import java.util.List;


public class PersonFactory {
    enum API_NAME {
        RANDOM_USER,
        NAME_FAKE
    }

    public static final API_NAME API_RANDOM_USER = API_NAME.RANDOM_USER;
    public static final API_NAME API_NAME_FAKE = API_NAME.NAME_FAKE;

    public static List<Person> generate() {
        return PersonFactory.generate(1, PersonFactory.API_NAME_FAKE);
    }

    public static List<Person> generate(Integer count) {
        return PersonFactory.generate(count, PersonFactory.API_NAME_FAKE);
    }

    public static List<Person> generate(API_NAME api) {
        return PersonFactory.generate(1, api);
    }

    public static List<Person> generate(Integer count, API_NAME api) {
        switch (api) {
            case RANDOM_USER:
                return new RandomUserApiAdapter().generate(count);
            default:
                return new NameFakeApiAdapter().generate(count);
        }
    }
}