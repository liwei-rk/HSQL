package org.ict.hsql.simpleTest;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-12-5
 * Time: 下午10:58
 * To change this template use File | Settings | File Templates.
 */
public class Base {
    public Base() {
        System.out.println("Base");
        Random r = new Random(5);
        boolean guess=!r.nextBoolean();


    }

    void get(int a) {

    }

}
