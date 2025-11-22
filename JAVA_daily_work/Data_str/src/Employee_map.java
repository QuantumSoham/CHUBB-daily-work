import java.util.*;

public class Employee_map {
    public static void main(String[] args) {
        //map with company name as key, list of emp names as value
        Map<String, List<String>> companyMap = new HashMap<>();
        companyMap.put("TCS", Arrays.asList("Amit", "Ravi", "Sneha"));
        companyMap.put("Infosys", Arrays.asList("Priya", "Karan", "Nisha"));
        companyMap.put("Wipro", Arrays.asList("Manoj", "Divya", "Harsh"));
        companyMap.put("Accenture", Arrays.asList("Kunal", "Meera", "Asha"));
        companyMap.put("TechMahindra", Arrays.asList("Rohan", "Simran", "Aayush"));

        System.out.println("=== Company and Employees ===");
        for (Map.Entry<String, List<String>> entry : companyMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        String targetCompany = "Cognizant";
        String targetEmployee = "Ram";

        
        if (!companyMap.containsKey(targetCompany)) {
            System.out.println("\n" + targetCompany + " not found. Adding it...");
            companyMap.put(targetCompany, new ArrayList<>(Arrays.asList("Ram", "Shyam", "Anil")));
        }

       
        List<String> empList = companyMap.get(targetCompany);

        if (empList.contains(targetEmployee)) {
            System.out.println("\n" + targetEmployee + " is working in " + targetCompany);
        } else {
            System.out.println("\n" + targetEmployee + " not found in " + targetCompany);
        }

        // Print updated map
        System.out.println("\n=== Updated Company List ===");
        for (Map.Entry<String, List<String>> entry : companyMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
