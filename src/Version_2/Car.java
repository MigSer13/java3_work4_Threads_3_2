package Version_2;

class Car {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName()
    {
        return name;
    }

    public int getSpeed()
    {
        return speed;
    }

    public Car(Race race, int speed)
    {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        System.out.println(name + " готовится");
        try {
            Thread.sleep(500 + (int) (Math.random() * 800));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " готов");
    }

    public void overcomingObstacles (){
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(Car.this);
        }
    }

}
