package Version_1;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60),
                    new Tunnel(),
                    new Road(40));
        waitingForCars(race);
    }


    public static void waitingForCars(Race race){
        CyclicBarrier cyclicBarrierBeginRace = new CyclicBarrier(CARS_COUNT, new BeginRace());
        CyclicBarrier cyclicBarrierEndRace = new CyclicBarrier(CARS_COUNT, new EndRace());
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cyclicBarrierBeginRace, cyclicBarrierEndRace);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
    }

    public static class BeginRace implements Runnable {
        @Override
        public void run()
        {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }
    }
    public static class EndRace implements Runnable {
        @Override
        public void run()
        {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
    }

}





