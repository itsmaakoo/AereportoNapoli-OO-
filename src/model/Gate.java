package model;

public class Gate {
    private String gate;

    Gate(String gate) {
        this.gate = gate;
    }

    public String getGate() {
        return gate;
    }
    public void setGate(String gate) {
        this.gate = gate;
    }

    public String toString() {
        String stringa = "\nil gate è: " + gate;
        return stringa;
    }
}
