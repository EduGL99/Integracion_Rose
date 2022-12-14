package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResult;

import java.util.Map;


public class EditKqiPerspectiveMutationResponse extends GraphQLResult<Map<String, KqiPerspectiveTO>> {

    private static final String OPERATION_NAME = "editKqiPerspective";

    public EditKqiPerspectiveMutationResponse() {
    }

    public KqiPerspectiveTO editKqiPerspective() {
        Map<String, KqiPerspectiveTO> data = getData();
        return data != null ? data.get(OPERATION_NAME) : null;
    }

}
