package com.uasjsaonandroid;

public class Contact {
    private int id;
    private String name;
    private String email;
    private String address;
    private String gender;
    private String mobile;
    private String home;
    private String office;

    public Contact() {
        super();
    }
    public Contact(int id, String name, String email, String address, String gender, String mobile, String home, String office){

        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.mobile = mobile;
        this.home = home;
        this.office = office;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }


    public String getName(){
        return name ;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }


    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getHome(){
        return home;
    }

    public String getOffice(){
        return office;
    }



    public void getAddress(String address) {
        this.address = address;
    }

  //  public void getMobile(int mobile) {
 //       this.mobile = mobile;
 //   }




    public void getGender(String gender) {
        this.gender = gender;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setOffice(String office) {
    this.office = office;
    }
}
