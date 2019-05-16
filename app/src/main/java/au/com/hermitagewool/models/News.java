package au.com.hermitagewool.models;

public class News {
    private String id;
    private String mAuthor;
    private String mImageUrl;
    private String mTitle;
    private String mBody;
    private String mCategory;
    private String mCreationDate;

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        this.mBody = mBody;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmCreationDate() {
        return mCreationDate;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setmCreationDate(String mCreationDate) {
        this.mCreationDate = mCreationDate;
    }

    public News() {
    }

    public News(String mAuthor, String mTitle, String mBody, String mCreationDate) {
        this.mAuthor = mAuthor;
        this.mTitle = mTitle;
        this.mBody = mBody;
        this.mCreationDate = mCreationDate;
    }
}

