package androidapp.yashthaluri.com.yeoman.Models;

public class MyBookingsHelper {

    private String photoUrl;
    private String name;
    private String phoneNumber;

    public MyBookingsHelper()
    {

    }

    public MyBookingsHelper(String phoneNumber, String photoUrl, String name)
    {
        this.name = name;
        this.photoUrl = photoUrl;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
