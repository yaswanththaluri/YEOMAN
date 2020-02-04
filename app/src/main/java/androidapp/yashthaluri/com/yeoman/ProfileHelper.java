package androidapp.yashthaluri.com.yeoman;

public class ProfileHelper
{
    private String userName;
    private String role;
    private String aadharNo;
    private String gender;
    private String address;
    private String city;
    private String zipCode;
    private String aadharFileLink;
    private String isProfileFilled;
    private String isProfileVerified;

    public ProfileHelper()
    {

    }

    public ProfileHelper(String userName, String role, String aadharNo, String gender, String address, String city, String zipCode, String aadharFileLink, String isProfileFilled, String isProfileVerified)
    {
        this.userName = userName;
        this.role = role;
        this.aadharNo = aadharNo;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.aadharFileLink = aadharFileLink;
        this.isProfileFilled = isProfileFilled;
        this.isProfileVerified = isProfileVerified;
    }

    public String getAadharFileLink() {
        return aadharFileLink;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getIsProfileFilled() {
        return isProfileFilled;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public void setAadharFileLink(String aadharFileLink) {
        this.aadharFileLink = aadharFileLink;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setIsProfileFilled(String isProfileFilled) {
        this.isProfileFilled = isProfileFilled;
    }

    public String getIsProfileVerified() {
        return isProfileVerified;
    }

    public void setIsProfileVerified(String isProfileVerified) {
        this.isProfileVerified = isProfileVerified;
    }
}
