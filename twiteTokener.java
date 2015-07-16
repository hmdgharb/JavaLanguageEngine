import java.io.*;
import java.util.*;

class box{

	long twite_number;
	String in;
	String Type;

	box(){
	}

	box(long t_n, String n, String tp){

		twite_number = t_n;
		in = n;
		Type = tp;
	}

	void detail(){

		System.out.print(" twite number: " + twite_number + ", Type: \"" + Type + "\", data: \"" + in + "\"");
	}
	
	String result(){
		
		String rs = " twite number: " + twite_number + ", Type: \"" + Type + "\", data: \"" + in + "\"";
		return rs;
	}
}

class OnlineSpecific extends box {
	
	OnlineSpecific(long t_n, String d, String tp){
		
		super(t_n, d, tp);
	}
	

	public void printOnlineSpecificInfo(){
		
		detail();
		System.out.println(" is in class: \"OnlineSpecific\"" );
	}
	
	String OnlineSpecificResult(){

		String g = result();
		g += " is in class: \"OnlineSpecific\"";
		return g;
	}
}

class Miscellaneous extends box {
	
	Miscellaneous(long t_n, String d, String tp){
		
		super(t_n, d, tp);
	}
	

	public void printMiscellaneousInfo(){
		
		detail();
		System.out.println(" is in class: \"Miscellaneous\"" );
	}
	
	String MiscellaneousResult(){

		String g = result();
		g += " is in class: \"OnlineSpecific\"";
		return g;
	}
}

class LanguageEngine{

	InputStream readF;
	public List<Miscellaneous> myMiscellaneousList = new ArrayList<Miscellaneous>();
	public List<OnlineSpecific> myOnlineSpecificList = new ArrayList<OnlineSpecific>();

	private boolean isNumeric(String str) {
    		if (str == null || str == "\n" || str.length() == 0 || str == " \n") {
    		    return false;
    		}
    
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
        		if (Character.isDigit(str.charAt(i)) == false) {
            			return false;
        		}
    		}
    		
