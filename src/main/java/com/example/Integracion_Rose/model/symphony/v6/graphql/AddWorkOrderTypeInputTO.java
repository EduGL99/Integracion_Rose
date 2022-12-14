package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer;

import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddWorkOrderTypeInputTO implements java.io.Serializable {

    @javax.validation.constraints.NotNull
    private String name;
    private String description;
    private java.util.List<PropertyTypeInputTO> properties;
    @javax.validation.constraints.NotNull
    private java.util.List<CheckListCategoryDefinitionInputTO> checkListCategories;
    private Boolean assigneeCanCompleteWorkOrder;
    private Double duration;

    public AddWorkOrderTypeInputTO() {
    }

    public AddWorkOrderTypeInputTO(String name, String description, java.util.List<PropertyTypeInputTO> properties, java.util.List<CheckListCategoryDefinitionInputTO> checkListCategories, Boolean assigneeCanCompleteWorkOrder, Double duration) {
        this.name = name;
        this.description = description;
        this.properties = properties;
        this.checkListCategories = checkListCategories;
        this.assigneeCanCompleteWorkOrder = assigneeCanCompleteWorkOrder;
        this.duration = duration;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.List<PropertyTypeInputTO> getProperties() {
        return properties;
    }

    public void setProperties(java.util.List<PropertyTypeInputTO> properties) {
        this.properties = properties;
    }

    public java.util.List<CheckListCategoryDefinitionInputTO> getCheckListCategories() {
        return checkListCategories;
    }

    public void setCheckListCategories(java.util.List<CheckListCategoryDefinitionInputTO> checkListCategories) {
        this.checkListCategories = checkListCategories;
    }

    public Boolean getAssigneeCanCompleteWorkOrder() {
        return assigneeCanCompleteWorkOrder;
    }

    public void setAssigneeCanCompleteWorkOrder(Boolean assigneeCanCompleteWorkOrder) {
        this.assigneeCanCompleteWorkOrder = assigneeCanCompleteWorkOrder;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{ ", " }");
        if (name != null) {
            joiner.add("name: " + GraphQLRequestSerializer.getEntry(name));
        }
        if (description != null) {
            joiner.add("description: " + GraphQLRequestSerializer.getEntry(description));
        }
        if (properties != null) {
            joiner.add("properties: " + GraphQLRequestSerializer.getEntry(properties));
        }
        if (checkListCategories != null) {
            joiner.add("checkListCategories: " + GraphQLRequestSerializer.getEntry(checkListCategories));
        }
        if (assigneeCanCompleteWorkOrder != null) {
            joiner.add("assigneeCanCompleteWorkOrder: " + GraphQLRequestSerializer.getEntry(assigneeCanCompleteWorkOrder));
        }
        if (duration != null) {
            joiner.add("duration: " + GraphQLRequestSerializer.getEntry(duration));
        }
        return joiner.toString();
    }

    public static class Builder {

        private String name;
        private String description;
        private java.util.List<PropertyTypeInputTO> properties;
        private java.util.List<CheckListCategoryDefinitionInputTO> checkListCategories;
        private Boolean assigneeCanCompleteWorkOrder;
        private Double duration;

        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setProperties(java.util.List<PropertyTypeInputTO> properties) {
            this.properties = properties;
            return this;
        }

        public Builder setCheckListCategories(java.util.List<CheckListCategoryDefinitionInputTO> checkListCategories) {
            this.checkListCategories = checkListCategories;
            return this;
        }

        public Builder setAssigneeCanCompleteWorkOrder(Boolean assigneeCanCompleteWorkOrder) {
            this.assigneeCanCompleteWorkOrder = assigneeCanCompleteWorkOrder;
            return this;
        }

        public Builder setDuration(Double duration) {
            this.duration = duration;
            return this;
        }


        public AddWorkOrderTypeInputTO build() {
            return new AddWorkOrderTypeInputTO(name, description, properties, checkListCategories, assigneeCanCompleteWorkOrder, duration);
        }

    }
}
