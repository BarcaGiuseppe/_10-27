package model;

import datasource.ConnectionDBH2;
public class Person {

    public static class Builder<T extends Builder<T>> {
        protected String id;
        protected String name;
        protected String surname;
        protected Integer age;
        protected String email;
        protected String phoneNumber;
        public Builder() {

        }

        public Builder addId(String id) {
            this.id = id;
            return this;
        }

        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Builder addSurname(String surname) {
            this.surname = surname;
            return getThis();
        }

        public Builder addAge(Integer age) {
            this.age = age;
            return getThis();
        }

        public Builder addEmail(String email) {
            this.email = email;
            return getThis();
        }

        public Builder addPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return getThis();
        }

        public Person.Builder getThis(){ return this; };

        public Person build() {
            return new Person(this);
        }

    }

    final private String id;
    final private String name;
    final private String surname;
    final private Integer age;
    final private String email;
    final private String phoneNumber;

    protected <T extends Builder<T>> Person(Builder<T> builder) {
        this.id = builder.id != null ? builder.id : ConnectionDBH2.getMaxID();
        this.name = builder.name;
        this.surname = builder.surname;
        this.age = builder.age;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getId() {
        return this.id;
    }


    public String getName() {
        return this.name;
    }


    public String getSurname() {
        return this.surname;
    }


    public Integer getAge() {
        return this.age;
    }


    public String getEmail() {
        return this.email;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }

}
