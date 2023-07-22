
import java.util.Scanner;

import static java.lang.Thread.*;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Здравствуйте и добро пожаловать в мир удивительных драконов");
        System.out.println("-------------------------------");
        sleep(1000);
        System.out.print("Правда... ");
        sleep(1000);
        System.out.println("драконов тут нет, но создатель этой игры понял это лишь после того как создал игру, а потом поленился менять называние");
        System.out.println("-------------------------------");
        sleep(1000);
        System.out.println("И так давайте начнем");
        System.out.println("-------------------------------");
        sleep(1000);
        System.out.println("Как вас зовут?");
        System.out.print("Ваше имя: ");
        String name = scanner.next();
        Hero hero = new Hero(name);
        sleep(1000);
        new City(hero);
    }


}