package com.example.appbanvexemphim.Singleton;

public class ChooseSeat {

    private static ChooseSeat instance;  // Static instance variable
    private int selectedSeat;  // Object to store selected seat information
    private float price;

    private ChooseSeat() { }  // Private constructor to prevent direct instantiation

    public static ChooseSeat getInstance() {
        if (instance == null) {
            instance = new ChooseSeat();  // Create instance if not already created
        }
        return instance;
    }

    public void setSelectedSeat(int selectedSeat) {
        this.selectedSeat = selectedSeat;
    }

    public int getSelectedSeat() {
        return selectedSeat;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
