import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Scanner;
public class Dddd {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        File folder = new File ("CS1A_Builders_Employees");
        folder.mkdir();  
        int num =0;
        while (num<1){
            System.out.print(">> CS1A Builders' Daily Time Record System <<\n"
                            + "[1] Login\n[2] Register\n[3] Quit\nChoice: ");
            String choice = sc.nextLine(); 
        
        switch (choice){
            case "1":
                login(folder);
                break;
            case "2":
                register(folder);
                break;
            case "3":
                switch (quit()){
                    case "1":
                        num++;
                        break;
                    case "2":
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.\n");}
                break;
            default:
                System.out.println("Invalid input. Please try again.\n");
       }}      
    } //end of main method
    
    public static void register(File folder){ 
        Scanner sc = new Scanner (System.in);
        Random rand = new Random ();      
        
        System.out.print("\n>> REGISTRATION <<\nFirst Name: ");
        String firstName = sc.nextLine();
            while (firstName.isEmpty()||firstName.isBlank()){
                System.out.print("Please fill out the this field.\nFirst Name: ");
                firstName = sc.nextLine();
            }
        System.out.print("Last name: ");
        String lastName = sc.nextLine();
            while (lastName.isEmpty()||lastName.isBlank()){
                System.out.print("Please fill out the this field.\nLast Name: ");
                lastName = sc.nextLine();
            }     
        System.out.print("Password: ");
        String userPass = sc.nextLine();
            while (userPass.isEmpty()||userPass.isBlank()){
                System.out.print("Please fill out the this field.\nPassword: ");
                userPass = sc.nextLine();
            }   
        System.out.print("Confirm Password: ");
        String userConfirm = sc.nextLine();
            while (!userConfirm.equals(userPass)){
                if(userConfirm.isEmpty()||userConfirm.isBlank()){
                    System.out.print("\nPlease fill out the this field.\nConfirm Password: ");
                    userConfirm = sc.nextLine();  
                }else if(userConfirm.equals(userPass)){
                    break;
                }  
                System.out.print("\nInvalid password.\nConfirm Password: ");
                userConfirm = sc.nextLine();    
            }
        System.out.print("\nA Second Password will be your verification code. If your forgot your\n"
                         + "first password, you can input your second password.\nSecond Password: ");
        String secondPass = sc.nextLine();
            while (secondPass.isEmpty()){
                System.out.print("Please don't leave it blank.\nSecond Password: ");
                secondPass = sc.nextLine();
            }    
        System.out.print("Confirm Second Password: ");
        String secondConf = sc.nextLine();
            while (!secondConf.equals(secondPass)){
                if(secondConf.isEmpty()||secondConf.isBlank()){
                    System.out.print("\nPlease fill out the this field.\nConfirm Second Password: ");
                    secondConf = sc.nextLine();  
                }else if(secondConf.equals(secondPass)){
                    break;
                }  
                System.out.print("\nInvalid password.\nConfirm Second Password: ");
                secondConf = sc.nextLine();    
            }
        int userID = rand.nextInt(10000000,10999999);
        String fullName = firstName+lastName;
        
        try {
        folder.mkdir();
        File regis = new File(folder,fullName+"_"+userID+".txt");
        
        File[] files = folder.listFiles();

        for (File nameOfFile : files) {
            if (nameOfFile.getName().startsWith(fullName)){
                
                String name = nameOfFile.getName();
                name = name.replace(fullName+"_", "");
                name = name.replace(".txt", "");
                    
                System.out.print("\nIt seems like you have already registered."
                                   + " Are you sure this account is yours?\n"
                                   + "Name: "+firstName+" "+lastName+"\nID: "+name+"\n"
                                   + "[1] YES\n[2] NO\nChoice: ");
                String choice = sc.nextLine();
                
                    switch (choice){
                        case "1":
                        System.out.print("\nWould you like to login?\n"
                                         +"[1] YES\n[2] NO\n[3] I forgot my password\nChoice: ");
                        String choice2 = sc.nextLine();
                        
                        switch (choice2){
                            case "1":
                                login(folder);
                                return;
                            case "2":
                                System.out.println();
                                return;
                            case "3":
                                forgotPass(nameOfFile);
                                return;
                            default:     
                                System.out.println("\nInvalid input. Please try again\n");
                                return;
                        }
                        case "2":
                            break;
                        default:
                            System.out.println("\nInvalid input. Please try again\n");
                            return;
                    }
                }}
        FileWriter writer = new FileWriter(regis);

        System.out.print("\nShift time:\n[1]Morning Shift 8AM - 5PM\n"
                        +"[2]Night Shift 8PM - 5AM\nChoice: ");
        String num = sc.nextLine();
            while (!num.equals("1")&&!num.equals("2")){
                System.out.print("Invalid input. Please try again.\nChoice: ");
            num = sc.nextLine();
            }                
        writer.write("CS1A Builder's Employee Log\n"
                    +"Name of employee: "+firstName+" "+lastName+"\n"
                    +"ID: "+userID+"\n"
                    +"Password: "+userPass+"\n"
                    + "Second Password: "+secondPass+"\n");
                if (num.equals("1")){
                    writer.write("Shift: Morning Shift (8AM - 5PM)\n");
                }else if (num.equals("2")){
                    writer.write("Shift: Night Shift (8PM - 5AM)\n");
                }
        writer.write("\nDate\t\tTime In\t\tTime Out\tTotal Hours");
        writer.close();
                
        System.out.print("\nYour ID is "+userID+". This is required for you to log in to our system.\n"
                        +"You are now successfully registered, "+firstName+"!\n \n"
                        +"Proceed to login? \n[1]Yes\n[2]No\nChoice: ");
        String yorn = sc.nextLine();
            switch (yorn){
                case "1":
                    login(folder);
                    break;
                case "2":
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid input. Please try again\n");
       }}catch(Exception e) {
            System.out.println("An error occurred. Please try again.\n");
        }
    } //end of register method
    
