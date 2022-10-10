package com.example.Integracion_Rose.model.symphony.v6.graphql;


public enum PermissionValueTO {

    YES("YES"),
    NO("NO"),
    BY_CONDITION("BY_CONDITION");

    private final String graphqlName;

    PermissionValueTO(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
