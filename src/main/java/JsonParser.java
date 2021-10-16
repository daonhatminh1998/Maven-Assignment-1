import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;

public class JsonParser {

    public static ArrayList<Student> parseJSOn(String url) throws ParseException {
        StudentList studentList = new StudentList();

        Client client = Client.create();
        WebResource webResource = client.resource(url);

        ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
        if (clientResponse.getStatus() != 200) {
            throw new RuntimeException("Failed" + clientResponse.toString());
        }
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));

        Iterator<Object> it =  jsonArray.iterator();

        while (it.hasNext()) {
            JSONObject jsonObject = (JSONObject) it.next();

            Student student = new Student(Integer.parseInt(jsonObject.get("id").toString()), jsonObject.get("first_name").toString(), (String) jsonObject.get("gpa"), jsonObject.get("email").toString(), jsonObject.get("gender").toString());
            studentList.addStudent(student);
        }
        System.out.println(studentList.getStudentList());
        return studentList.getStudentList();
    }

    public static  void main(String[] args) throws ParseException {
        StudentList studentList = new StudentList();
        studentList.setStudentList(parseJSOn("https://hccs-advancejava.s3.amazonaws.com/student.json"));

        studentList.search("Aida  ");
        studentList.search("male");
        studentList.search("  ");
    }

}
