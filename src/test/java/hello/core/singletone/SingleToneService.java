package hello.core.singletone;

public class SingleToneService {

    private static final SingleToneService instance = new SingleToneService();

    public static SingleToneService getInstance(){
        return instance;
    }

    private SingleToneService(){ // 다른 곳에서 사용하지 못하게 생성을 private 생성자로 생성을 막아버림
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
