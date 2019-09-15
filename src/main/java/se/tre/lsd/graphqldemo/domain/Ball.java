package se.tre.lsd.graphqldemo.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class Ball {

    String name;
    String color;

}
