package hellojpa.ch03_ch04;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀀스 이름.
//        initialValue = 1, allocationSize = 1
//)
public class OldMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;
    //    @Column(name = "name") // 객체는 username이라 쓰고, DB에는 name이라고 쓸 때
//    @Column(updatable = false) // 이 컬럼은 절대 변경되지 않는다.(DB에서 강제로 업데이트 하지 않는 이상)
//    @Column(nullable = false) // Not null 제약조건을 걸어준다.
//    @Column(unique = true) // 얘는 여기서 잘 안쓴다. 유니크 제약조건을 만들어주기는 하는데, 이름이 랜덤하게 설정되고, 한 컬럼에만 적용되고 복합에서는 안되기 때문에 운영에서 사용하기 어렵다. 때문에 위의 @Table에서 사용한다.
    @Column(columnDefinition = "varchar(100) default 'EMPTY'") // 특정 DB에 종속적인 옵션들을 직접 넣을 수 있다.
    private String username;

    @Column()
    private Integer age; // Integer를 써도 생각한 대로 DB에 Integer와 가장 적절한 숫자 타입이 설정된다.

    @Enumerated(EnumType.STRING) // DB에는 Enum 타입이 없어서 @Enumerated 추가해야 한다.
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //DATE, TIME, TIMESTAMP 세가지가 있는데, 보통 DB는 이 세가지를 구분해서 쓰므로 매핑정보를 줘야 한다.
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob // DB에 varchar를 넘어서는 큰 컨텐츠를 넣고 싶을 경우 @Lob 사용
    private String description;

    @Transient // DB랑 관계없이 메모리에서만 계산하고 싶을 때, 즉, DB의 컬럼과 매핑하지 않을 때 사용
    private int temp;

    private LocalDate testLocalDate; //하이버네이트 최신버전에서는 @Temporal 없이 이렇게 쓰면 된다.
    private LocalDateTime testLocalDateTime;
}
