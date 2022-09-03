package hello.core.singleton;

public class SingletonService {
    //싱글톤은 클라이언트가 여럿일 때 그에 상응하는 객체 생성을 줄여줘서 힙 메모리를 최적화 시킨다.
    // private으로 다른 클래스에서 new로 생성되는 것을 막는다.
    //접근자로 제어하는 방법이 싱글톤
    //자바가 처음 컴파일 될 때 자기가 가지고 있다.
    //1.static영역에 객체를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    //자기 자신을 인스턴스 생성해서 가져온다. 접근자다.
    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance(){
        return instance;
    }

    //private으로 생성자를 만들어서 다른 클래스에서 마구잡이로 생성을 못하게 막았다.
    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService(){
    //-> 프라이빗이면 싱글톤이구나 하고 알아야된다.
    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
