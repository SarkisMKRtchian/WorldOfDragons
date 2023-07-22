import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DarkForest {
    DarkForest(Hero hero) throws InterruptedException {
        System.out.println("-------------------------------");
        System.out.println("Вы идете по темному лесу, вам холодно, и хочется пить...");
        System.out.println("-------------------------------");
        Thread.sleep(1000);
        System.out.println("... Вы продолжаете свой путь... как вдруг из кустов издается какой-то непонятный шорох...");
        System.out.println("-------------------------------");
        Thread.sleep(1000);
        System.out.println("Вы тихими шагами подходите к кустам, как вдруг из под кустов выпрыгивает чующие и злобными глазами начинает смотреть на вас");
        System.out.println("-------------------------------");
        Thread.sleep(1000);
        Random random = new Random();
        Enemy enemy = new Enemy(random.nextInt(2));
        System.out.println("Это " + enemy.getType() + "! Сразу заметили вы...");
        action(hero, enemy);


    }

    public void action(Hero hero, Enemy enemy) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                """
                        -------------------------------
                             ---[Ваши действие]---
                        -------------------------------
                        1. Драться
                        2. Свои характеристики
                        3. Характеристики врага
                        4. Убежать
                        -------------------------------
                        """
        );
        try {
            System.out.print("Ответ: ");
            int answer = scanner.nextInt();
            switch (answer){
                case 1 -> new Battle(hero, enemy).start();
                case 2 -> hero.getAllCharacteristics();
                case 3 -> enemy.getCharacteristics();
                case 4 -> new City(hero);
                default -> {
                    System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
                    action(hero, enemy);
                }
            }
        }catch (InputMismatchException e){
            System.out.println("------------------------------------------------");
            System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
            System.out.println("-------------------------------------------------");
            Thread.sleep(1000);
            action(hero, enemy);
        }
    }


}
