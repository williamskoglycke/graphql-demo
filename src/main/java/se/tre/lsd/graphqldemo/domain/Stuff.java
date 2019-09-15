package se.tre.lsd.graphqldemo.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Stuff {

    String name;
    String color;

}
