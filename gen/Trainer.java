import org.antlr.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Trainer {
    public static void main(String[] args) throws IOException {

        Python2Lexer lexer = new Python2Lexer(CharStreams.fromFileName("/home/jesus/IdeaProjects/PsiCoder2/source/in.txt"));
        CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);
        Python2Parser parser = new Python2Parser(tokens);
        ParseTree tree = parser.single_input();
        MyVisitor<Object> loader = new MyVisitor<Object>();
        loader.visit(tree);
        //System.out.println(tree);
    }
}