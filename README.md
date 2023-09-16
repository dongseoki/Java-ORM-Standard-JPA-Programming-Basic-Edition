# Java-ORM-Standard-JPA-Programming-Basic-Edition
김영한 강의...

# 레포내 프로젝트들 설명

ex1-hello-jpa
* ch4까지 jpa 실습을 위해 사용함.
* ch10 : jpql 실습을 위해 사용함.

jpashop
* 실전예제 용으로 사용하는 프로젝트;
* 배민처럼 주문을 가정함.

relationalMapping
* ch5-8 에서 사용. 
* 학습용으로 사용하는 프로젝트. 

# 내 노션 링크
* https://www.notion.so/ORM-JPA-Main-Page-55deb48d4c004623b221a46ff1f7747d?pvs=4

# h2 db 접속 방법
H2 사용 및 접속 관련 정보.

Window
C:\study\h2-2019-10-14\h2\bin\h2.bat 실행.

MacOS
/Users/dongseoklee/util/h2/bin/h2.sh

최초 파일  생성
jdbc:h2:~/javaormjpabasic
jdbc:h2:~/jpashop

h2 접속 정보
jdbc:h2:tcp://localhost/~/javaormjpabasic

또는
jdbc:h2:tcp://localhost/~/jpashop

경우에 따라 다름.
개별 프로젝트 설정을 참고할것.


참고 정보
현재 홈폴더에 jpashop.mv.db 자료 저장되게 해둠. 참고할것...
