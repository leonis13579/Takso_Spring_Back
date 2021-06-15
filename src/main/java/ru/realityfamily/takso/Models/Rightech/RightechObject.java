package ru.realityfamily.takso.Models.Rightech;

public class RightechObject {
    String _id;
    String name;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class Status{
        boolean SeatHeat;
        boolean Battery;
        boolean Oil;
        boolean CheckEngine;
        boolean Condition;
        boolean Fuel;
        boolean SeatBelt;

        public boolean isSeatHeat() {
            return SeatHeat;
        }

        public void setSeatHeat(boolean seatHeat) {
            SeatHeat = seatHeat;
        }

        public boolean isBattery() {
            return Battery;
        }

        public void setBattery(boolean battery) {
            Battery = battery;
        }

        public boolean isOil() {
            return Oil;
        }

        public void setOil(boolean oil) {
            Oil = oil;
        }

        public boolean isCheckEngine() {
            return CheckEngine;
        }

        public void setCheckEngine(boolean checkEngine) {
            CheckEngine = checkEngine;
        }

        public boolean isCondition() {
            return Condition;
        }

        public void setCondition(boolean condition) {
            Condition = condition;
        }

        public boolean isFuel() {
            return Fuel;
        }

        public void setFuel(boolean fuel) {
            Fuel = fuel;
        }

        public boolean isSeatBelt() {
            return SeatBelt;
        }

        public void setSeatBelt(boolean seatBelt) {
            SeatBelt = seatBelt;
        }
    }
}
