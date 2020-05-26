import java.time.LocalDate;


public class PhysiotherapistSpecialization extends ObjectPlusPlus implements ClassName {
    private static final long serialVersionUID = 1L;

    private LocalDate date;



    public PhysiotherapistSpecialization(LocalDate date,Specialization specialization, Physiotherapist physiotherapist ){
        if (date == null){throw new NullPointerException("Pole data nie może być puste.");}
        this.date = date;

        specialization.addLink(ClassName.physiotherapistRole,ClassName.specializationRole , physiotherapist);
        physiotherapist.addLink(ClassName.specializationRole, ClassName.physiotherapistRole, specialization);

    }

    public String toString() {
        String join = "";
        join +="\nSpecjalizacja Fizjoterapeuty:"+
                "\nData przyznania: " +date;

        return join;
    }

}
