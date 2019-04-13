package dk.patientsystemet.demo.Model;

public class Patient {

    private int id;
    private int noteId;
    private int age;
    private String cpr;
    private int height;
    private int weight;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String note;
    private String date;
    private String searchword;
    private int zip;
    private String address;
    private String city;
    private int phone;

    public Patient() {
    }

    public Patient(int id, int noteId, int age, String cpr, int height, int weight, String firstName, String lastName, String birthDate, String note, String date, String searchword, int zip, String address, String city, int phone) {
        this.id = id;
        this.noteId = noteId;
        this.age = age;
        this.cpr = cpr;
        this.height = height;
        this.weight = weight;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.note = note;
        this.date = date;
        this.searchword = searchword;
        this.zip = zip;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSearchword() {
        return searchword;
    }

    public void setSearchword(String searchword) {
        this.searchword = searchword;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
