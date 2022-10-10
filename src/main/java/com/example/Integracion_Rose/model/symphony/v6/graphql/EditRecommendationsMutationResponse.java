package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResult;

import java.util.Map;


public class EditRecommendationsMutationResponse extends GraphQLResult<Map<String, RecommendationsTO>> {

    private static final String OPERATION_NAME = "editRecommendations";

    public EditRecommendationsMutationResponse() {
    }

    public RecommendationsTO editRecommendations() {
        Map<String, RecommendationsTO> data = getData();
        return data != null ? data.get(OPERATION_NAME) : null;
    }

}
