package serializableexamples;

import java.io.Serializable;

public class Creature extends AbstractCreature implements Serializable {

    String creatureType;

    // At the deserialization of the Animal class, the no-args constructor is called for the Creature class
    // This constructor is called only if the class does not implement the Serializable interface
    // If the Serializable interface is implemented, no constructor is called during the deserialization process
    public Creature() {
        System.out.println("Creating the Creature during deserialization");
    }

    public Creature(String creatureType) {
        this.creatureType = creatureType;
    }

    public String getCreatureType() {
        return creatureType;
    }
}
