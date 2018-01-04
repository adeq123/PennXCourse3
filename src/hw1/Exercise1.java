package hw1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
 
public class Exercise1 {
 
    public static void main(String[] args) {
        List<Adres> listAdres = new ArrayList<>();
 
        listAdres.add(new Adres("£anowa" , 34 , "90-134"));
        listAdres.add(new Adres("Traktorowa" , 14 , "93-534"));
 
        listAdres.add(new Adres("Plantowa" , 45 , "90-200"));
        listAdres.add(new Adres("£anowa" , 35 , "90-137"));
 
        listAdres.add(new Adres("Plantowa" , 10 , "90-184"));
        listAdres.add(new Adres("Sêpia" , 30 , "93-434"));
 
        listAdres.add(new Adres("£anowa" , 2 , "90-128"));
        listAdres.add(new Adres("Sêpia" , 22 , "93-429"));
 
        listAdres.add(new Adres("Szpitalna" , 10 , "97-134"));
        listAdres.add(new Adres("Szpitalna" , 20 , "97-145"));
 
        List<Adres>  copyListAdres = new ArrayList<>(listAdres);
 
        showList(copyListAdres);
 
        changeAllNameStreet(copyListAdres);
 
        showList(copyListAdres);
 
        List<Adres> sortCopyListAdres = new ArrayList<Adres>();
        sortCopyListAdres = sortByStreetNumber(copyListAdres);
 
        showList(sortCopyListAdres);
 
    }
 
    public static void showList(List<Adres> listAdres) {
        System.out.println();
        for (Adres adres : listAdres) {
            System.out.println(adres);
        }
    }
 
    public static void changeAllNameStreet(List<Adres> listAdres) {
        for (Adres adres: listAdres) {
            adres.setNameStreet("Jagielloñska");
        }
    }
 
    public static List<Adres> sortByStreetNumber(List<Adres> listAdres) {
        
        List<Adres> sortCopyListAdres = new ArrayList<Adres>(listAdres);
        Collections.sort(sortCopyListAdres, (Comparator<Adres>) (Adres a1, Adres a2) -> a1.compareTo(a2));
        return sortCopyListAdres;
    }
 
}