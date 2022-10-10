package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResult;

import java.util.Map;


public class RemoveVendorMutationResponse extends GraphQLResult<Map<String, String>> {

    private static final String OPERATION_NAME = "removeVendor";

    public RemoveVendorMutationResponse() {
    }

    public String removeVendor() {
        Map<String, String> data = getData();
        return data != null ? data.get(OPERATION_NAME) : null;
    }

}
