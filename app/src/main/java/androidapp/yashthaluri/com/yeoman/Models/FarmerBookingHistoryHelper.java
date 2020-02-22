package androidapp.yashthaluri.com.yeoman.Models;

public class FarmerBookingHistoryHelper
{
    private String labourName;
    private String labourId;
    private String attStatus;
    private String isBookingAccepted;
    private String workDesc;
    private String bookingDate;

    public FarmerBookingHistoryHelper(String labourName, String labourId, String attStatus, String isBookingAccepted, String workDesc, String bookingDate)
    {
        this.labourId = labourId;
        this.labourName = labourName;
        this.attStatus = attStatus;
        this.isBookingAccepted = isBookingAccepted;
        this.workDesc = workDesc;
        this.bookingDate = bookingDate;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public String getAttStatus() {
        return attStatus;
    }

    public String getIsBookingAccepted() {
        return isBookingAccepted;
    }

    public String getLabourId() {
        return labourId;
    }

    public String getLabourName() {
        return labourName;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public void setAttStatus(String attStatus) {
        this.attStatus = attStatus;
    }

    public void setIsBookingAccepted(String isBookingAccepted) {
        this.isBookingAccepted = isBookingAccepted;
    }

    public void setLabourId(String labourId) {
        this.labourId = labourId;
    }

    public void setLabourName(String labourName) {
        this.labourName = labourName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
