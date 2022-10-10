package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResult;

import java.util.Map;


public class RemoveCounterMutationResponse extends GraphQLResult<Map<String, String>> {

    private static final String OPERATION_NAME = "removeCounter";

    public RemoveCounterMutationResponse() {
    }

    public String removeCounter() {
        Map<String, String> data = getData();
        return data != null ? data.get(OPERATION_NAME) : null;
    }

}