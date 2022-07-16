package ghanekar.vaibhav.allsamples2.fragments.apicall.volley;

public class PostApiRequestModel {

    private String name;
    private String salary;
    private String age;

    public PostApiRequestModel(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PostApiRequestModel{" +
                "name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
