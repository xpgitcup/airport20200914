package cn.edu.cup.physical

class UnitSystem {

    String systemName;
    String description;

    static hasMany = [quantityUnits: QuantityUnit]

    static constraints = {
        systemName(unique: true);
        description();
    }

    static mapping = {
    }

    String toString() {
        return "${systemName}";
    }
}
