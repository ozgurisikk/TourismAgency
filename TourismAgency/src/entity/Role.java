package entity;

public enum Role {
    ADMIN,
    EMPLOYEE;
    public static Role fromString(String roleStr) {
        return Role.valueOf(roleStr.toUpperCase()); // Enum değeri olarak verilen string'i enum'a dönüştürür
    }
}
