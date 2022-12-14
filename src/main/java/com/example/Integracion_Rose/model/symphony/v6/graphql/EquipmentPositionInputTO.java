package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer;

import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipmentPositionInputTO implements java.io.Serializable {

    private String id;
    @javax.validation.constraints.NotNull
    private String name;
    private Integer index;
    private String visibleLabel;

    public EquipmentPositionInputTO() {
    }

    public EquipmentPositionInputTO(String id, String name, Integer index, String visibleLabel) {
        this.id = id;
        this.name = name;
        this.index = index;
        this.visibleLabel = visibleLabel;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getVisibleLabel() {
        return visibleLabel;
    }

    public void setVisibleLabel(String visibleLabel) {
        this.visibleLabel = visibleLabel;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{ ", " }");
        if (id != null) {
            joiner.add("id: " + GraphQLRequestSerializer.getEntry(id));
        }
        if (name != null) {
            joiner.add("name: " + GraphQLRequestSerializer.getEntry(name));
        }
        if (index != null) {
            joiner.add("index: " + GraphQLRequestSerializer.getEntry(index));
        }
        if (visibleLabel != null) {
            joiner.add("visibleLabel: " + GraphQLRequestSerializer.getEntry(visibleLabel));
        }
        return joiner.toString();
    }

    public static class Builder {

        private String id;
        private String name;
        private Integer index;
        private String visibleLabel;

        public Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setIndex(Integer index) {
            this.index = index;
            return this;
        }

        public Builder setVisibleLabel(String visibleLabel) {
            this.visibleLabel = visibleLabel;
            return this;
        }


        public EquipmentPositionInputTO build() {
            return new EquipmentPositionInputTO(id, name, index, visibleLabel);
        }

    }
}
