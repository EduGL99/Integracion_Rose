package com.example.Integracion_Rose.model.symphony.v6.graphql;


public enum ExitPointRoleTO {

    DEFAULT("DEFAULT"),
    DECISION("DECISION");

    private final String graphqlName;

    ExitPointRoleTO(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
