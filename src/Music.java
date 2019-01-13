public class Music {
    private String title;
    private String authod;

    @Override
    public String toString() {
        return "Music [title=" + title + ", authod=" + authod + "]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthod() {
        return authod;
    }

    public void setAuthod(String authod) {
        this.authod = authod;
    }
}
