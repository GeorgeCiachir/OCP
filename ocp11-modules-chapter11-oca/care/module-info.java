module zoo.animal.care {

    exports zoo.animal.care.medical;

    //This is not exposed to modules that use this module
    //requires zoo.animal.feeding;

    //This is now exposed to modules that use this module -> it is basically a "composed" requires and exports
    //Other modules that use this module won't need to import the zoo.animal.feeding module as it is already provided as
    //a transitive dependency
    //This is now exposed to zoo.animal.talks and zoo.staff
    requires transitive zoo.animal.feeding;
}
