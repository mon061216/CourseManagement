package model.isScheduled;
public class IsScheduledDTO {
    private String slotID;
    private String classID;

    public IsScheduledDTO() {
    }

    public IsScheduledDTO(String slotID, String classID) {
        this.slotID = slotID;
        this.classID = classID;
    }

    public String getSlotID() {
        return slotID;
    }

    public void setSlotID(String slotID) {
        this.slotID = slotID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }
    
}
