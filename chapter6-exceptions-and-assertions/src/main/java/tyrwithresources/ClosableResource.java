package tyrwithresources;

import java.io.Closeable;

class ClosableResource implements Closeable {

    @Override
    public void close() {
        System.out.println("Closing the tyrwithresources.ClosableResource");
    }

    public void doSomething() {
        System.out.println("tyrwithresources.ClosableResource doing something");
    }
}
