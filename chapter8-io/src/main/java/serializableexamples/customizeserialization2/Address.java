package serializableexamples.customizeserialization2;

import java.io.Serializable;

public class Address implements Serializable {

    private String city;

    public Address(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return city;
    }
}
