import java.time.LocalDate;

public class Permission extends ObjectPlusPlus   {
    private static final long serialVersionUID = 1L;

    private String permissionName;
    private LocalDate validFrom;

    private LocalDate validTo;
    public Permission(String permissionName, LocalDate validFrom, LocalDate validTo){
        super();
        if (permissionName == null){throw new NullPointerException("Pole nie może być puste.");}
        this.permissionName = permissionName;
        if (validFrom == null){throw new NullPointerException("Pole nie może być puste.");}
        this.validFrom = validFrom;
        if (validTo == null){throw new NullPointerException("Pole nie może być puste.");}
        this.validTo = validTo;
    }

    public String toString() {
        String join = "";
        join += "\nPozwolenie:"+
                "\nNazwa pozwolenia: " + permissionName +
                "\nWażne od: " + validFrom +
                "\nWażne do: " + validTo;

        return join;
    }

    //Settery i Gettery
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }
}
