
import java.time.LocalDate;

public class AvailableVisitType extends ObjectPlusPlus{

    private  String visitTypeName;
    private int minPrice;
    private int maxPrice;


    public AvailableVisitType(String visitTypeName, int minPrice, int maxPrice){
        super();
        if (visitTypeName == null){throw new NullPointerException("Pole nie może być puste.");}
        this.visitTypeName = visitTypeName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String toString() {

        String join = "";
        join +="\nDostępne typy wizyt: "+
                "\nNazwa typu wizty: " + visitTypeName+
                "\nPrzedział cen od " + minPrice + " do " +maxPrice + " zł";

        return join;
    }


    public Visit createVisit(LocalDate date, int price){
       return new Visit( date, price);
    }

    public void deleteObjectAndLink(String role, String reverseRoleName) throws Exception{
        for (Object o : this.getLinks(role)){
            ObjectPlus.deleteEkstension(this);
            ObjectPlus.deleteEkstension(o);
            deleteLinks(role);
           ((Visit) o).deleteLinks(reverseRoleName);

        }
    }

    public class Visit extends ObjectPlusPlus {

        private static final long serialVersionUID = 1L;

        private LocalDate date;
        private int price;

        private Visit(LocalDate date, int price){
            super();
            if (visitTypeName == null){throw new NullPointerException("Pole daty ie może być puste.");}
            this.date = date;
            if(price <=maxPrice && price >= minPrice){
                this.price = price;
            }
            else{
                throw new  IllegalArgumentException("Wartosc ceny nie zawiera się w przedziale: " +minPrice + " - " +maxPrice);
            }

        }
        public String toString() {
            String join = "";
            join += "\nWizyty:"+
                    "\nData wizyty: " + date+
                    "\nCena wizyty: " + price;

            return join;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            if(price <=maxPrice && price >= minPrice){
                this.price = price;
            }
            else{
                throw new  IllegalArgumentException("Wartosc ceny nie zawiera się w przedziale: " +minPrice + " - " +maxPrice );
            }
        }


    }

}
