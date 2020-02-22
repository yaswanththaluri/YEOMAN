package androidapp.yashthaluri.com.yeoman.Models;

public class BookJobHelper {
    private String farmerName;
    private String date;
    private String attStatus;


    public BookJobHelper()
    {

    }

    public BookJobHelper(String farmerName, String date, String attStatus)
    {
        this.farmerName = farmerName;
        this.date = date;
        this.attStatus = attStatus;
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
}
