import java.util.InputMismatchException;
import java.util.Scanner;

public class Shop {

    private void buy(Hero hero) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int greenHealPrice = 30;
        int blueHealPrice = 50;
        int redHealPrice = 100;
        System.out.println(
                "-------------------------------\n" +
                "     ---ВЫБЕРИТЕ ЗЕЛЬЕ---      \n" +
                "-------------------------------\n" +
                "[У ВАС [" + hero.getMoney() + "] ЗОЛОТЫХ МОНЕТ]\n" +
                "-------------------------------\n" +
                "1. Зеленое зелье(+30 здоровье): 30 золотых монет \n" +
                "2. Синее зелье(+50 здоровье): 50 золотых монет\n" +
                "3. Красное зелье(+100 здоровье): 100 золотых монет\n" +
                "-------------------------------\n" +
                "4. Назад\n" +
                "-------------------------------\n"
        );
        try {
            System.out.print("Ответ: ");
            int answer = scanner.nextInt();
            int heroMoney = hero.getMoney();
            switch (answer){
                case 1 -> {
                    if(heroMoney >= greenHealPrice){
                        hero.setMoney(heroMoney - greenHealPrice);
                        hero.setGreenHeal(hero.getGreenHeal() + 1);
                        System.out.println(
                                "-------------------------------\n" +
                                "Вы купили зеленое зелье\n" +
                                "У вас [" + hero.getGreenHeal() + "шт] зеленого зелья\n" +
                                "-------------------------------\n"
                        );
                        welcome(hero);
                    }else {
                        System.out.println("!!!---[У ВАС НЕДОСТАТОЧНО ЗОЛОТЫХ МОНЕТ]--!!!");
                        buy(hero);
                    }
                }case 2 -> {
                    if(heroMoney >= blueHealPrice){
                        hero.setMoney(heroMoney - blueHealPrice);
                        hero.setBlueHeal(hero.getBlueHeal() + 1);
                        System.out.println(
                                "-------------------------------\n" +
                                "Вы купили зеленое зелье\n" +
                                "У вас [" + hero.getBlueHeal() + "шт] зеленого зелья\n" +
                                "-------------------------------\n"
                        );
                        welcome(hero);
                    }else {
                        System.out.println("!!!---[У ВАС НЕДОСТАТОЧНО ЗОЛОТЫХ МОНЕТ]--!!!");
                        buy(hero);
                    };
                }case 3 -> {
                    if(heroMoney >= redHealPrice){
                        hero.setMoney(heroMoney - redHealPrice);
                        hero.setRedHeal(hero.getRedHeal() + 1);
                        System.out.println(
                                "-------------------------------\n" +
                                "Вы купили зеленое зелье        \n" +
                                "У вас [" + hero.getRedHeal() + "шт] зеленого зелья\n" +
                                "-------------------------------\n"
                        );
                        welcome(hero);
                    }else {
                        System.out.println("!!!---[У ВАС НЕДОСТАТОЧНО ЗОЛОТЫХ МОНЕТ]--!!!");
                        buy(hero);
                    }
                }
                case 4 -> welcome(hero);
                default -> {
                    System.out.println("------------------------------------------------");
                    System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
                    System.out.println("-------------------------------------------------");
                    Thread.sleep(1000);
                    buy(hero);
                }
            }

        }catch (InputMismatchException e){
            System.out.println("------------------------------------------------");
            System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
            System.out.println("-------------------------------------------------");
            Thread.sleep(1000);
            buy(hero);
        }

    }
    public void welcome(Hero hero) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                """
                -------------------------------
                         ---ТОРГОВЕЦ---      \s
                -------------------------------
                1. Купить зелье
                2. Инвентарь
                3. Лечиться
                4. Уйти
                -------------------------------
                """
        );
        try {
            System.out.print("Ответ: ");
            int answer = scanner.nextInt();
            switch (answer){
                case 1 -> buy(hero);
                case 2 -> {
                    hero.getAllCharacteristics();
                    welcome(hero);
                }
                case 3 -> {
                    hero.heal();
                    welcome(hero);
                }
                case 4 -> new City(hero);
                default -> {
                    System.out.println("------------------------------------------------");
                    System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
                    System.out.println("-------------------------------------------------");
                    Thread.sleep(1000);
                    welcome(hero);
                }
            }
        }catch (InputMismatchException | InterruptedException e){
            System.out.println("------------------------------------------------");
            System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
            System.out.println("-------------------------------------------------");
            Thread.sleep(1000);
            welcome(hero);
        }
    }
}
