module karmic {
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;

    requires spring.expression;
    requires spring.core;

    requires org.junit.jupiter.api;

    opens combat to com.fasterxml.jackson.databind, spring.core;

    exports gui;
    exports engine;
    exports combat;
    exports eventListener;
    exports event;
}
