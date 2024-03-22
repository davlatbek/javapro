package ru.innotech.javapro.hw1.tests;

import ru.innotech.javapro.hw1.annotations.AfterSuite;
import ru.innotech.javapro.hw1.annotations.BeforeSuite;
import ru.innotech.javapro.hw1.annotations.Test;
import ru.innotech.javapro.hw1.models.Box;

public class MyTests {

    private Box box;

    public MyTests() {
        System.out.println("Default contructor");
    }

    public MyTests(Box box) {
        System.out.println("Init box constructor");
        this.box = box;
    }

    @BeforeSuite
    public void setup() {
        box = new Box(10, 20, 30);
    }

    @Test(priority = 7)
    public void testVolumeFunc() {
        assert box.volume() == 6000 : "testSquareFunc не работает корректно";
    }

    @Test(priority = 3)
    public void testVolumeFunc2() {
        assert box.volume() == 6000 : "testSquareFunc не работает корректно";
    }

    @Test(priority = 1)
    public void testSquareFunc() {
        assert box.square() == 2200 : "testSquareFunc не работает корректно";
    }

    @Test()
    public void testPerimeterFunc() {
        assert box.perimeter() == 240 : "testSquareFunc не работает корректно";
    }

    /**
     * Раскомменить для проверки приоритета выше 10
     */
    /*@Test(priority = 11)
    public void testSmthElseFunc() {
        assert box.volume() == 6000 : "testSquareFunc не работает корректно";
    }*/
    @AfterSuite
    public void tearDown() {
        box = null;
        System.gc();
    }


}
