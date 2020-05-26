import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;


public class ObjectPlusPlus extends ObjectPlus implements Serializable {

    protected static HashSet<ObjectPlusPlus> allParts = new HashSet<>();
    protected Hashtable<String, HashMap<Object, ObjectPlusPlus>> links = new Hashtable<>();

    public ObjectPlusPlus() {
        super();
    }

    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
        HashMap<Object, ObjectPlusPlus> objectLink;


        if(counter < 1) {
            return;
        }

        if(links.containsKey(roleName)) {

            objectLink = links.get(roleName);
        }
        else {
            objectLink = new HashMap<Object, ObjectPlusPlus>();
            links.put(roleName, objectLink);
        }

        if(!objectLink.containsKey(qualifier)) {
            objectLink.put(qualifier, targetObject);
            targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }


    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }


    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws Exception {
        if(allParts.contains(partObject)) {
            throw new Exception("Ta czesc jest juz powiazana z jakas caloscia!");
        }

        addLink(roleName, reverseRoleName, partObject);

        allParts.add(partObject);
    }


    public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
        HashMap<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            throw new Exception("Brak powiazan dla roli: " + roleName);
        }

        objectLinks = links.get(roleName);

        return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }


    public boolean areLinks(String roleName) {
        if(!links.containsKey(roleName)) {
            return false;
        }

        HashMap<Object, ObjectPlusPlus> objectLinks = links.get(roleName);
        return objectLinks.size() > 0;
    }



    public void showLinks(String roleName, PrintStream stream) throws Exception {
            HashMap<Object, ObjectPlusPlus> objectLink;

            if(!links.containsKey(roleName)) {
                // Brak powiazan dla tej roli
                throw new Exception("No links for the role: " + roleName);
            }

            objectLink = links.get(roleName);

            Collection col = objectLink.values();

            stream.println(this.toString() + " (" + this.getClass().getSimpleName() + ") links for the '" + roleName + "' role:");

            for(Object obj : col) {
                stream.println("   " + obj);
            }
    }
    //Zwraca obiekt odnaleziony na podstawie kwalifikatora (dla podanej nazwy roli).
    //Dziala w oparciu o asocjacje kwalifikowana.

    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        HashMap<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            // Brak powiazan dla tej roli
            throw new Exception("Brak powiazan dla roli: " + roleName);
        }

        objectLinks = links.get(roleName);
        if(!objectLinks.containsKey(qualifier)) {
            // Brak powiazan dla tej roli
            throw new Exception("Brak powiazania dla kwalifikatora: " + qualifier);
        }
        return objectLinks.get(qualifier);
    }
    public void deleteLinks(String roleName) throws Exception {
        HashMap<Object, ObjectPlusPlus> objectLink;

        if(links.containsKey(roleName)) {

            links.remove(roleName);
        }


    }


}