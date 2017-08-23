package tracks;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Erin on 7/3/2017.
 */
public class Attendee implements Serializable{
    private String name;
    private String email;
    private String status;
    private ArrayList<String> classes;
    private Integer totalCost;
    private Boolean hotel;
    private Boolean parking;


    private Integer classCost;
    private Integer hotelFee = 185;
    private Integer parkingFee = 10;
    private Integer employeeCost = 850;
    private Integer studentCost = 1000;
    private Integer speakerCost = 0;
    private Integer othersCost = 1350;


    public Attendee(String name, String email, String status, ArrayList classes, Boolean hotel, Boolean parking) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.classes = classes;
        this.hotel = hotel;
        this.parking = parking;

        CalculateCosts();

    }

    public Attendee() {
        name = "";
        email = "";
        classes = new ArrayList<String>();
        status = "";
        hotel = false;
        parking = false;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList getClasses() {
        return classes;
    }

    public void setClasses(ArrayList classes) {
        this.classes = classes;
    }

    public Integer getTotalCost() {
        return totalCost;
    }


    public Boolean getHotel() {
        return hotel;
    }

    public void setHotel(Boolean hotel) {
        this.hotel = hotel;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
        CalculateCosts();
    }


    public Integer getClassCost() {
        return classCost;
    }


    private void CalculateCosts(){
        if ( this.classes != null) {
            Integer numClasses = this.classes.size();
            switch (status) {
                case "JHU Employee":
                    this.classCost = employeeCost;
                    break;
                case "JHU Student":
                    this.classCost = studentCost;
                    break;
                case "Speaker":
                    this.classCost = speakerCost;
                    break;
                default:
                    this.classCost = othersCost;
                    break;
            }

            this.totalCost = this.classCost * numClasses;



            if (this.hotel == true) {
                this.totalCost = this.totalCost + hotelFee;
            }
            if (this.parking == true && this.hotel == false) {
                this.totalCost = totalCost + parkingFee;
            }
        }
    }

    public void RemoveClass(int i){
        classes.remove(i);
        this.totalCost = totalCost - classCost;
    }
}
