module zoo.animal.talks {
//    exports zoo.animal.talks.content to zoo.staff; //zoo.staff needs to be discoverable by this module, in the "mods" folder
    exports zoo.animal.talks.content;
    exports zoo.animal.talks.media;
    exports zoo.animal.talks.schedule;

    // No longer needs to import this module as it is provided by the zoo.animal.care as a transitive dependency
    // requires zoo.animal.feeding;

    // requires zoo.animal.care;
    // also expose zoo.animal.care to zoo.staff
    requires transitive zoo.animal.care;
}
