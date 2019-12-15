package utils;

import main.day03.Day3Instruction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AoCFileReader {
    public static List<Integer> readIntegerLineByLine(File f) {
        List<Integer> res = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.add(Integer.parseInt(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<Integer> readIntegerLine(File f) {
        List<Integer> res = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String[] line = br.readLine().split(",");
            for (String i : line) {
                res.add(Integer.parseInt(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<Long> readLongLine(File f) {
        List<Long> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String[] line = br.readLine().split(",");
            for (String i : line) {
                res.add(Long.parseLong(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static List<List<String>> readListOfCharList(File f) {
        List<List<String>> res = new ArrayList<List<String>>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> charLine = new ArrayList<String>();
                for (char c : line.toCharArray()) {
                    charLine.add(String.valueOf(c));
                }
                res.add(charLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<List<Day3Instruction>> readDay3Instructions(File f) {
        List<List<Day3Instruction>> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            res.add(new ArrayList<>(Arrays.stream(br.readLine().split(",")).map(Day3Instruction::new).collect(Collectors.toList())));
            res.add(new ArrayList<>(Arrays.stream(br.readLine().split(",")).map(Day3Instruction::new).collect(Collectors.toList())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String readOneLine(File f) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            line = br.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static List<String> readMulitpleLines(File f) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static Map<String, Character> readStringMap(File f) {
        Map<String, Character> res = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.put(line.split(" => ")[0], line.split(" => ")[1].charAt(0));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static List<int[]> readProgram(File f) {
        List<int[]> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                int[] pLine = new int[split.length];
                for (int i = 0; i < split.length; ++i) {
                    pLine[i] = Integer.parseInt(split[i]);
                }
                res.add(pLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
