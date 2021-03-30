module zoo.staff {

    // No longer needs to import this dependencies, because they are provided as transitive dependencies
    // by the talks module
    // requires zoo.animal.feeding;
    // requires zoo.animal.care;

    // This provides the above dependencies as transitive
    requires zoo.animal.talks;
}
