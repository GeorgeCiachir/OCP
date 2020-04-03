package tyrwithresources;

class AutoClosableResource implements AutoCloseable {

    @Override
    public void close() {
        System.out.println("Closing the tyrwithresources.AutoClosableResource");
    }

    public void doSomething() {
        System.out.println("tyrwithresources.AutoClosableResource doing something");
    }
}
