package se.tre.lsd.graphqldemo.api.datafetch;

import graphql.schema.DataFetcher;
import se.tre.lsd.graphqldemo.domain.Stuff;

public class StuffFetcher {

    public static DataFetcher getStuff() {
        return environment -> Stuff.of("Stuffy", "50 shades of stuffing");
    }

}
