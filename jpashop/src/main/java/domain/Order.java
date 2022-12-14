package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Table(name = "ORDERS") //order가 예약어로 걸리는 DB가 있어서
public class Order  extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;


//    private Long memberId;
    // 위 처럼 member의 id를 가지고 있는 경우 객체지향스럽지 않다. (객체를 관계형 DB에 맞추어 설계한 것이다.)
    // 왜냐하면 아래처럼 member를 찾아야 하기 때문이다.
    // Order order = em.find(Order.class, 1L);
    // Long memberId = order.getMemberId();
    // Member member = em.find(Member.class, memberId);

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    // 때문에 위 처럼 Member를 가지고 있는 것이 더 객체지향적이고, 아래와 같이 바로 member를 찾을 수 있어야 한다.
    // Member member = order.getMember();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    // 연관관계 편의 메서드
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
