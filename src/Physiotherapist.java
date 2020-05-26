

public class Physiotherapist extends ObjectPlusPlus {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String name;
    private String permissionID;

    public Physiotherapist(String firstName, String name, String permissionID) {
        super();

        if (firstName == null) {
            throw new NullPointerException("Pole imie nie może być puste.");
        }
        this.firstName = firstName;
        if (name == null) {
            throw new NullPointerException("Pole nazwisko nie może być puste.");
        }
        this.name = name;
        this.permissionID = permissionID;


    }

    public String getPermissionID() {
        return permissionID;
    }

    public String toString() {
        String join = "";
        join += "\nFizjoterapueta:"+
                "\nImie: " + firstName +
                "\nNazwisko: " + name ;
        return join;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}