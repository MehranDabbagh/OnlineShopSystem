package entity;

public class BaseEntity {

    private Integer id;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        // TODO implement here
        return false;
    }
}
