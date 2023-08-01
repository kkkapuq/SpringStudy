package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 내부의 static 으로 갖고 있는다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // 외부에서 생성하지 못하게 private생성자로 만든다.

    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 로직 호출");
    }
}
