package domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item{

    private String artist;

    public Album(String artist, String name, int price) {
        super(name, price);
        this.artist  = artist;
    }

    public Album() {
        super();
    }
}
