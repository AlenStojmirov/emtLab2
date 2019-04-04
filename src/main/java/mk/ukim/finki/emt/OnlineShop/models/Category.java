package mk.ukim.finki.emt.OnlineShop.models;

public class Category {
    private Long id;
    private String cname;

    @Override
    public String toString() {
        return cname;
    }

    public Category(Long id, String cname) {
        this.id = id;
        this.cname = cname;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
