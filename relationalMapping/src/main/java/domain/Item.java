package domain;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn // DTYPE 컬럼이 생성됨.
public class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
