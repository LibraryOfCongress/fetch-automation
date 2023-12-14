package api_automation.requestBuilder;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class RequestBuilder {

    @JsonPropertyOrder({"level","name"})
    private String name;
    private int level;
    @JsonPropertyOrder({"ownerName","ownerTierID"})
    private String ownerName;
    private int ownerTierID;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public int getOwnerTierID() {return ownerTierID; }
    public void setOwnerTierID(int ownerTierID) {this.ownerTierID = ownerTierID; }

}