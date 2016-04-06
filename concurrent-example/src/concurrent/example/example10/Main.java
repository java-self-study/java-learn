package concurrent.example.example10;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

  // Пример 10. Перебор коллекции с параллельным изменением (CopyOnWriteArrayList).
  //
  public static void main(String[] args) {
    final List<Integer> list = new CopyOnWriteArrayList<Integer>();

    // Заполняем коллекцию 1000 значений.
    for (int i = 0; i < 1000; i++) {
      list.add(i);
    }

    new Thread(new Runnable() {
      @Override
      public void run() {
        // Через 50 мс удаляем одно значение.
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        list.remove(100);
        System.out.println("removed");
      }
    }).start();

    // По очереди с задержкой в 1 мс выводим значения из списка.
    for (int i : list) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(i);
    }

    System.out.println("----------------------------------------");

    // Теперь выводим весь список без задержек.
    for (int i : list) {
      System.out.println(i);
    }
  }
}
