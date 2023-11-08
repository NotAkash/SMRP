package main.data;
import java.io.*;
import java.sql.Timestamp;


/*
 * Client class is used to create a blueprint from which Client objects will be created.
 * Used to evaluate the client credentials and login the user.
 */

public class Client {
    // Instance variables of client class
    private String clientName;
    private String clientPW;
    private String clientWH;
    // System provides local DateTime which is then split into two parts (date + time)
    private final String[] entryDT = new Timestamp(System.currentTimeMillis()).toLocalDateTime().toString().split("T");
    private final String   entryDate = entryDT[0];
    private final String[] entryTime = entryDT[1].split(":");

    // Client constructor used to initialize Client objects. Called when object of Client class is created
    public Client(String clientName, String clientPW, String clientWH) {
        this.clientName = clientName;
        this.clientPW = clientPW;
        this.clientWH = clientWH;
    }

    public Client() { }

    /**
     * To determine if a client can make changes to the product records and database they must be either
     * a manager or an admin. This algorithm reads the login files to determine the permission of last user.
     * @return the login credentials of the last person to use application.
     */
    public String getLastAuth(){
        // baeldung.com/java-csv-file-array
        String splitBy = ","; // using comma to separate
        String file = "src/main/data/log.csv"; //Open file
        String lastAuth = ""; String line;
        // Attempt to read the file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // while the current line of the file exists
            while ((line = br.readLine())!=null){
                lastAuth = line.split(splitBy)[0]; //get the first csv column
            }
        } catch (IOException e) {
            e.printStackTrace();
            // error handling
        }
        return lastAuth;

    }



    public String validateClient(){
        String line =  "";
        String splitBy = ","; // use comma as separator
        String file = "src/main/data/credentials.csv"; // main.data file URL

        // https://stackoverflow.com/questions/9781373/a-try-catch-method-in-while-loop
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Compressed while loop, seen in screenshot above
            while ((line = br.readLine()) != null) {
            String[] userCredential = line.split(splitBy);
            String userName = userCredential[0].toLowerCase();
            String passWord = userCredential[1].toLowerCase();

            if(userName.equals(clientName.toLowerCase())
                && passWord.equals(clientPW.toLowerCase())){
                logClient();
                if (clientName.toLowerCase().equals("" +
                        "employee")){
                    return userName;
                } else if (userName.equals("manager") || userName.equals("admin")){
                    return userName;
                }
            }
        }
            br.close();
            return "error";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "file error"; //If IOException, print stacktrace and return file error
    }


    private void logClient(){
        String file = "src/main/data/log.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append("\n"+clientName +","+ clientWH +","+ entryDate + "," + entryTime[0]+":"+entryTime[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}