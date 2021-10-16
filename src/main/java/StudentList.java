import java.util.ArrayList;

public class StudentList {
    private ArrayList<Student> studentList =null;

    public StudentList() {
        studentList = new ArrayList<>();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> list) {
        studentList = list;
    }

    public ArrayList<Student> addStudent(Student student){
        getStudentList().add(student);
        return studentList;
    }
    public void search(String search){
        int check = 0;
        System.out.println("Search by name or gender: " + search);
        for(Student e:studentList){
            if(e.getFirst_name().trim().equalsIgnoreCase(search.trim()) || e.getGender().trim().equalsIgnoreCase(search.trim())){
                System.out.println(e);
                check++;
            }
        }
        if (check == 0){
            System.out.println("Not found!!");
        }
    }
}



