package tests.data;

public enum Apple {
    iPhone("iPhone"),
    AppleWatch("Apple Watch"),
    AirPods("AirPods"),
    iPad("iPad"),
    MacBook("MacBook"),
    iMac("iMac");

    public final String name;

    Apple(String name) {
        this.name = name;
    }
}
