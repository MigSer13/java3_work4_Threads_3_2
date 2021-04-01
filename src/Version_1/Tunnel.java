package Version_1;

import java.util.concurrent.Semaphore;

class Tunnel extends Stage {
    private Semaphore semaphore;

    public Tunnel()
    {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        semaphore = new Semaphore(2);
    }

    @Override
    public void go(Car c)
    {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
