package LiebreTortuga;

import java.util.Random;

import static java.lang.Math.random;

public class GenerarAleatorio {
    int GenerarEntero(int x,String name){
        int y;
        Random random = new Random();
       y =5 + (int)(Math.random() * ((x - 6) + 1));
        System.out.println("Ga "+name+": " +y);
        return y;
    }

    int GenerarEntero2(int x,String name){
        int y;
        Random random = new Random();
        y =  random.nextInt(50);     ;
        System.out.println("Ga "+name+": " +y);
        return y;
    }
}
