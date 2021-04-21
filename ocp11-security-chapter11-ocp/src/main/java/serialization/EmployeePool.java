package serialization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmployeePool {

    private static final Map<String, Employee> POOL = new ConcurrentHashMap<>();

    public static void add(String name, Employee employee) {
        POOL.put(name, employee);
    }

    public static Employee getByName(String name) {
        return POOL.get(name);
    }


    public static Map<String, Employee> getEmployees() {
        return POOL;
    }
}