		return true;
	}	
	
	private boolean checkNumber(String t){

		//check for number values
		for(int i = 0; i < t.length(); ){
			
			if( (t.charAt(i) >= '1' && t.charAt(i) <= '9' || t.charAt(i) == '0') || (t.charAt(i) == ':' && i != t.length() -1)){
	
				i++;
				if(i == t.length()){
					
					return true;
				}
			}
			else
				i = t.length();
		}
		return false;
	}

	private boolean checkURL(String t){

		if(t.length() >= 7){
			
			if(t.charAt(0) == 'h' && t.charAt(1) == 't' && t.charAt(2) == 't' && t.charAt(3) == 'p' && t.charAt(4) == ':' && t.charAt(5) == '/' && t.charAt(6) == '/')
				return true;
			
			else if(t.charAt(0) == 'h' && t.charAt(1) == 't' && t.charAt(2) == 't' && t.charAt(3) == 'p' && t.charAt(4) == 's' && t.charAt(5) == ':' && t.charAt(6) == '/' && t.charAt(7) == '/')
				return true;
		}
		
		else if(t.length() >= 5){
			if(t.charAt(0) == 'f' && t.charAt(1) == 't' && t.charAt(2) == 'p' && t.charAt(3) == ':' && t.charAt(4) == '/' && t.charAt(5) == '/')
				return true;
		}
		
		else if(t.length() >= 4){
			if(t.charAt(0) == 'w' && t.charAt(1) == 'w' && t.charAt(2) == 'w' && t.charAt(3) == '.')
				return true;
		}
		
		return false;
	}

	private boolean checkAtSign(String t){

		if(t.charAt(0) == '@')
			return true;
		return false;
	}

	private boolean checkHashtag(String t){

		if(t.charAt(0) == '#')
			return true;
		return false;
	}

	private boolean checkEmoticon(String t){

		if(t.length() == 2){		
			if( (t.charAt(0) == '\\' && t.charAt(1) == ':') ||(t.charAt(0) == '/' && t.charAt(1) == ':') || (t.charAt(0) == '(' && t.charAt(1) == ':') || (t.charAt(0) == ')' && t.charAt(1) == ':') ||(t.charAt(0) == ':' && t.charAt(1) == '|') || (t.charAt(0) == ':' && t.charAt(1) == '(') || (t.charAt(0) == ':' && t.charAt(1) == ')') ||(t.charAt(0) == '|' && t.charAt(1) == ':') || (t.charAt(0) == 'o' && t.charAt(1) == '_'))
				 return true;
		}
		else if(t.length() == 3){
			if( (t.charAt(0) == 'o' && t.charAt(1) == '_' && t.charAt(2) == 'O') || (t.charAt(0) == 'o' && t.charAt(1) == '_' && t.charAt(2) == 'o') || (t.charAt(0) == 'O' && t.charAt(1) == '_' && t.charAt(2) == 'O') || (t.charAt(0) == 'O' && t.charAt(1) == '_' && t.charAt(2) == 'o') || (t.charAt(0) == ')' && t.charAt(1) == '-' && t.charAt(2) == ':') || (t.charAt(0) == '(' && t.charAt(1) == '-' && t.charAt(2) == ':') || (t.charAt(0) == '|' && t.charAt(1) == '-' && t.charAt(2) == ':'))
				return true;
		}
		
		return false;
	}

	private void addToCollection(long t_n, String t){
		
		if(checkNumber(t))
			myMiscellaneousList.add(new Miscellaneous(t_n, t, "numerical"));

		else if(checkURL(t))
			myOnlineSpecificList.add(new OnlineSpecific(t_n, t, "URL" ));
		
		else if(checkAtSign(t))
			myOnlineSpecificList.add(new OnlineSpecific(t_n, t, "@" ));
		
		else if(checkHashtag(t))
			myOnlineSpecificList.add(new OnlineSpecific(t_n, t, "#" ));
		
		else if(checkEmoticon(t))
			myOnlineSpecificList.add(new OnlineSpecific(t_n, t, "E" ));
	
		
		else
			myMiscellaneousList.add(new Miscellaneous(t_n, t, "other"));
	}

	public void showResult(){
	
		for(int i= 0; i < myOnlineSpecificList.size(); i++){
			
			OnlineSpecific OS = myOnlineSpecificList.get(i);
			OS.printOnlineSpecificInfo();
		}
		
		for(int i= 0; i < myMiscellaneousList.size(); i++){
			
			Miscellaneous M = myMiscellaneousList.get(i);
			M.printMiscellaneousInfo();
		}
	}

        public void readFile(String name){

                try {
			File fileDir = new File(name);

                        BufferedReader in = new BufferedReader(
                                                new InputStreamReader(
                        new FileInputStream(fileDir), "UTF8"));

                        String str;

                        while ((str = in.readLine()) != null) {
                        	System.out.println(str);
                        }
                        in.close();
                }
                catch (UnsupportedEncodingException e)
                {
                        System.out.println(e.getMessage());
                }
                catch (IOException e)
                {
                        System.out.println(e.getMessage());
                }
                catch (Exception e)
                {
                        System.out.println(e.getMessage());
                }
        }

	public void writeFile(){
		try{
			System.out.println("The output Result is in Result.txt file");	
			PrintWriter ot = new PrintWriter("./Result.txt");
			for(int i= 0; i < myOnlineSpecificList.size(); i++){
			
				OnlineSpecific OS = myOnlineSpecificList.get(i);
				ot.println(OS.OnlineSpecificResult());
			}
		
		
			for(int i= 0; i < myMiscellaneousList.size(); i++){
			
				Miscellaneous M = myMiscellaneousList.get(i);
				ot.println(M.MiscellaneousResult());
			}
			ot.close();	
		}
		catch (IOException e) {
			e.printStackTrace();
        	}
	}

	public void parsingText(String fileName){

		try{
			File fileDir = new File(fileName);

			BufferedReader in = new BufferedReader(
						new InputStreamReader(
							new FileInputStream(fileDir), "UTF8"));
			String str;
			long twite_number;
			int line = 0;
			while ((str = in.readLine()) != null) {
				line +=1;
				System.out.println("the Line number " + line + " is in processing");
				String[] lineArray = str.split("\\s+");
				if(isNumeric(lineArray[0]) ){
					
					twite_number = Long.parseLong(lineArray[0]);
					for(int i=1; lineArray.length != i; i++){
						addToCollection(twite_number, lineArray[i]);
					}
				}
			}
			in.close();

		}catch(IOException e){
		
			System.out.print("Exception");
		}
	}
}
public class twiteTokener{
	public static void main(String[] args){
		LanguageEngine LNG = new LanguageEngine();
	
		if (args.length !=  0){
			LNG.parsingText(args[0]);
			//LNG.showResult();
			LNG.writeFile();
		}else{
			System.out.println("usage: java second <input-file> ");
		}

	}
} 
