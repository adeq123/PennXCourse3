package hw1;

public class Adres implements Comparable{
    public String getSt() {
        return st;
    }
    public int getNumber() {
        return number;
    }
    public String getKod() {
        return kod;
    }
    String st;
    int number;
    String kod;
public Adres(String st, int no, String kod){
    this.st = st;
    this.number = no;
    this.kod = kod;
}

public void setNameStreet (String streetName){
    this.st = streetName;
}

public String toString(){
    return st+" "+kod+" "+number;
}
public int compareTo(Object o) {
    Adres o2 = (Adres) o;
    if (this.getNumber() > o2.getNumber()) {
        return 1;
    } else if (this.getNumber() < o2.getNumber()) {
        return -1;
    } else {
        return 0;
    }
}

}
