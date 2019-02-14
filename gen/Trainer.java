import org.antlr.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Trainer {

    static String name ;
    static ArrayList<Double> stats = new ArrayList<>();
    static HashMap<String, ArrayList<Double>> users = new HashMap<>();
    static MyVisitor mv = new MyVisitor();

    public void InPut(){

        System.out.println("Analizador Estilografico >[Python 3]<");
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su Nombre: ");
        name = sc.nextLine();
        System.out.println();
    }
    public void Read(HashMap<String, ArrayList<Double>> db ){

        File file = new File("db/users_db");
        FileReader fileR;
        BufferedReader file2 = null;

        try {
            fileR = new FileReader(file);
            file2 = new BufferedReader(fileR);

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo "+file.getName());
        }

        try {
            String linea;
            while((linea=file2.readLine())!=null){
                //System.out.println("linea.- "+linea);

                if(linea.contains(":")){
                    String [] user = linea.split(":");
                    String [] params = user[1].split("&");
                    ArrayList<Double> param = new ArrayList<>();
                    for (int i = 0; i <params.length ; i++)
                        param.add(Double.parseDouble(params[i].trim()));
                    users.put(user[0],param);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void WriteOut(){
        FileWriter file = null;
        try {
            file = new FileWriter("db/users_db", true);
            PrintWriter filePw = new PrintWriter(file);

            filePw.write( name +" : " );
            for (int i = 0; i < stats.size() ; i++) {
                if (i == stats.size()-1){
                    //System.out.println("fin");
                    filePw.write( stats.get(i)+"\n" );
                }else{
                    filePw.write( stats.get(i)+"&");
                }

            }
            file.close();
        } catch (IOException e){
            System.out.println("No se encontro el archivo "+file.getEncoding());
        }
    }
    public void Calstats(){

        // Porcetajes
        float perUcamel = (float) mv.Ucamel_case_cont/mv.numlines;
        float perLcamel = (float) mv.Lcamel_case_cont/mv.numlines;
        float perAllcaps = (float) mv.all_caps_count/mv.numlines;
        float perSmallcaps = (float) mv.small_caps_count/mv.numlines;

        System.out.println("Upper Camel Case: "+String.format("%.2f", perUcamel*100)+"%");
        double perUcamelF = (double) Math.round(perUcamel * 100d) / 100d;
        stats.add(perUcamelF);

        System.out.println("Lower Camel Case: "+String.format("%.2f",perLcamel*100)+"%");
        double perLcamelF = (double) Math.round(perLcamel * 100d) / 100d;
        stats.add(perLcamelF);

        System.out.println("All Caps: "+String.format("%.2f",perAllcaps*100)+"%");
        double perAllcapsF = (double) Math.round(perAllcaps * 100d) / 100d;
        stats.add(perAllcapsF);

        System.out.println("Small Caps: "+String.format("%.2f",perSmallcaps*100)+"%");
        double perSmallcapsF = (double) Math.round(perSmallcaps * 100d) / 100d;
        stats.add(perSmallcapsF);
        System.out.println();
        // Cantidades
        System.out.println("Palabras en Español: "+mv.spanish_words_cont);
        stats.add((double)mv.spanish_words_cont);
        System.out.println("Abreviaciones: "+mv.abreviations_words_cont);
        stats.add((double)mv.abreviations_words_cont);
        System.out.println("Palabras Desconocidas: "+mv.other_words_cont);
        stats.add((double)mv.other_words_cont);
        System.out.println("Palabras Incompletas: "+mv.uncomplete_words_cont);
        System.out.println("Palabras en Ingles: "+mv.english_words_cont);
        stats.add((double)mv.english_words_cont);
    }

    public void Simil(){
        System.out.println();
        System.out.println("El codigo ingresado por el usuario: "+name);
        System.out.println("Tiene una similitud del: "+"%");
        System.out.println("Con el usuario: ");
    }

    public void OutPut(){
        Read(users);
        //System.out.println("lo que se muestra en consola");
        //System.out.println("user"+users.size());
        if (users.size()>=1){
            Calstats();
            Simil();
        }else
            Calstats();

    }
    public static void main(String[] args) throws IOException {

        Trainer t = new Trainer();
        t.InPut();
        Python3Lexer lexer = new Python3Lexer(CharStreams.fromFileName("source/in.txt"));
        CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);
        Python3Parser parser = new Python3Parser(tokens);
        ParseTree tree = parser.file_input();
        MyVisitor<Object> loader = new MyVisitor<Object>();
        loader.visit(tree);
        // Resultados del Analisis Estilografico
        t.OutPut();
        t.WriteOut();
    }
}