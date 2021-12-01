import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class QueueReadingThread<T> extends Thread {

    private ReferenceQueue<T> referenceQueue;

    public QueueReadingThread(ReferenceQueue<T> referenceQueue) {
        this.referenceQueue = referenceQueue;
    }

    @Override
    public void run() {

        System.out.println("Поток, отслеживающий очередь, стартовал!");
        Reference<?> ref = null;

        //ждем, пока в очереди появятся ссылки
        while ((ref = referenceQueue.poll()) == null) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException("Поток " + getName() + " был прерван!");
            }
        }

        //как только в очереди появилась фантомная ссылка - очистить ее
        if (ref instanceof PhantomReferencePrintInfo) {
            ((PhantomReferencePrintInfo<?>) ref).cleanup();
        }else if (ref instanceof WeakReference){
            System.out.println("Вызван метод .clear() у WeakReference");
            ref.clear();
        }
    }
}
