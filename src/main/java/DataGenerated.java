public class DataGenerated {
    private StringBuffer data;

    public DataGenerated() {
        System.out.println("Создан объект " + DataGenerated.class);
        this.data = new StringBuffer();
        for (long i = 0; i < 100000000; i++) {
            this.data.append('x');
        }

    }

    @Override
    protected void finalize() {
        System.err.println("У объекта вызван метод finalize");
    }
}
