package androidapp.yashthaluri.com.yeoman.Models;

public class DetailWorkerModel {
    int bitmap;
    String name,skills,rating, uid, searchedDate;

    public DetailWorkerModel(int bitmap, String name, String skills, String rating, String uid, String searchedDate) {
        this.bitmap = bitmap;
        this.name = name;
        this.skills = skills;
        this.rating = rating;
        this.uid = uid;
        this.searchedDate = searchedDate;
    }

    public int getBitmap() {
        return bitmap;
    }

    public void setBitmap(int bitmap) {
        this.bitmap = bitmap;
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
