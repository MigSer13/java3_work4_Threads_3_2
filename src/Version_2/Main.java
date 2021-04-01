package Version_2;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args)
    {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60),
                new Tunnel(),
                new Road(40));
        waitingForCars(race);
    }


    private static void waitingForCars(Race race)
    {
        Car[] cars = new Car[CARS_COUNT];
        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);

        Callable<Integer> preparation = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception
            {
                for (int i = 0; i < cars.length; i++) {
                    cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
                }
                return 1;
            }
        };

        Future<Integer> futurePreparation = executorService.submit(preparation);
        try {
            futurePreparation.get();
            System.out.println("Все участники подготовились.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        Callable<Integer> overcomingObstacles = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception
            {
                for (int i = 0; i < cars.length; i++) {
                    cars[i].overcomingObstacles();
                }
                return 1;
            }
        };
        Future<Integer> futureOvercomingObstacles = executorService.submit(overcomingObstacles);
        try {
            futureOvercomingObstacles.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        executorService.shutdown();
    }
}





