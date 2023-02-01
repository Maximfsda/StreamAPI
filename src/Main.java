
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<T> items = stream.sorted(order).collect(Collectors.toList());
        if (!items.isEmpty()) {
            minMaxConsumer.accept(items.get(0), items.get(items.size() - 1));

        } else {
            minMaxConsumer.accept(null, null);
        }
    }

    public static void main(String[] args) throws Exception {
        Stream<Integer> stream = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 8, 13, 21)).stream();

        findMinMax(
                stream,
                (x, y) -> x.compareTo(y),
                (x, y) -> System.out.println(String.format("min: %s, max: %s", x, y))
        );

        stream.close();

        DemoStream();
    }


    public static void DemoStream() {
        // Задача. Для любого набора случайно сгенерированных чисел
        // нужно определить количество парных.

        // 1. Создать поток данных из случайного массива чисел
        ArrayList<Integer> AL = new ArrayList<Integer>();
        int number;
        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            number = rnd.nextInt() % 100;
            AL.add(number);
        }

        System.out.println("Array AL:");
        System.out.println(AL);

        Stream<Integer> st = AL.stream();
        Predicate<Integer> fn;
        fn = (n) -> (n%2) == 0;
        Stream<Integer> resStream = st.filter(fn);
        System.out.println("n = " + resStream.count());
    }
}