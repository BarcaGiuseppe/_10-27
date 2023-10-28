package util;

public class ConcreteObserver implements Observer {
    private final String emailOwner;
    private final String emailPerson;
    private final String phoneNumberPerson;
    private final String idPerson;
    public ConcreteObserver(Builder builder){
        this.emailOwner = "gbarca1995@gmail.com";
        this.emailPerson = builder.emailPerson;
        this.phoneNumberPerson = builder.phoneNumberPerson;
        this.idPerson = builder.idPerson;
    }

    public static class Builder{
        private String emailPerson;
        private String phoneNumberPerson;
        private String idPerson;

        public Builder(){}

        public Builder setEmailPerson(String emailPerson){
            this.emailPerson = emailPerson;
            return this;
        }

        public Builder setPhoneNumberPerson(String phoneNumberPerson){
            this.phoneNumberPerson = phoneNumberPerson;
            return this;
        }
        public Builder setIdPerson(String idPerson){
            this.idPerson = idPerson;
            return this;
        }
        public ConcreteObserver build() {
            return new ConcreteObserver(this);
        }
    }

    public String getEmailOwner(){
        return this.emailOwner;
    }
    public String getEmailPerson(){
        return this.emailPerson;
    }

    public String getPhoneNumberPerson(){
        return this.phoneNumberPerson;
    }
    public String getIdPerson(){
        return this.idPerson;
    }
    @Override
    public void update() {
        ServiceEmail.sendMail(getEmailOwner(), getEmailPerson(), getPhoneNumberPerson(), getIdPerson());
    }
}
