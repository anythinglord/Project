import org.antlr.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Trainer {

    static String name ;
    static ArrayList<Double> stats = new ArrayList<>();
    static HashMap<String, ArrayList<Double>> users = new HashMap<>();
    static MyVisitor mv = new MyVisitor();


    public void InPut(){

        System.out.println("Analizador Estilografico [Python 3]");
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su Nombre: ");
        name = sc.nextLine();
        System.out.println();
    }

    // leer el archivo de los usuarios
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

    // Guarda en la db los datos de los usuarios
    public void WriteOut(){
        //System.out.println("Write---");
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

    // Calcula las Estadisticas de cada usuario
    public void Calstats(boolean print){

        // Estadisticas
        int total = mv.Ucamel_case_cont + mv.Lcamel_case_cont + mv.all_caps_count + mv.small_caps_count+mv.snake_case_cont;

        float perSnake = (float) mv.snake_case_cont/total;
        float perUcamel = (float) mv.Ucamel_case_cont/total;
        float perLcamel = (float) mv.Lcamel_case_cont/total;
        float perAllcaps = (float) mv.all_caps_count/total;
        float perSmallcaps = (float) mv.small_caps_count/total;

        if(print){
            System.out.println("Número de Lineas: "+mv.numlines);
            System.out.println("Snake Case: "+String.format("%.2f", perSnake*100)+"%");
            System.out.println("Upper Camel Case: "+String.format("%.2f", perUcamel*100)+"%");
            System.out.println("Lower Camel Case: "+String.format("%.2f",perLcamel*100)+"%");
            System.out.println("All Caps: "+String.format("%.2f",perAllcaps*100)+"%");
            System.out.println("Small Caps: "+String.format("%.2f",perSmallcaps*100)+"%");
        }



        double perSnakeF = (double) Math.round(perSnake * 100d) / 100d;
        stats.add(perSnakeF);
        double perUcamelF = (double) Math.round(perUcamel * 100d) / 100d;
        stats.add(perUcamelF);
        double perLcamelF = (double) Math.round(perLcamel * 100d) / 100d;
        stats.add(perLcamelF);
        double perAllcapsF = (double) Math.round(perAllcaps * 100d) / 100d;
        stats.add(perAllcapsF);
        double perSmallcapsF = (double) Math.round(perSmallcaps * 100d) / 100d;
        stats.add(perSmallcapsF);




        // Cantidades
        if(print){
            System.out.println();
            System.out.println("Cantidad de Sentencias if: "+mv.if_cont);
            System.out.println("Cantidad de ciclos While: "+mv.while_cont);
            System.out.println("Cantidad de ciclos For: "+mv.for_cont);
            System.out.println("Programacion Funcional: "+mv.lambda_exist+" cantidad: "+mv.lambda_cont);
            System.out.println("Palabras en Español: "+mv.spanish_words_cont);
            System.out.println("Abreviaciones: "+mv.abreviations_words_cont);
            System.out.println("Palabras Desconocidas: "+mv.other_words_cont);
            System.out.println("Palabras Incompletas: "+mv.uncomplete_words_cont);
            System.out.println("Palabras en Ingles: "+mv.english_words_cont);
        }

        stats.add((double)mv.if_cont);
        stats.add((double)mv.while_cont);
        stats.add((double)mv.for_cont);
        stats.add((double)mv.spanish_words_cont);
        stats.add((double)mv.abreviations_words_cont);
        stats.add((double)mv.other_words_cont);
        stats.add((double)mv.english_words_cont);

        WriteOut();
    }

    // Busca la similitud del codigo de la persona con los demas usuarios
    // usuarios almacenados en la base de datos
    public void Simil(){

        System.out.println();
        //System.out.println("El codigo ingresado por el usuario: "+name);

        double tolerance ;
        if (mv.numlines <= 15)
            tolerance = 0.3;
        else
            tolerance = 0.05;

        ArrayList<String> keys = new ArrayList<>();
        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            keys.add(String.valueOf(e.getKey()));
        }
        int maxl = 0 ,max = 0;
        String targetUser = "";
        ArrayList<Double> target = new ArrayList<>();

        for (int i = 0; i < users.size() ; i++) {
            if(keys.get(i) != name){

                //local = users.get(name);
                target = users.get(keys.get(i));
                for (int j = 0; j < users.get(keys.get(i)).size() ; j++) {
                    if(stats.get(j) >= target.get(j)*tolerance && stats.get(j) <= target.get(j)+target.get(j)*tolerance)
                        maxl++;

                }

                // por el momento solo selecciona un usuario
                // se puede mostar un conjunto de usuarios si se desea
                if(maxl > max){
                    max = maxl; maxl = 0; targetUser = keys.get(i);
                }else
                    maxl = 0;

            }
        }

        double ans = (double) Math.round(max/8 * 100d) / 100d;
        if(ans>0){
            System.out.println("El usuario: "+name);
            System.out.println("Tiene una similitud del: "+ans*100 +" %");
            System.out.println("Con el usuario: "+targetUser);
        }else
            System.out.println("No hay similitud con ningun usuario");


    }

    public void OutPut(){
        Read(users);
        if (users.size() >= 1){
            Calstats(true);
            Simil();
        }else
            Calstats(true);

    }

    public void otherUsers() throws IOException{

        //

        // Recopila el codigo de los usuarios desde CodeJam
        // Realiza el analisis Estilografico
        for (int i = 0; i < 40 ; i++) {

            File file = new File("codes/code"+(i+1)+".txt");
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
                int line = 0;
                while((linea=file2.readLine())!=null && line < 1){
                    name = linea.substring(2,linea.length());
                    line++;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            Python3Lexer lexer = new Python3Lexer(CharStreams.fromFileName("codes/code"+(i+1)+".txt"));
            CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);
            Python3Parser parser = new Python3Parser(tokens);
            ParseTree tree = parser.file_input();
            MyVisitor<Object> loader = new MyVisitor<Object>();
            loader.visit(tree);


            // Calcula las estadisticas de los usuarios
            Calstats(false);
            stats = new ArrayList<>();
        }



    }
    public static void main(String[] args) throws IOException {

        Trainer t = new Trainer();

        // Recopila la informacion de los demas usuarios
        t.otherUsers();

        // Informacion del usuario
        t.InPut();
        // Analisis Estilografico
        Python3Lexer lexer = new Python3Lexer(CharStreams.fromFileName("source/in.txt"));
        CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);
        Python3Parser parser = new Python3Parser(tokens);
        ParseTree tree = parser.file_input();
        MyVisitor<Object> loader = new MyVisitor<Object>();
        loader.visit(tree);


        // Resultados del Analisis Estilografico
        t.OutPut();
        System.out.println();
        System.out.println("Codigos tomados de Google CodeJam 2017");
        System.out.println("https://www.go-hero.net/jam/17/solutions/0/3/Python");

    }
}