package main.java;

public class MathOperation {

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public boolean validateName(String custname) {
        return custname != null && custname.equals("James");
    }
}
