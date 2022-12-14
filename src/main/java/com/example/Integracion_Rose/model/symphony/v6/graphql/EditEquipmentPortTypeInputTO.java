package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer;

import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditEquipmentPortTypeInputTO implements java.io.Serializable {

    @javax.validation.constraints.NotNull
    private String id;
    @javax.validation.constraints.NotNull
    private String name;
    @javax.validation.constraints.NotNull
    private java.util.List<PropertyTypeInputTO> properties;
    @javax.validation.constraints.NotNull
    private java.util.List<PropertyTypeInputTO> linkProperties;

    public EditEquipmentPortTypeInputTO() {
    }

    public EditEquipmentPortTypeInputTO(String id, String name, java.util.List<PropertyTypeInputTO> properties, java.util.List<PropertyTypeInputTO> linkProperties) {
        this.id = id;
        this.name = name;
        this.properties = properties;
        this.linkProperties = linkProperties;
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

    public java.util.List<PropertyTypeInputTO> getProperties() {
        return properties;
    }

    public void setProperties(java.util.List<PropertyTypeInputTO> properties) {
        this.properties = properties;
    }

    public java.util.List<PropertyTypeInputTO> getLinkProperties() {
        return linkProperties;
    }

    public void setLinkProperties(java.util.List<PropertyTypeInputTO> linkProperties) {
        this.linkProperties = linkProperties;
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
        if (properties != null) {
            joiner.add("properties: " + GraphQLRequestSerializer.getEntry(properties));
        }
        if (linkProperties != null) {
            joiner.add("linkProperties: " + GraphQLRequestSerializer.getEntry(linkProperties));
        }
        return joiner.toString();
    }

    public static class Builder {

        private String id;
        private String name;
        private java.util.List<PropertyTypeInputTO> properties;
        private java.util.List<PropertyTypeInputTO> linkProperties;

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

        public Builder setProperties(java.util.List<PropertyTypeInputTO> properties) {
            this.properties = properties;
            return this;
        }

        public Builder setLinkProperties(java.util.List<PropertyTypeInputTO> linkProperties) {
            this.linkProperties = linkProperties;
            return this;
        }


        public EditEquipmentPortTypeInputTO build() {
            return new EditEquipmentPortTypeInputTO(id, name, properties, linkProperties);
        }

    }
}
