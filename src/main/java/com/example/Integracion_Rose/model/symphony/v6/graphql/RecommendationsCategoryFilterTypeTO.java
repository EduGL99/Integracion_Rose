package com.example.Integracion_Rose.model.symphony.v6.graphql;


public enum RecommendationsCategoryFilterTypeTO {

    NAME("NAME");

    private final String graphqlName;

    RecommendationsCategoryFilterTypeTO(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
