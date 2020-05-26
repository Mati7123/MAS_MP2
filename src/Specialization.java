
public class Specialization extends ObjectPlusPlus {

    private static final long serialVersionUID = 1L;

    private String specName;


    public Specialization(String specName) {
        super();
        if (specName == null) {
            throw new NullPointerException("Pole nie może być puste.");
        }
        this.specName = specName;

    }

    public String toString() {
        String join = "";
        join += "\nSpecjalizacja: "+
                "\nNazwa specjalizaji: " + specName;
        return join;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
