import java.util.InputMismatchException;
import java.util.Scanner;


public class Battle extends Thread{
    int allHeroDamage = 0;
    int allEnemyDamage = 0;
    int step = 0;
    boolean isBattle = true;
    Battle(Hero hero, Enemy enemy) throws InterruptedException {
        while (isBattle){
            int heroDamage = hero.attack();
            int enemyDamage = enemy.attack();


            enemy.setHp(enemy.getHp() - heroDamage);
            if (enemy.getHp() <= 0) {
                hero.setExp(hero.getExp() + enemy.getExp());
                hero.setMoney(hero.getMoney() + enemy.getMoney());
                enemyDie(enemy, hero);
                isBattle = false;
                break;
            }
            if(heroDamage == hero.getDamage() * 3){

                System.out.println(
                        "-------------------------------\n" +
                        "Вы атаковали врага и нанесли ему " + heroDamage + "ед. урона [Крит. удар]\n" +
                        "[ВАШЕ ЗДОРОВЬЕ: " + hero.getHp() + "/" + hero.getMaxHp() + "]\n" +
                        "[ЗДОРОВЬЕ ВРАГА: " + enemy.getHp() + "/" + enemy.getMaxHp() + "]\n" +
                        "-------------------------------\n"
                );

            } else if (heroDamage != 0) {
                System.out.println(
                        "-------------------------------\n" +
                        "Вы атаковали врага и нанесли ему " + heroDamage + "ед. урона\n" +
                        "[ВАШЕ ЗДОРОВЬЕ: " + hero.getHp() + "/" + hero.getMaxHp() + "]\n" +
                        "[ЗДОРОВЬЕ ВРАГА: " + enemy.getHp() + "/" + enemy.getMaxHp() + "]\n" +
                        "-------------------------------\n"
                );
            }else {
                System.out.println("!=-[ВЫ ПРОМОХНУЛИСЬ]-=!");
            }





            Thread.sleep(1000);

            hero.setHp(hero.getHp() - enemyDamage);
            if(hero.getHp() <= 0){
                heroDie();
                isBattle = false;
                break;
            }

            if(enemyDamage == enemy.getDamage() * 3){
                System.out.println(
                        "-------------------------------\n" +
                        "Враг атаковал вас и нанес " + enemyDamage + "ед. урона [Крит. удар]\n" +
                        "[ВАШЕ ЗДОРОВЬЕ: " + hero.getHp() + "/" + hero.getMaxHp() + "]\n" +
                        "[ЗДОРОВЬЕ ВРАГА: " + enemy.getHp() + "/" + enemy.getMaxHp() + "]\n" +
                        "-------------------------------\n"
                );

            } else if (enemyDamage != 0) {
                System.out.println(
                        "-------------------------------\n" +
                        "Враг атаковал вас и нанес " + enemyDamage + "ед. урона\n" +
                        "[ВАШЕ ЗДОРОВЬЕ: " + hero.getHp() + "/" + hero.getMaxHp() + "]\n" +
                        "[ЗДОРОВЬЕ ВРАГА: " + enemy.getHp() + "/" + enemy.getMaxHp() + "]\n" +
                        "-------------------------------\n"
                );
            }else {
                System.out.println("!=-[ВРАГ ПРОМОХНУЛСЯ]-=!");
            }




            step++;
            allHeroDamage += heroDamage;
            allEnemyDamage += enemyDamage;
            if(step % 2 == 0 && hero.getHp() < hero.getMaxHp()){
                actions(hero, enemy, allHeroDamage, allEnemyDamage);
            }
            Thread.sleep(1000);

        }
    }

    private void heroDie() throws InterruptedException {
        System.out.println("""
            -------------------------------
            !!!---[ВЫ ПРОИГРАЛИ]---!!!
            -------------------------------
            1. Выйти
            """);

        try {
            Scanner scanner = new Scanner(System.in);
            int answer = scanner.nextInt();
            if (answer == 1) {
                System.exit(0);
            } else {
                System.out.println("------------------------------------------------------");
                System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
                System.out.println("------------------------------------------------------");
                Thread.sleep(1000);
                heroDie();
            }
        } catch (InputMismatchException e){
            System.out.println("------------------------------------------------");
            System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
            System.out.println("-------------------------------------------------");
            Thread.sleep(1000);
            heroDie();
        }
    }

    private void enemyDie(Enemy enemy, Hero hero) throws InterruptedException {
        System.out.println(
            "-------------------------------\n" +
            "    !!!---[ВЫ ПОБЕДИЛИ]---!!!  \n" +
            "-------------------------------\n" +
            "[НАГРАДЫ]\n" +
            "Золото: +" + enemy.getMoney() + " [" + hero.getMoney() + "]\n" +
            "Опыт:   +" + enemy.getExp() + "[" + hero.getLevel() + "] " + hero.getExp() + "/" + hero.getExpForLvlUp() + "\n" +
            "-------------------------------\n" +
            "1. Продолжить путь\n" +
            "2. Свои характеристики\n" +
            "3. Лечится\n" +
            "4. Вернуться в город\n"
        );

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ответ: ");
            int answer = scanner.nextInt();
            switch (answer){
                case 1 -> new DarkForest(hero);
                case 2 -> {
                    hero.getAllCharacteristics();
                    enemyDie(enemy, hero);
                }
                case 3 -> {
                    hero.heal();
                    enemyDie(enemy, hero);
                }
                case 4 -> new City(hero);
                default -> {
                    System.out.println("------------------------------------------------");
                    System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
                    System.out.println("-------------------------------------------------");
                    Thread.sleep(1000);
                    enemyDie(enemy, hero);
                }
            }
        } catch (InputMismatchException e){
            System.out.println("------------------------------------------------");
            System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
            System.out.println("-------------------------------------------------");
            Thread.sleep(1000);
            enemyDie(enemy, hero);
        }
    }

    private void actions(Hero hero, Enemy enemy, int heroDamage, int enemyDamage) throws InterruptedException {
        System.out.println(
                "-------------------------------\n" +
                "   ---[ВЫБЕРИТЬЕ ДЕЙСТВИЕ]---  \n" +
                "-------------------------------\n" +
                "[Статистика боя]\n" +
                "[НАНЕСЕН УРОН] - " + heroDamage + "\n" +
                "[ПОЛУЧЕН УРОН] - " + enemyDamage + "\n" +
                "[ЗДОРОВЬЕ " + hero.getName().toUpperCase() + "] - " + hero.getHp() + "/" + hero.getMaxHp() + "\n" +
                "[ЗДОРОВЬЕ " + enemy.getType().toUpperCase() + "] - " + enemy.getHp() + "/" + enemy.getMaxHp() + "\n" +
                "-------------------------------\n" +
                "Соперник оказался серьезным, хотите ли вы принять лекарства?\n" +
                "1. Да\n" +
                "2. Нет\n" +
                "3. Хочу убежать в город!\n"
        );
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ответ: ");
            int answer = scanner.nextInt();
            switch (answer){
                case 1 -> hero.heal();
                case 2 -> {}
                case 3 -> new City(hero);
                default -> {
                    System.out.println("------------------------------------------------");
                    System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
                    System.out.println("-------------------------------------------------");
                    Thread.sleep(1000);
                    actions(hero, enemy, heroDamage, enemyDamage);
                }
            }
        } catch (InputMismatchException e){
            System.out.println("------------------------------------------------");
            System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
            System.out.println("-------------------------------------------------");
            Thread.sleep(1000);
            actions(hero, enemy, heroDamage, enemyDamage);
        }
    }
}
