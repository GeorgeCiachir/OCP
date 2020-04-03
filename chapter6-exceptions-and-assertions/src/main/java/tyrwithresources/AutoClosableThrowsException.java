package tyrwithresources;

public class AutoClosableThrowsException implements AutoCloseable {

    @Override
    public void close() {
        throw new IllegalStateException("Cannot close resource");
    }
}