    public static void login(File folder){ 
        Scanner sc = new Scanner (System.in);
        System.out.print("\n>> LOGIN <<\nID: "); 
        String userLogin = sc.nextLine();
            while (userLogin.isEmpty()||userLogin.isBlank()){
                System.out.print("Please don't leave it blank.\nID: ");
                userLogin = sc.nextLine();
            }
        System.out.print("Password: "); 
        String userPass = sc.nextLine();
            while (userPass.isEmpty()||userLogin.isBlank()){
                System.out.print("Please don't leave it blank.\nPassword: ");
                userPass = sc.nextLine();
            }
        
        folder.mkdir();
        File[] files = folder.listFiles();
        
        try {
        for (File nameOfFile : files) {
            if (nameOfFile.getName().endsWith(userLogin+".txt")){
                
                Scanner myReader = new Scanner(nameOfFile);
                while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                while (data.contains("Password: ")){  
                    if (!data.equals("Password: "+userPass)){
                        int num = 0;
                        while (num <= 100){
                            System.out.print("\nInvalid Password.\nPassword: ");
                            userPass=sc.nextLine();
                            
                            if(data.equals("Password: "+userPass)){
                                System.out.println("\nYou are now logged in!");
                                mainPage(nameOfFile);
                                return;
                            }
                            num++;
                            if (num % 3 == 0){
                                System.out.print("\nHave you forgotten your password?\n"
                                                +"[1]YES\n"
                                                + "[2]NO\n"
                                                +"Choice: ");
                            String num2 = sc.nextLine();
                                switch (num2){
                                    case "1":
                                        forgotPass(nameOfFile);
                                        return;
                                    case "2":
                                        continue;
                                } 
                            }}
                        }else if(data.equals("Password: "+userPass)){
                            System.out.println("\nYou are now logged in!");
                            mainPage(nameOfFile);
                            return;
                        }
        }}}} 
        System.out.print("\nInvalid ID.\nIt seems you haven't registered yet.\n"
                        +"Would you like to register?\n"
                        +"[1] Yes\n"
                        +"[2] No\n"
                        +"Choice: ");  
        String numm = sc.nextLine();
        
            switch (numm){
                case "1":
                    register(folder);
                    break;
                case "2":
                    login(folder);
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        } catch ( Exception e) {
            System.out.println("An error has occured. Please try again.");}                      
    } //end of login method
    
