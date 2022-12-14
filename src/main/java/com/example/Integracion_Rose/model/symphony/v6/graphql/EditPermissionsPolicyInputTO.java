package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer;

import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditPermissionsPolicyInputTO implements java.io.Serializable {

    @javax.validation.constraints.NotNull
    private String id;
    private String name;
    private String description;
    private Boolean isGlobal;
    private Boolean isMulticontractor;
    private InventoryPolicyInputTO inventoryInput;
    private WorkforcePolicyInputTO workforceInput;
    private AutomationPolicyInputTO automationInput;
    private AssurancePolicyInputTO assuranceInput;
    @javax.validation.constraints.NotNull
    private java.util.List<String> groups;

    public EditPermissionsPolicyInputTO() {
    }

    public EditPermissionsPolicyInputTO(String id, String name, String description, Boolean isGlobal, Boolean isMulticontractor, InventoryPolicyInputTO inventoryInput, WorkforcePolicyInputTO workforceInput, AutomationPolicyInputTO automationInput, AssurancePolicyInputTO assuranceInput, java.util.List<String> groups) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isGlobal = isGlobal;
        this.isMulticontractor = isMulticontractor;
        this.inventoryInput = inventoryInput;
        this.workforceInput = workforceInput;
        this.automationInput = automationInput;
        this.assuranceInput = assuranceInput;
        this.groups = groups;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(Boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public Boolean getIsMulticontractor() {
        return isMulticontractor;
    }

    public void setIsMulticontractor(Boolean isMulticontractor) {
        this.isMulticontractor = isMulticontractor;
    }

    public InventoryPolicyInputTO getInventoryInput() {
        return inventoryInput;
    }

    public void setInventoryInput(InventoryPolicyInputTO inventoryInput) {
        this.inventoryInput = inventoryInput;
    }

    public WorkforcePolicyInputTO getWorkforceInput() {
        return workforceInput;
    }

    public void setWorkforceInput(WorkforcePolicyInputTO workforceInput) {
        this.workforceInput = workforceInput;
    }

    public AutomationPolicyInputTO getAutomationInput() {
        return automationInput;
    }

    public void setAutomationInput(AutomationPolicyInputTO automationInput) {
        this.automationInput = automationInput;
    }

    public AssurancePolicyInputTO getAssuranceInput() {
        return assuranceInput;
    }

    public void setAssuranceInput(AssurancePolicyInputTO assuranceInput) {
        this.assuranceInput = assuranceInput;
    }

    public java.util.List<String> getGroups() {
        return groups;
    }

    public void setGroups(java.util.List<String> groups) {
        this.groups = groups;
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
        if (description != null) {
            joiner.add("description: " + GraphQLRequestSerializer.getEntry(description));
        }
        if (isGlobal != null) {
            joiner.add("isGlobal: " + GraphQLRequestSerializer.getEntry(isGlobal));
        }
        if (isMulticontractor != null) {
            joiner.add("isMulticontractor: " + GraphQLRequestSerializer.getEntry(isMulticontractor));
        }
        if (inventoryInput != null) {
            joiner.add("inventoryInput: " + GraphQLRequestSerializer.getEntry(inventoryInput));
        }
        if (workforceInput != null) {
            joiner.add("workforceInput: " + GraphQLRequestSerializer.getEntry(workforceInput));
        }
        if (automationInput != null) {
            joiner.add("automationInput: " + GraphQLRequestSerializer.getEntry(automationInput));
        }
        if (assuranceInput != null) {
            joiner.add("assuranceInput: " + GraphQLRequestSerializer.getEntry(assuranceInput));
        }
        if (groups != null) {
            joiner.add("groups: " + GraphQLRequestSerializer.getEntry(groups));
        }
        return joiner.toString();
    }

    public static class Builder {

        private String id;
        private String name;
        private String description;
        private Boolean isGlobal;
        private Boolean isMulticontractor;
        private InventoryPolicyInputTO inventoryInput;
        private WorkforcePolicyInputTO workforceInput;
        private AutomationPolicyInputTO automationInput;
        private AssurancePolicyInputTO assuranceInput;
        private java.util.List<String> groups;

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

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setIsGlobal(Boolean isGlobal) {
            this.isGlobal = isGlobal;
            return this;
        }

        public Builder setIsMulticontractor(Boolean isMulticontractor) {
            this.isMulticontractor = isMulticontractor;
            return this;
        }

        public Builder setInventoryInput(InventoryPolicyInputTO inventoryInput) {
            this.inventoryInput = inventoryInput;
            return this;
        }

        public Builder setWorkforceInput(WorkforcePolicyInputTO workforceInput) {
            this.workforceInput = workforceInput;
            return this;
        }

        public Builder setAutomationInput(AutomationPolicyInputTO automationInput) {
            this.automationInput = automationInput;
            return this;
        }

        public Builder setAssuranceInput(AssurancePolicyInputTO assuranceInput) {
            this.assuranceInput = assuranceInput;
            return this;
        }

        public Builder setGroups(java.util.List<String> groups) {
            this.groups = groups;
            return this;
        }


        public EditPermissionsPolicyInputTO build() {
            return new EditPermissionsPolicyInputTO(id, name, description, isGlobal, isMulticontractor, inventoryInput, workforceInput, automationInput, assuranceInput, groups);
        }

    }
}
