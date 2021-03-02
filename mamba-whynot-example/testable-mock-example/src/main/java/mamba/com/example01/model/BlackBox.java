package mamba.com.example01.model;

/**
 * @author mamba
 * @date 2021/3/2 18:00
 */
public class BlackBox {

    private String data;

    public BlackBox(String data) {
        this.data = data;
    }

    public static BlackBox secretBox() {
        return new BlackBox("secret");
    }

    public void put(String something) {
        data = something;
    }

    public String getColor() {
        return data;
    }

}
