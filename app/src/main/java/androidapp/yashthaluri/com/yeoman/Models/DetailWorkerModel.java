package androidapp.yashthaluri.com.yeoman.Models;

public class DetailWorkerModel {
    String bitmap, role;
    String name,skills,rating, uid, searchedDate;

    public DetailWorkerModel(String bitmap, String name, String skills, String rating, String uid, String searchedDate, String role) {
        this.bitmap = bitmap;
        this.name = name;
        this.skills = skills;
        this.rating = rating;
        this.role = role;
        this.uid = uid;
        this.searchedDate = searchedDate;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSearchedDate() {
        return searchedDate;
    }

    public void setSearchedDate(String searchedDate) {
        this.searchedDate = searchedDate;
    }
}
