package io.tntra.javabootcamp.Enumpkg;

// import com.fasterxml.jackson.annotation.JsonCreator;
// import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {
    SAVINGS("Savings"),
    CURRENT("Current"),
    FIXED_DEPOSIT("FD");

    public String value;
    AccountType(String value) {
        this.value = value;
    }
//    @JsonValue
    public String getValue(){return this.value;}

//    @JsonCreator
    public static AccountType create(String value){
        AccountType accountTypes[] = AccountType.values();
        for(AccountType type:accountTypes){
            if(type.getValue().equalsIgnoreCase(value)){
                return type;
            }
        }
        return SAVINGS;
    }
}
