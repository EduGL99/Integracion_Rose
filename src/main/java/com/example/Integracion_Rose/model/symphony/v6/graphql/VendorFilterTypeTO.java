package com.example.Integracion_Rose.model.symphony.v6.graphql;


public enum VendorFilterTypeTO {

    NAME("NAME");

    private final String graphqlName;

    VendorFilterTypeTO(String graphqlName) {
        this.graphqlName = graphqlName;
    }

    @Override
    public String toString() {
        return this.graphqlName;
    }

}
