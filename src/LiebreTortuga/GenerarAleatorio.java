package LiebreTortuga;

import java.util.Random;

import static java.lang.Math.random;

public class GenerarAleatorio {
    int GenerarEntero(int x){
        int y;
        Random random = new Random();
       y =5 + (int)(Math.random() * ((20 - 6) + 1));
        System.out.println(" GA: "+y);
        return y;
    }

    int GenerarEntero2(int x){
        int y;
        Random random = new Random();
        y =  random.nextInt(50);     ;
        System.out.println(" GA: "+y);
        return y;
    }
}
