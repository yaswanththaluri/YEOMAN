package androidapp.yashthaluri.com.yeoman.Models;

public class BookJobHelper {
    private String farmerName;
    private String date;
    private String attStatus;
    private String acceptanceStatus;
    private String workDesc;

    public BookJobHelper()
    {

    }

    public BookJobHelper(String farmerName, String date, String attStatus, String acceptanceStatus, String workDesc)
    {
        this.farmerName = farmerName;
        this.date = date;
        this.attStatus = attStatus;
        this.acceptanceStatus = acceptanceStatus;
        this.workDesc = workDesc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttStatus() {
        return attStatus;
    }

    public void setAttStatus(String attStatus) {
        this.attStatus = attStatus;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getAcceptanceStatus() {
        return acceptanceStatus;
    }

    public void setAcceptanceStatus(String acceptanceStatus) {
        this.acceptanceStatus = acceptanceStatus;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public String getWorkDesc() {
        return workDesc;
    }
}
