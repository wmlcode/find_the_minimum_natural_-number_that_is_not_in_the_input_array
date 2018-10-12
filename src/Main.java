import sun.misc.FloatingDecimal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Long timestamp_start = getIntTimestamp();

        String readType = args[0] != null ? args[0] : "input";
        List<Integer> input = new ArrayList<>();

        Long read_start = getIntTimestamp();
        if (readType.equals("file")) {
            String path = args[1];
            System.out.println("path to file: " + path);
            input = getInput(path);
        } else {
            input = getInput();
        }
        Long read_ms = (getIntTimestamp() - read_start);
        System.out.println("Read time: " + read_ms.doubleValue()/1000L + " sec");

        System.out.println("Unsigned size on input: " + input.size());

        Collections.sort(input);
        System.out.println("First natural int: " + getFirstEmpty(input).toString());

        Long total_ms = (getIntTimestamp() - timestamp_start);
        System.out.println("Total time: " + total_ms.doubleValue()/1000L + " sec");
    }

    private static Long getIntTimestamp() {
        Long timestamp = new Long(System.currentTimeMillis());
        return timestamp;
    }

    private static Integer getFirstEmpty(List input) {
        for (int i = 0; i < input.size(); i++) {
            if (!input.get(i).equals(i))
                return Integer.parseInt(input.get(i).toString())-1;
        }
        return -1;
    }

    private static List<Integer> getInput() {
        List<Integer> input = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (Integer.parseInt(line) >= 0) {
                input.add(Integer.parseInt(line));
            }
            if (line.equals("\n"))
                break;
        }

        return input;
    }

    private static List<Integer> getInput(String path) {
        List<Integer> input = new ArrayList<>();
        try {
            FileReader fr = new FileReader(path);
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(" ");
                for (int i = 0; i < line.length; i++) {
                    if (Integer.parseInt(line[i]) >= 0) {
                        input.add(Integer.parseInt(line[i]));
                    }
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }
}
