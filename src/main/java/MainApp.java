import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000);

        DataGenerated dataGenerated = new DataGenerated();

        ReferenceQueue<DataGenerated> queue = new ReferenceQueue<>();

        Reference<DataGenerated> refSoft = new SoftReference<>(dataGenerated, queue);
        Reference<DataGenerated> refWeak = new WeakReference<>(dataGenerated, queue);
        Reference<DataGenerated> refPhantom = new PhantomReferencePrintInfo<>(dataGenerated, queue);

        System.out.println("StrongReference сслыка на объект = " + dataGenerated);
        System.out.println("SoftReference сслыка на объект   = " + refSoft.get());
        System.out.println("WeakReference сслыка на объект   = " + refWeak.get());
        System.out.println("PhantomReference сслыка на объект всегда = " + refPhantom.get());

        //если не убрать strong ref. объект удален не будет
        dataGenerated = null;
        //если не убрать soft ref. объект будет удален только при нехватке памяти
        refSoft = null;
        //weakref можно не убирать. объект будет удален
        refWeak.clear();

        System.out.println("ref = " + refPhantom);

        Thread.sleep(1000);

        System.out.println("Вызывается сборка мусора!");

        System.gc();
        Thread.sleep(300);

        System.out.println("ref = " + refPhantom);

        Thread.sleep(2000);

        System.out.println("Вызывается сборка мусора!");

        System.gc();

    }
}
