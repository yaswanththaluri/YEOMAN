package androidapp.yashthaluri.com.yeoman.Models;

public class FarmerBookingHistoryHelper
{
    private String labourName;
    private String labourId;
    private String attStatus;
    private String isBookingAccepted;
    private String workDesc;
    private String bookingDate;
    private String phoneNumber;
    private String imageUrl;

    public FarmerBookingHistoryHelper()
    {

    }

    public FarmerBookingHistoryHelper(String labourName, String labourId, String attStatus, String isBookingAccepted, String workDesc, String bookingDate, String phoneNumber, String imageUrl)
    {
        this.labourId = labourId;
        this.labourName = labourName;
        this.attStatus = attStatus;
        this.isBookingAccepted = isBookingAccepted;
        this.workDesc = workDesc;
        this.bookingDate = bookingDate;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
