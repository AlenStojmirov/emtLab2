package mk.ukim.finki.emt.OnlineShop.models;

public class Manufacturer {
    private Long id;
    private String mname;

    @Override
    public String toString() {
        return mname;
    }

    public Manufacturer() {
    }

    public Manufacturer(String mname) {
        this.mname = mname;
    }

    public Manufacturer(long l, String nike) {
        this.id = l;
        this.mname = nike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }
}
