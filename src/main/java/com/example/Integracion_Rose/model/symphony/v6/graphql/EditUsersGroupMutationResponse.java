package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLResult;

import java.util.Map;


public class EditUsersGroupMutationResponse extends GraphQLResult<Map<String, UsersGroupTO>> {

    private static final String OPERATION_NAME = "editUsersGroup";

    public EditUsersGroupMutationResponse() {
    }

    public UsersGroupTO editUsersGroup() {
        Map<String, UsersGroupTO> data = getData();
        return data != null ? data.get(OPERATION_NAME) : null;
    }

}
