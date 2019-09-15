package se.tre.lsd.graphqldemo.api.datafetch;

import graphql.schema.DataFetcher;

public class HelloFetcher {

    public static DataFetcher getHelloWorldDataFetcher() {
        return environment -> "world";
    }

    public static DataFetcher getEchoDataFetcher() {
        return environment -> environment.getArgument("toEcho");
    }


}
