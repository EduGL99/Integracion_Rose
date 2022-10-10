package com.example.Integracion_Rose.configuration.graphql;

import com.example.Integracion_Rose.model.symphony.FragmentNode;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLOperationRequest;

public interface CustomGraphQLOperationRequest extends GraphQLOperationRequest {

    FragmentNode getInlineFragment();
}
