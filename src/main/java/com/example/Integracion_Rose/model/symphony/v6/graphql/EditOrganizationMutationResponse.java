package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResult;

import java.util.Map;


public class EditOrganizationMutationResponse extends GraphQLResult<Map<String, OrganizationTO>> {

    private static final String OPERATION_NAME = "editOrganization";

    public EditOrganizationMutationResponse() {
    }

    public OrganizationTO editOrganization() {
        Map<String, OrganizationTO> data = getData();
        return data != null ? data.get(OPERATION_NAME) : null;
    }

}
