import java.util.Random;

/**
 * Класс врага. Честно говоря, не понял зачем создавать 2 разных класса для 2 типов врагов если вместо этого можно просто создать методы внутри одного класса.
 * И еще если прям честно-честно не понял как правильно реализовать рандомное создание врагов(Даже после того как посмотрел примеры).
 * Но все же думаю, что и этот вариант не плох по скольку в методах все ровно передаются характеристики врага и его тип. Конкретно для данной игры где не надо прописать логику
 * поведения каждого врага думаю подойдет. Но я понимаю, что если надо будет прописать много разных характеристик, то тогда лучше будет создать класс для каждого врага.
 *
 */
public class Enemy {
    private String type;
    private int hp;
    private int maxHp;
    private int damage;
    private int dexterity;
    private int exp;
    private int money;

    /**
     * Создаем экземпляр класса врага и передаем в него рандомное число 0-1, где -
     * 0 - это скелет
     * 1 - это гоблин
     * @param type тип врага
     */
    Enemy(int type){
        switch (type){
            case 0 -> skeleton();
            case 1 -> goblin();
        }
    }

    /**
     * Создание скелета
     */
    private void skeleton(){
        setType("Скелет");
        setHp(80);
        setMaxHp(80);
        setDamage(15);
        setDexterity(20);
        setExp(30);
        setMoney(50);
    }

    /**
     * Создание гоблина
     */
    private void goblin(){
        setType("Гоблин");
        setHp(50);
        setMaxHp(50);
        setDamage(10);
        setExp(20);
        setDexterity(10);
        setMoney(35);
    }

    /**
     * Повторят {@link Hero#getAllCharacteristics()}, кроме инвентаря
     */
    public void getCharacteristics(){
        System.out.println(
                "-------------------------------\n" +
                "  ---[ХАРАКТЕРИСТИКИ ВРАГА]--- \n" +
                "-------------------------------\n" +
                "Тип:      " + getType() + "\n" +
                "Урон:     " + getDamage() + "\n" +
                "Здоровье: " + getHp() + "/" + getMaxHp() + "\n" +
                "Ловкость: " + getDexterity() + "\n" +
                "-------------------------------\n"
        );
    }

    /**
     * Повторяет {@link Hero#attack()}
     * @return урон
     */
    public int attack(){
        Random random = new Random();
        if(getDexterity() * 3 > random.nextInt(101)){
            return getDamage();
        }else if(getDexterity() * 3 == random.nextInt(101)){
            return getDamage() * 3;
        }else return 0;
    }

//    getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
