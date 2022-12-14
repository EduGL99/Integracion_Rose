package com.example.Integracion_Rose.model.symphony.v6.graphql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kobylynskyi.graphql.codegen.model.graphql.GraphQLRequestSerializer;

import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddKqiComparatorInputTO implements java.io.Serializable {

    @javax.validation.constraints.NotNull
    private String kqiTargetFk;
    @javax.validation.constraints.NotNull
    private String comparatorFk;
    private double number;
    @javax.validation.constraints.NotNull
    private String comparatorType;

    public AddKqiComparatorInputTO() {
    }

    public AddKqiComparatorInputTO(String kqiTargetFk, String comparatorFk, double number, String comparatorType) {
        this.kqiTargetFk = kqiTargetFk;
        this.comparatorFk = comparatorFk;
        this.number = number;
        this.comparatorType = comparatorType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getKqiTargetFk() {
        return kqiTargetFk;
    }

    public void setKqiTargetFk(String kqiTargetFk) {
        this.kqiTargetFk = kqiTargetFk;
    }

    public String getComparatorFk() {
        return comparatorFk;
    }

    public void setComparatorFk(String comparatorFk) {
        this.comparatorFk = comparatorFk;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getComparatorType() {
        return comparatorType;
    }

    public void setComparatorType(String comparatorType) {
        this.comparatorType = comparatorType;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{ ", " }");
        if (kqiTargetFk != null) {
            joiner.add("kqiTargetFk: " + GraphQLRequestSerializer.getEntry(kqiTargetFk));
        }
        if (comparatorFk != null) {
            joiner.add("comparatorFk: " + GraphQLRequestSerializer.getEntry(comparatorFk));
        }
        joiner.add("number: " + GraphQLRequestSerializer.getEntry(number));
        if (comparatorType != null) {
            joiner.add("comparatorType: " + GraphQLRequestSerializer.getEntry(comparatorType));
        }
        return joiner.toString();
    }

    public static class Builder {

        private String kqiTargetFk;
        private String comparatorFk;
        private double number;
        private String comparatorType;

        public Builder() {
        }

        public Builder setKqiTargetFk(String kqiTargetFk) {
            this.kqiTargetFk = kqiTargetFk;
            return this;
        }

        public Builder setComparatorFk(String comparatorFk) {
            this.comparatorFk = comparatorFk;
            return this;
        }

        public Builder setNumber(double number) {
            this.number = number;
            return this;
        }

        public Builder setComparatorType(String comparatorType) {
            this.comparatorType = comparatorType;
            return this;
        }


        public AddKqiComparatorInputTO build() {
            return new AddKqiComparatorInputTO(kqiTargetFk, comparatorFk, number, comparatorType);
        }

    }
}
