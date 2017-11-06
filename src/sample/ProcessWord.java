package sample;

public class ProcessWord {
    private String url;

    public ProcessWord (String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "{url: "+url+"}";
    }
}
