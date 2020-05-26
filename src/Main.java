
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class Main implements ClassName{

    public static void main(String[] args) throws Exception  {
        if(new File("data").isFile())
        {
            try
            {
                FileInputStream fileInput = new FileInputStream("data");
                ObjectInputStream streamInput = new ObjectInputStream(fileInput);
                ObjectPlus.readExtents(streamInput);
                streamInput.close();
                fileInput.close();
            }
            catch(IOException i){
                i.printStackTrace();
                return;
            }
            catch(ClassNotFoundException c){
                System.out.println("Nie znaleziono klasy.");
                c.printStackTrace();
                return;
            }
        }


        LocalDate date = LocalDate.of(1999,1,11);
        LocalDate date2 = LocalDate.of(1991,1,11);
        LocalDate date3 = LocalDate.of(2000,1,11);
        LocalDate date4 = LocalDate.of(2015,1,11);
        LocalDate date5 = LocalDate.of(2010,1,11);
        LocalDate date6 = LocalDate.of(2025,1,11);
        LocalDate date7 = LocalDate.of(2030,1,11);

        Physiotherapist physiotherapist1 = new Physiotherapist("Jan", "Sałata", "POT21331");
        Physiotherapist physiotherapist2 = new Physiotherapist("Stefan", "Burczymucha","POT23341");

        Specialization specialization1 = new Specialization("Fizjoterapia niemowląt");
        Specialization specialization2 = new Specialization("Choroby kręgosłupa dzieci");

        PhysiotherapistSpecialization physiotherapistSpecialization1= new PhysiotherapistSpecialization(date, specialization1, physiotherapist1);
        PhysiotherapistSpecialization physiotherapistSpecialization2 = new PhysiotherapistSpecialization(date2,specialization2, physiotherapist2);


        AvailableVisitType availablevisitType1 = new AvailableVisitType("Rehabilitacja niemowląt", 100,1000);
        AvailableVisitType availablevisitType2 = new AvailableVisitType("Wizyta ostopeatyczna", 100,1000);

        AvailableVisitType.Visit visit1 = availablevisitType1.createVisit(date6, 150);
        AvailableVisitType.Visit visit2 = availablevisitType1.createVisit(date7, 350);
        AvailableVisitType.Visit visit3 = availablevisitType2.createVisit(date3, 632);

        Permission permission1 = new Permission("Praca z dziećmi", date4, date6);
        Permission permission2 = new Permission("Praca z dziećmi", date5, date7);


        //Asocjacja zwykła
        System.out.println("-------------------Asocjacja zwykła--------------------");
        visit1.addLink(physiotherapistRole, visitRole, physiotherapist2);

        visit1.showLinks(physiotherapistRole, System.out);
        physiotherapist2.showLinks(visitRole, System.out);

        //Asocjacja z atrybutem
        System.out.println("-------------------Asocjacja z atrybutem-------------------");



        physiotherapist1.showLinks(specializationRole, System.out);


        //Asocjacja z kwalfikowana
        System.out.println("-------------------Asocjacja z kwalfikowana-------------------");

        permission1.addLink(physiotherapistRole, permissionRole, physiotherapist1, physiotherapist1.getPermissionID());
        permission2.addLink(physiotherapistRole, permissionRole, physiotherapist2, physiotherapist2.getPermissionID());
        System.out.println(permission1.getLinkedObject(physiotherapistRole, "POT21331"));
        //physiotherapist2.showLinks(physiotherapistRole, System.out);

        //Kompozycja
        System.out.println("-------------------Kompozycja-------------------");

        availablevisitType1.addPart(visitRole, availableVisitTypeRole, visit1);
        availablevisitType1.addPart(visitRole, availableVisitTypeRole, visit2);

        availablevisitType1.showLinks(visitRole, System.out);
        visit1.showLinks(availableVisitTypeRole, System.out);


        //Kompozycja warunek 1 Blokowanie samodzielanego tworzenia czesci - przez klase wewnętrzna
        //Kompozycja Warunek 2 Zakazanie współdzielnia całości:
       // availablevisitType2.addPart(visitRole, availableVisitTypeRole, visit1);
        //Kompozycja Warunek 3 Usuwanie części przy usuwaniu całości
       // availablevisitType1.deleteObjectAndLink(visitRole,availableVisitTypeRole);
        //ObjectPlus.showExtents((AvailableVisitType.class));
       // ObjectPlus.showExtents((AvailableVisitType.Visit.class));
        visit1.showLinks(availableVisitTypeRole, System.out);



        try
        {
            FileOutputStream fileOutput = new FileOutputStream("data");
            ObjectOutputStream StreamOutput = new ObjectOutputStream(fileOutput);
            ObjectPlus.saveExtents(StreamOutput);
            StreamOutput.close();
            fileOutput.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }


    }

}
