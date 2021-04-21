package serialization;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Employee implements Serializable {

    private String name;
    private int age;
    private int income;
    private String ssn;
    private String sensitive;

//    private static final ObjectStreamField[] serialPersistentFields = {
//            new ObjectStreamField("name", String.class),
//            new ObjectStreamField("ssn", String.class)
//    };

    public Employee(String name, int age, int income, String ssn) {
        this.name = name;
        this.age = age;
        this.income = income;
        this.ssn = ssn;
    }

    private static String encrypt(String input) {
        return input;
    }

    private static String decrypt(String input) {
        return input;
    }

    private Object writeReplace() {
        Employee existing = EmployeePool.getByName(this.name);
        return existing != null ? existing : this;
    }

    private void writeObject(ObjectOutputStream oos) throws Exception {
        ObjectOutputStream.PutField putFields = oos.putFields();
        putFields.put("name", name);
        putFields.put("income", income);
        putFields.put("ssn", encrypt(ssn));
        oos.writeFields();
    }

    private void readObject(ObjectInputStream ois) throws Exception {
        ObjectInputStream.GetField getFields = ois.readFields();
        this.name = (String) getFields.get("name", null);
        this.income = getFields.get("income", 0);
        this.ssn = decrypt((String) getFields.get("ssn", null));
        this.sensitive = (String) getFields.get("sensitive", "value");
    }

    private synchronized Object readResolve() {
        Employee existing = EmployeePool.getByName(this.name);

        if (existing == null) {
            EmployeePool.add(name, this);
            return this;
        } else {
            copyDataOn(existing);
            return existing;
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", income=" + income +
                ", ssn='" + ssn + '\'' +
                ", sensitive='" + sensitive + '\'' +
                '}';
    }

    private void copyDataOn(Employee dest) {
        dest.name = this.name;
        dest.age = this.age;
        dest.income = this.income;
        dest.ssn = this.ssn;
        dest.sensitive = this.sensitive;
    }
}
