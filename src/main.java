import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) throws Exception {
        try{
            File directory = checkDirectory(args);
            checkNames(directory);
            GameManager gameManager = new GameManager(s-> System.out.println(s),directory);
            gameManager.start();
        }
        catch (Exception exception){
            System.out.println(exception);
        }


    }

    private static void checkNames(File directory) throws Exception {
        File[] fileNames = directory.listFiles();
        String regexPlayer = "level[0-9]+.txt";
        Pattern pattern = Pattern.compile(regexPlayer);
        for (File f:fileNames) {
            Matcher matcher = pattern.matcher(f.getName());
            if(!matcher.matches()){
                throw new Exception(f.getName()+" is not in the name format of levelX");
            }
        }

    }

    public static File checkDirectory(String[] args) throws Exception {
        if(args.length != 1)
            throw new Exception("something wrong with args params");
        File directory = new File(args[0]);

        if (directory.exists() && directory.isDirectory()) {
            return directory;
        } else {
            throw new Exception("The path is either invalid or not a directory.");
        }

    }
}
