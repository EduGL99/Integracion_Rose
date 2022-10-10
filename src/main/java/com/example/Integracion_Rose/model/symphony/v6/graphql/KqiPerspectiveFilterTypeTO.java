package com.example.Integracion_Rose.model.symphony.v6.graphql;


public enum KqiPerspectiveFilterTypeTO {

    NAME("NAME");

    private final String graphqlName;

    KqiPerspectiveFilterTypeTO(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
