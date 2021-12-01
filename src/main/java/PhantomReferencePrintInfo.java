import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferencePrintInfo<T> extends PhantomReference<T>{

    public PhantomReferencePrintInfo(T obj, ReferenceQueue<T> queue) {

        super(obj, queue);

        Thread thread = new QueueReadingThread<>(queue);

        thread.start();
    }

    public void cleanup() {
        System.err.println("Очистка фантомной ссылки! Удаление объекта из памяти!");
        clear();
    }
}