    public static void mainPage(File nameOfFile){
        Scanner sc = new Scanner (System.in);
        
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, hh:mm:ss a");
        String todayparse = today.format(formatter);
        System.out.println("\nWelcome!\n"+todayparse);
        
        System.out.print("\n[1] Time In\n"
                          +"[2] Time out\n"
                          +"[3] Profile\n"
                          +"[4] Settings\n"
                          +"[5] Logout\n"
                          + "Choice: ");
        String choice = sc.nextLine();
        switch (choice){
            case "1":
                timeInDate(nameOfFile);
                break;
            case "2":
                timeOutTime(nameOfFile);
                break;
            case "3":
                profile(nameOfFile);
                break;
            case "4":
                settings(nameOfFile);
                break;
            case "5":
                switch (quit()){
                    case "1":
                        return;
                    case "2":
                        mainPage(nameOfFile);
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        mainPage(nameOfFile); 
                        break;
                }
                break;
            default:
                System.out.println("Invalid input. Please try again.\n");
                mainPage(nameOfFile);
        }    
    } //end of mainPage method

    public static void timeIn(File nameOfFile, String parsetoday){
        
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("hh:mm a");
        String parsenow = now.format(formatter);
        
        try{ 
        Scanner myReader = new Scanner (nameOfFile);
        FileWriter writer = new FileWriter(nameOfFile, true);  
        
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();  
                
                if (data.contains("Shift")){
                    if(data.contains("Night Shift (8PM - 5AM)")){
                        LocalTime due = LocalTime.parse("20:30");
                        Boolean isLate = now.isAfter(due);
                        
                        System.out.println("\nTime check: "+parsenow);
                        if (isLate==true) {
                           System.out.println("Attendance marked. You are late."); 
                        }
                        else{
                           System.out.println("Attendance marked.");
                        }   
       
                    }else if (data.contains("Morning Shift (8AM - 5PM)")){
                        LocalTime due = LocalTime.parse("08:30");
                        Boolean isLate = now.isAfter(due);
                        
                        System.out.println("\nTime check: "+parsenow);
                        if (isLate==true) {
                           System.out.println("Attendance marked. You are late."); 
                        }
                        else{
                           System.out.println("Attendance marked.");
                        } 
                    }   
                } if (data.startsWith(parsetoday)){
                    writer.append("\t"+parsenow);
            }}
            writer.close();
            myReader.close();     
            mainPage(nameOfFile);
        }catch(Exception e) {
            System.out.println("An error has occured. Please try again.");}
    }
    
    public static void timeInDate(File nameOfFile){
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("d MMM uuuu");
        String parsetoday = today.format(formatdate);

        try{
        BufferedWriter writer = new BufferedWriter(new FileWriter(nameOfFile, true));  
        Scanner myReader = new Scanner (nameOfFile);
        
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine(); 
                
                    if (data.startsWith(parsetoday+"\t")){
                        System.out.println("\nYou already timed in.");
                        mainPage(nameOfFile);
                        return;   
                    }
            }
            writer.append("\n"+parsetoday);
            myReader.close();
            writer.close();
            timeIn(nameOfFile, parsetoday);
        }catch (IOException e){ 
            System.out.println("An error has occured. Please try again.");
    }}
    
    public static void timeOutTime(File nameOfFile) {
        
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("d MMM uuuu");
        String parsetoday = today.format(formatdate);
        DateTimeFormatter mili = DateTimeFormatter.ofPattern("HH:mm");
        String content =" ";
        try {          
            Scanner myReader = new Scanner (nameOfFile); 
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();  
                content += data + "\n";
                if (data.startsWith(parsetoday)){
                    if(data.contains("Hours")){
                        System.out.println("\nYou have already timed out.");
                        mainPage(nameOfFile);
                        return;
                    } 

                data = data.replace(parsetoday+"\t"," ");
                String get = data.substring(0, 3);
                get = get.trim();
                String mm = data.replace(get, " ");
                mm = mm.trim();
                
                if(mm.contains("pm")){
                    mm = mm.replace("pm"," ");
                    int hr = Integer.parseInt(get);
                    hr+=12;
                    String hour =hr+mm;
                    hour = hour.strip();
                    timeOut(nameOfFile,hour,mili,parsetoday);
                }else if(mm.contains("am")){
                    mm = mm.replace("am"," ");
                    String hour =get+mm;
                    hour = hour.strip();
                    timeOut(nameOfFile,hour,mili,parsetoday);
                }}   
            }
            myReader.close(); 
            if (!content.contains(parsetoday)){
                mainPage(nameOfFile);
            } 
        } catch ( Exception e) {
          System.out.println("An error has occured. Please try again.");}
    }
    
    public static void timeOut(File nameOfFile, String hour, DateTimeFormatter mili, String parsetoday){
        LocalTime today = LocalTime.now();
        String now = today.format(mili);
        
        LocalTime timeO = LocalTime.parse(now);
        LocalTime timeI = LocalTime.parse(hour);
        DateTimeFormatter ampm = DateTimeFormatter.ofPattern("hh:mm a");
        String timeout = timeO.format(ampm);
        
        long hours = timeI.until(timeO, ChronoUnit.HOURS);
        if (hours==0){
            hours = 0;
        }
        System.out.println("\nTime check: "+timeout+"\n"
                + "Total Hours: "+hours);
        try{
           Scanner sc = new Scanner (nameOfFile); 
           FileWriter write = new FileWriter (nameOfFile, true);
           
           while (sc.hasNextLine()){
               String data = sc.nextLine();
               if (data.contains(parsetoday)){
                   write.append("\t"+timeout+"\t"+hours+" Hours");
               }
           }
           write.close();
           sc.close();
           mainPage(nameOfFile);
        }catch(Exception e){
            System.out.println("An error has occured. Please try again.");
    }}  
      
    public static void profile (File nameOfFile){  
        System.out.println();
        try {
            Scanner myReader = new Scanner (nameOfFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.startsWith("Password: ")){
                    continue;
                }
                if (data.startsWith("Second Password: ")){
                    continue;
                }
                System.out.println(data); 
            }
            myReader.close();  
            mainPage(nameOfFile);
        }catch( IOException e) {
            System.out.println("An error has occured. Please try again.");}
    }
    
    public static void settings(File nameOfFile) {
        Scanner sc = new Scanner (System.in);
        
        System.out.print("\n>> SETTINGS <<\n"
                          +"[1] Terms and Condition\n"
                          +"[2] Change Password\n"
                          +"[3] Return\n"
                          + "Choice: ");
        String choice = sc.nextLine();
        switch (choice){
            case "1":
                TermsAndCondition(nameOfFile);
                break;
            case "2":
                changepass(nameOfFile);
                break;
            case "3":
                mainPage(nameOfFile);
                break;
            default:
                System.out.println("Invalid input. Please try again");
                settings(nameOfFile);
        }
    }

    public static void changepass(File nameOfFile){

        Scanner sc = new Scanner(System.in);
        System.out.print("\n>> CHANGE PASSWORD <<\n"
                        +"Password: "); 
        String userPass = sc.nextLine();
            while (userPass.isEmpty()){
                System.out.print("Please fill in on this field.\n"
                                + "Password: ");
                userPass = sc.nextLine();
            }
        String content = "";
        try {
            Scanner myReader = new Scanner(nameOfFile);  
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
                
                if (data.startsWith("Password: ")){
                    while (!data.contains(userPass)){
                        if (!data.contains(userPass)){
                            System.out.print("\nInvalid Password.\n"
                                        +"Password: ");
                        userPass=sc.nextLine();  
                        }
                        else if(data.contains("Password: "+userPass)){
                            break;
                }}}
            content += data+"\r\n";
            }
            content = content.trim();
                System.out.print("New Password: ");
                String newPass = sc.nextLine();
                    while (newPass.isEmpty()){
                        System.out.print("Please fill in on this field.\n"
                            + "Password: ");
                    newPass = sc.nextLine();
                }
                System.out.print("Confirm New Password: ");
                String passConfirm = sc.nextLine();
                    while (!passConfirm.equals(newPass)){
                        if(passConfirm.isEmpty()||passConfirm.isBlank()){
                            System.out.print("\nPlease fill out the this field.\n"
                                    +"Confirm New Password: ");
                            passConfirm = sc.nextLine();               
                        }else if(passConfirm.equals(newPass)){
                            break;
                        }  
                        System.out.print("\nInvalid Confirm password.\n"
                                + "Confirm Password: ");
                        passConfirm = sc.nextLine(); 
                    }
                myReader.close();
                String newtext = content.replace("Password: "+userPass,"Password: "+newPass);
                FileWriter writer = new FileWriter(nameOfFile);
                writer.write(newtext);
                writer.close();
                System.out.println("\nPasword changed.");
                mainPage(nameOfFile);                       
            } catch ( Exception e) {
            System.out.println("\nAn error has occured. Please try again.\n");} 
    }
    
    public static void forgotPass(File nameOfFile) {
        Scanner sc = new Scanner (System.in);
        System.out.print("\nSecond Password: ");
        String secondPassword = sc.nextLine();

        while (secondPassword.isEmpty()){
            System.out.print("Please fill out this field.\n"
                            + "Second Password: ");
            secondPassword = sc.nextLine();
        }
        try {
            Scanner myReader = new Scanner(nameOfFile);  
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
                
                while (data.contains("Second Password: ")){ 
                    if (!data.equals("Second Password: "+secondPassword)){
                        System.out.print("\nInvalid Second Password.\n"
                                            +"Second Password: ");
                        secondPassword=sc.nextLine();
                }
                        System.out.println("\nYou are now logged in!");
                        mainPage(nameOfFile);
                        break;
                    }   
                }
                myReader.close(); 
         } catch ( Exception e) {
                System.out.println("An error has occured. Please try again.");}
    }
    
    public static void TermsAndCondition(File nameOfFile){
        Scanner sc = new Scanner (System.in);
        System.out.print("\n>> TERMS AND CONDITION <<\n\n"
                        +"Welcome to our Daily Time Recorder\n"
                        +"These terms and conditions outline the rules and regulations for the use of CS1A Builders.\n"
                        +"By using our service, you agree to submit private information. The submitted data will be\n"
                        +"hidden and only the company’s higher-ups can view the information. The following terminology \n"
                        +"applies to these Terms and Conditions, Privacy Statement, Disclaimer Notice, and all Agreements.\n"
                        +"The person who accesses this service agrees to the Company's terms and conditions.\n"
                        +"[1] Back\n"
                        + "Choice: ");
        String choice = sc.nextLine();
        
        switch (choice){
            case "1":
                settings(nameOfFile);
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                TermsAndCondition(nameOfFile);
        }
    }
    
    public static String quit(){
        Scanner sc = new Scanner (System.in);
        System.out.print("\nAre you sure you want to quit?\n"
                + "[1] YES\n"
                + "[2] NO\n"
                + "Choice: ");
        String yorn = sc.nextLine();
        System.out.println();
        return yorn;   
    } //end of quit method   
    
}
