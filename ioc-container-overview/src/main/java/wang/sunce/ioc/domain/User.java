package wang.sunce.ioc.domain;

import org.springframework.core.io.Resource;
import wang.sunce.ioc.domain.enums.City;

import java.util.Arrays;
import java.util.List;

public class User {

    private String name;

    private Integer age;

    private City city;

    private City[] workCities;

    private List<City> liveCities;

    private Resource location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLiveCities() {
        return liveCities;
    }

    public void setLiveCities(List<City> liveCities) {
        this.liveCities = liveCities;
    }

    public Resource getLocation() {
        return location;
    }

    public void setLocation(Resource location) {
        this.location = location;
    }

    public static User createUser() {
        User user = new User();
        user.setName("sunce");
        user.setAge(18);
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", liveCities=" + liveCities +
                ", location=" + location +
                '}';
    }
}
