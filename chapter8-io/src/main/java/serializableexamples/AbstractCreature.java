package serializableexamples;

public abstract class AbstractCreature {

    // This constructor is always called during the deserialization process
    // Of course, it is also called when initially constructing the Animal instances, in memory
    // In the example ObjectInputStreamReadAndOutputStreamsReadWriteExamples this constructor will be called 4 times:
    // 2 times for creating th 2 Animal instances and 2 times for their deserialization them
    public AbstractCreature() {
        System.out.println("Creating the AbstractCreature");
    }
}
