package se.tre.lsd.graphqldemo.api.datafetch;

import graphql.schema.DataFetcher;
import se.tre.lsd.graphqldemo.domain.Ball;

public class BallFetcher {

    public static DataFetcher getBall() {
        return environment -> {

            System.out.println("query to ball repo");

            final String color = environment.getArgument("color");
            final String name = environment.getArgument("name");
            return Ball.of(name, color);
        };
    }

}
