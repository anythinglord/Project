import org.antlr.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Trainer {
    public static void main(String[] args) throws IOException {

        Python3Lexer lexer = new Python3Lexer(CharStreams.fromFileName("/home/jesus/IdeaProjects/Project/source/in.txt"));
        CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);
        Python3Parser parser = new Python3Parser(tokens);
        ParseTree tree = parser.file_input();
        MyVisitor<Object> loader = new MyVisitor<Object>();
        loader.visit(tree);
        //System.out.println(tree);
    }
}