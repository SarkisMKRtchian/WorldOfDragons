
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Класс игрока, подумал что для меня лучше будет создать отдельный класс героя,
 * чем воспользоваться одним классом и для врагов и для героя
 */
public class Hero {
    private final String name;
    private int level = 1;
    private int expForLvlUp = 50;

    private int exp = 0;
    private int hp = 100;

    private int maxHp = 100;
    private int damage = 15;
    private int dexterity = 25;
    private int money = 0;
    private int greenHeal = 0;
    private int blueHeal = 0;
    private int redHeal = 0;

    /**
     * Создание игрока, передаем имя, и даем 1 уровень
     * @param name имя героя
     */
    Hero(String name){
        this.name = name;
        setLevel(1);
    }

    /**
     * Лечение игрока. Есть на выбор 3 вида лекарств -
     * 1 - зеленое зелье восстанавливает 30% здоровья от макс. здоровья
     * 2 - синее зелье восстанавливает 50% здоровья от макс. здоровья
     * 3 - красное зелье восстанавливает 100% здоровья от макс. здоровья
     */
    public void heal() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "--------------------------------\n" +
                        "     ---Выберите зелье---\n" +
                        "--------------------------------\n" +
                        "1. Зеленое зелье (+30% здоровье)  " + getGreenHeal() + "\n" +
                        "2. Синее зелье (+50% здоровье)    " + getBlueHeal() + "\n" +
                        "3. Красное зелье (+100 здоровье)  " + getRedHeal() + "\n" +
                        "--------------------------------\n"

        );
        try {
            System.out.print("Ответ: ");
            int answer = scanner.nextInt();
            switch (answer){
                case 1 -> {
                    if(getGreenHeal() > 0){
                        int heal = Math.round((float) maxHp * 30 / 100);
                        if(heal >= maxHp){
                            hp = maxHp;
                        }else hp += heal;
                        setGreenHeal(getGreenHeal() - 1);
                        System.out.println(
                                "-[Вы выпили зеленое зелье, и восстановили " + heal + "ед. здоровья]-\n" +
                                        "-[Здоровье " + hp + "/" + maxHp + "]-\n");
                    }else System.out.println("!!!---[В инвентаре нет зеленого зелья]---!!!");
                }case 2 -> {
                    if (getBlueHeal() > 0){
                        int heal = Math.round((float) maxHp * 50 / 100);
                        if(heal >= maxHp){
                            hp = maxHp;
                        }else hp += heal;
                        setBlueHeal(getBlueHeal() - 1);
                        System.out.println(
                                "-[Вы выпили синее зелье, и восстановили " + heal + "ед. здоровья]-\n" +
                                        "-[Здоровье " + hp + "/" + maxHp + "]-\n");
                    }else System.out.println("!!!---[В инвентаре нет синего зелья]---!!!");
                }case 3 -> {
                    if (getRedHeal() > 0) {
                        hp = maxHp;
                        setRedHeal(getRedHeal() - 1);
                        System.out.println(
                                "-[Вы выпили красное зелье, и полностью восстановили ваше здоровье]-\n" +
                                        "-[Здоровье " + hp + "/" + maxHp + "]-\n");
                    }else System.out.println("!!!---[В инвентаре нет красного зелья]---!!!");
                }default -> {
                    System.out.println("------------------------------------------------------");
                    System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
                    System.out.println("------------------------------------------------------");
                    Thread.sleep(1000);
                    heal();
                }
            }
        } catch (InputMismatchException e){
            System.out.println("------------------------------------------------");
            System.out.println("!!!---[Пожалуйста выберите один из вариантов]---!!!");
            System.out.println("-------------------------------------------------");
            Thread.sleep(1000);
            heal();
        }
    }

    /**
     * Увеличение уровня игрока. Увеличивает характеристики игрока путем прибавления ко всем хар. % + 10 от уровня
     */
    public void levelUp(){
        maxHp += Math.round((float) (hp * level / 100) + 10);
        damage += Math.round((float) (damage * level / 100) + 10);
        dexterity += Math.round((float) (dexterity * level / 100) + 10);
        expForLvlUp += expForLvlUp + Math.round((float) expForLvlUp * 20 / 100);
        exp = 0;
        hp = maxHp;

        System.out.printf(
                "--------------------------------\n" +
                "      ---УРОВЕНЬ ПОВЫШЕН---     \n" +
                "Ваш новый уровень: " + getLevel() + "\n" +
                "---------------------------------\n" +
                "Ваши характеристики увеличены\n" +
                "---------------------------------\n" +
                "Здоровье: " + hp +"\n" +
                "Урон: " + damage + "\n" +
                "Ловкость: " + dexterity + "\n" +
                "---------------------------------\n"
        );
    }

    /**
     * Добавления опыта. Если опыт становиться больше или равно опыту для лвл-апа, то увеличиваем уровень на +1, и  вызываем levelUp()
     * @param exp опыт прокачки
     */
    public void setExp(int exp){
        this.exp = exp;
        if(this.exp >= expForLvlUp){
            level++;
            levelUp();
        }
    }

    /**
     * Атака игрока
     * Обычная атака - если x3|ловкость больше чем рандомное число от 0 до 100, то возвращает урон (Шанс успешной атаки на 1 уровне 75%)
     * Крит. атака - если x3|ловкость равно рандомное число от 0 до 100, то возвращает x3|урон (Шанс успеха 1%)
     * Промах - если х3|ловкость меньше или не равно рандомному числу от 0 до 100, то возвращает 0 (Шанс промаха 25%)
     * @return урон
     */
    public int attack(){
        Random random = new Random();
        int chance = getDexterity() * 3;
        int randomNumber = 101;
        if(chance > randomNumber){
            randomNumber += randomNumber;
        }
        if(chance == random.nextInt(randomNumber)){
            return getDamage() * 3;
        }else if(chance > random.nextInt(randomNumber)){
            return getDamage();
        }else return 0;
    }

    /**
     * Выводит на экран характеристики игрока, и инвентарь
     */
    public void getAllCharacteristics(){
        System.out.println(
                "-------------------------------\n" +
                "  ---[ВАШИ ХАРАКТЕРИСТИКИ]---  \n" +
                "-------------------------------\n" +
                "Уровень: [" + getLevel() + "] " + getExp() + "/" + getExpForLvlUp() + "\n" +
                "Урон: " + getDamage() + "\n" +
                "Здоровье: " + getHp() + "/" + getMaxHp() + "\n" +
                "Ловкость: " + getDexterity() + "\n" +
                "-------------------------------\n"
        );
        System.out.println(
                "-------------------------------\n" +
                "       ---[Инвентарь]---       \n" +
                "-------------------------------\n" +
                "Золото: " + getMoney() + "\n" +
                "Зеленое зелье: " + getGreenHeal() + "\n" +
                "Синее зелье:   " + getBlueHeal() + "\n" +
                "Красное зелье: " + getRedHeal() + "\n" +
                "------------------------------\n"
        );
    }


//    Getters and setters

    public String getName() {
        return name;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }



    public void setHp(int hp){
        this.hp = hp;
    }

    public int getHp(){
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getDamage(){
        return damage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public int getExpForLvlUp() {
        return expForLvlUp;
    }

    public int getExp() {
        return exp;
    }
    public int getGreenHeal() {
        return greenHeal;
    }

    public void setGreenHeal(int greenHeal) {
        this.greenHeal = greenHeal;
    }

    public int getBlueHeal() {
        return blueHeal;
    }

    public void setBlueHeal(int blueHeal) {
        this.blueHeal = blueHeal;
    }

    public int getRedHeal() {
        return redHeal;
    }

    public void setRedHeal(int redHel) {
        this.redHeal = redHel;
    }



    public int getDexterity() {
        return dexterity;
    }


}
