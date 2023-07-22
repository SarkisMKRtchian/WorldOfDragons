import java.util.InputMismatchException;
import java.util.Scanner;

public class City {
    City(Hero hero) throws InterruptedException {
        whereGo(hero);
    }

    private void whereGo(Hero hero) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                ---------------------------------
                         ---ГОРОД---
                ---------------------------------
                Выберите куда хотеть направиться
                ---------------------------------
                1. В темный лес.
                2. К торговцу
                3. Инвентарь
                4. Выйти
                ---------------------------------
                """);
        try {
            System.out.print("Ответ: ");
            int answer = scanner.nextInt();
            switch (answer){
                case 1 -> new DarkForest(hero);
                case 2 -> new Shop().welcome(hero);
                case 3 -> {
                    hero.getAllCharacteristics();
                    whereGo(hero);
                }
                case 4 -> System.exit(0);
                default -> {
                    System.out.println("------------------------------------------------");
                    System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
                    System.out.println("-------------------------------------------------");
                    Thread.sleep(1000);
                    whereGo(hero);
                }
            }
        }catch (InputMismatchException e){
            System.out.println("------------------------------------------------");
            System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
            System.out.println("-------------------------------------------------");
            Thread.sleep(1000);
            whereGo(hero);
        }
    }
}
