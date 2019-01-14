import org.antlr.v4.runtime.misc.ObjectEqualityComparator;
import org.stringtemplate.v4.ST;

import java.sql.SQLOutput;
import java.util.HashMap;

// realizado por Samael Salcedo y Camilo Nieto
public class MyVisitor<T> extends Python3BaseVisitor<T> {

    public static HashMap<String,Object> LocalVar = new HashMap<>();
    public static String ident = "";

    @Override
    public T visitSingle_input(Python3Parser.Single_inputContext ctx) {
        System.out.println("single input");
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitFile_input(Python3Parser.File_inputContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitEval_input(Python3Parser.Eval_inputContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitDecorator(Python3Parser.DecoratorContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitDecorators(Python3Parser.DecoratorsContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitDecorated(Python3Parser.DecoratedContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitAsync_funcdef(Python3Parser.Async_funcdefContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitFuncdef(Python3Parser.FuncdefContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitParameters(Python3Parser.ParametersContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitTypedargslist(Python3Parser.TypedargslistContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitTfpdef(Python3Parser.TfpdefContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitVarargslist(Python3Parser.VarargslistContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitVfpdef(Python3Parser.VfpdefContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitStmt(Python3Parser.StmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitSimple_stmt(Python3Parser.Simple_stmtContext ctx) {
        //System.out.println("simple stmt");
        return (T)visitChildren(ctx);
    }

    @Override public T visitSmall_stmt(Python3Parser.Small_stmtContext ctx) {
        System.out.println("small stmt");
        return (T)visitChildren(ctx);
    }

    @Override public T visitExpr_stmt(Python3Parser.Expr_stmtContext ctx) {
        System.out.println("expr stmt");
        System.out.println("ctx ----->: "+ctx.getText());
        if(ctx.getText().contains("=")){
            String var = (String)visitTestlist_star_expr(ctx.testlist_star_expr(0));
            System.out.println("VAR.---->: "+var);
            String value = (String)visitTestlist_star_expr(ctx.testlist_star_expr(1));
            //System.out.println("VALUE.---->: "+value);
            Object a = value;
            System.out.println("var: "+var+",value: "+value);
            System.out.println("IDENT: "+ident);
            if(ident.equals("ID")) {
                if (LocalVar.get(value) == null)
                    System.out.println(value + " is not defined");
                else
                    LocalVar.put(var, LocalVar.get(value));

            }else
                LocalVar.put(var,a);

            System.out.println("lo que hay en var: "+LocalVar.get(var));
        }else {
            System.out.println("children of expr stmt");
            return (T) visitChildren(ctx);
        }
        return null;
    }

    @Override public T visitAnnassign(Python3Parser.AnnassignContext ctx) {
        //System.out.println("Annassign");
        return (T)visitChildren(ctx);
    }

    @Override public T visitTestlist_star_expr(Python3Parser.Testlist_star_exprContext ctx) {
        System.out.println("Testlist star expr");
        return (T)visitChildren(ctx);
    }

    @Override public T visitAugassign(Python3Parser.AugassignContext ctx) {
        //System.out.println("Augassign");
        return (T)visitChildren(ctx);
    }

    @Override public T visitDel_stmt(Python3Parser.Del_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitPass_stmt(Python3Parser.Pass_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitFlow_stmt(Python3Parser.Flow_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitBreak_stmt(Python3Parser.Break_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitContinue_stmt(Python3Parser.Continue_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitReturn_stmt(Python3Parser.Return_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitYield_stmt(Python3Parser.Yield_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitRaise_stmt(Python3Parser.Raise_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }
    @Override public T visitImport_stmt(Python3Parser.Import_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitImport_name(Python3Parser.Import_nameContext ctx) {
        return (T)visitChildren(ctx);
    }
    @Override public T visitImport_from(Python3Parser.Import_fromContext ctx) {
        return (T)visitChildren(ctx);
    }
    @Override public T visitImport_as_name(Python3Parser.Import_as_nameContext ctx) {
        return (T)visitChildren(ctx);
    }
    @Override public T visitDotted_as_name(Python3Parser.Dotted_as_nameContext ctx) {
        return (T)visitChildren(ctx);
    }
    @Override public T visitImport_as_names(Python3Parser.Import_as_namesContext ctx) {
        return (T)visitChildren(ctx);
    }
    @Override public T visitDotted_as_names(Python3Parser.Dotted_as_namesContext ctx) {
        return (T)visitChildren(ctx);
    }
    @Override public T visitDotted_name(Python3Parser.Dotted_nameContext ctx) {
        return (T)visitChildren(ctx);
    }
    @Override public T visitGlobal_stmt(Python3Parser.Global_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }
    @Override public T visitNonlocal_stmt(Python3Parser.Nonlocal_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }
    @Override public T visitAssert_stmt(Python3Parser.Assert_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitCompound_stmt(Python3Parser.Compound_stmtContext ctx) {
        //System.out.println("Compound");
        return (T)visitChildren(ctx);
    }
    @Override public T visitAsync_stmt(Python3Parser.Async_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitIf_stmt(Python3Parser.If_stmtContext ctx) {
        System.out.println("If");
        if(ctx.IF()!=null){
            Boolean op = (Boolean) visitTest(ctx.test(0));
            boolean nothing = false;
            int times = 0;
            //System.out.println("lo que esta en if de compari:" +op);
            if(op !=null){
                if(op){
                    visitSuite(ctx.suite(0));
                }else if(ctx.ELIF().size()>0){
                    for (int i = 0; i <ctx.ELIF().size(); i++) {
                        System.out.println("ElIF number "+i);
                        op = (Boolean) visitTest(ctx.test(i));
                        if(op){
                            visitSuite(ctx.suite(i));
                            break;
                        }else
                            times++;
                    }
                    if(times == ctx.ELIF().size())
                        nothing = true;
                }else
                    nothing = true;


                if (nothing && ctx.suite().size()>1){
                    if(ctx.ELIF().size()>0)
                        visitSuite(ctx.suite(ctx.ELIF().size()+1));
                    else
                        visitSuite(ctx.suite(1));
                }
            }
        }
        //return (T)visitChildren(ctx);
        return null;
    }

    @Override
    public T visitWhile_stmt(Python3Parser.While_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitFor_stmt(Python3Parser.For_stmtContext ctx) {
        //System.out.println("in For stmt");
        if(ctx.FOR()!=null){
            int u ;
            String t = String.valueOf(0);
            LocalVar.put((String) visitExprlist(ctx.exprlist()),(Object)t);
            //String u = (String) visitExprlist(ctx.exprlist());
            String rango = (String) visitTestlist(ctx.testlist());
            int f = 0;
            if(rango.charAt(0)>=48 && rango.charAt(0)<= 57)
                f = Integer.parseInt(rango);
            else{
                if(LocalVar.containsKey(rango))
                    f = Integer.parseInt((String) LocalVar.get(rango));
                else
                    System.out.println(rango+" is not defined");
            }

            System.out.println("la f "+f);
            //int f = Integer.parseInt((String) visitTestlist(ctx.testlist()));
            for (u = 0; u < f ; u++) {
                visitSuite(ctx.suite(0));
                t = String.valueOf(u+1);
                LocalVar.put((String) visitExprlist(ctx.exprlist()),(Object)t);
            }
        }
        return null;
    }

    @Override
    public T visitTry_stmt(Python3Parser.Try_stmtContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitWith_stmt(Python3Parser.With_stmtContext ctx) {
        System.out.println("With_stmt");

        return (T)visitChildren(ctx);
    }

    @Override
    public T visitWith_item(Python3Parser.With_itemContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitExcept_clause(Python3Parser.Except_clauseContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitSuite(Python3Parser.SuiteContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitTest(Python3Parser.TestContext ctx) {
        System.out.println("test");

        return (T)visitChildren(ctx);
    }

    @Override
    public T visitTest_nocond(Python3Parser.Test_nocondContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitLambdef(Python3Parser.LambdefContext ctx) {

        if (ctx.LAMBDA()!=null){
            return (T) visitVarargslist(ctx.varargslist());
        }
        return (T) super.visitLambdef(ctx);
    }

    @Override
    public T visitLambdef_nocond(Python3Parser.Lambdef_nocondContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitOr_test(Python3Parser.Or_testContext ctx) {
        System.out.println("Or test");

        return (T)visitChildren(ctx);
    }

    @Override
    public T visitAnd_test(Python3Parser.And_testContext ctx) {
        System.out.println("And test");

        return (T)visitChildren(ctx);
    }
    @Override
    public T visitNot_test(Python3Parser.Not_testContext ctx) {

        System.out.println("Not test");

        if(ctx.NOT()!=null)
            return (T)visitNot_test(ctx.not_test());
        else
            //System.out.println("else in Not test");
            return (T)visitChildren(ctx);
    }

    //Funcion que nos permite comparar dos numeros
    public Boolean compare(double n1,double n2,String comp){
        Boolean ans = null;
        switch (comp){
            case "<": ans = n1 < n2; break;
            case ">": ans = n1 > n2; break;
            case "<=": ans = n1 <= n2; break;
            case ">=": ans = n1 >= n2; break;
            case "==": ans = Math.abs(n1-n2)<0.000000001; break;
            case "!=": ans = Math.abs(n1-n2)>0.000000001; break;
        }
        return ans;
    }

    @Override
    public T visitComparison(Python3Parser.ComparisonContext ctx) {

        System.out.println("comparison");
        if (ctx.expr(1) != null){

            String expr0 = ctx.expr(0).getText();
            String expr1 = ctx.expr(1).getText();
            String comp  = ctx.comp_op(0).getText();
            Boolean ans = null;

            if (expr0.charAt(0)>=48 && expr0.charAt(0)<=57){
                if (expr1.charAt(0)>=48 && expr1.charAt(0)<=57){
                    double n1 = Double.parseDouble(expr0);
                    double n2 = Double.parseDouble(expr1);
                    System.out.println("Ambos Numeros");
                    return (T)compare(n1,n2,comp);
                }else{
                    double n1 = Double.parseDouble(expr0);
                    if(LocalVar.get(expr1)==null){
                        System.out.println(expr1+" is not defined");
                        return (T)null;
                    }
                    else{
                        double n2 = Double.parseDouble((String) LocalVar.get(expr1));;
                        return (T)compare(n1,n2,comp);
                    }
                }
            }else{
                //puede ser variable u otra cosa
                if (expr1.charAt(0)>=48 && expr1.charAt(0)<=57){
                    System.out.println("segundo numero el primero no");
                    double n2 = Double.parseDouble(expr1);
                    if(LocalVar.get(expr0)==null){
                        System.out.println(expr0+" is not defined");
                        return (T)null;
                    }
                    else{
                        //double n1 = (double)LocalVar.get(expr0);
                        double n1 = Double.parseDouble((String) LocalVar.get(expr0));
                        return (T)compare(n1,n2,comp);
                    }

                }else{
                    //puede ser variable u otra cosa
                    if(LocalVar.get(expr0) == null){

                        if(LocalVar.get(expr1) == null){
                            System.out.println(expr1+" is not defined");
                            return (T)null;
                        }else{
                            System.out.println(expr0+" is not defined");
                            return (T)null;
                        }


                    }
                    else{
                        double n1 = (double)LocalVar.get(expr0);
                        if(LocalVar.get(expr1) == null){
                            System.out.println(expr1+" is not defined");
                            return (T)null;
                        }else{
                            double n2 = (double)LocalVar.get(expr1);
                            return (T)compare(n1,n2,comp);
                        }
                    }
                }
            }
        }else{
            System.out.println("not comparison of if");
            return (T)visitChildren(ctx);
        }
        //return null;
    }
    @Override
    public T visitComp_op(Python3Parser.Comp_opContext ctx) {
        //System.out.println("Comp_op");
        return  (T) ctx.getText();
        //return (T)visitChildren(ctx);
    }

    @Override
    public T visitStar_expr(Python3Parser.Star_exprContext ctx) {
        //System.out.println("Star expr");
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitExpr(Python3Parser.ExprContext ctx) {
        System.out.println("Expr");

        return (T)visitChildren(ctx);
    }

    @Override
    public T visitXor_expr(Python3Parser.Xor_exprContext ctx) {
        System.out.println("Xor-expr");
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitAnd_expr(Python3Parser.And_exprContext ctx) {
        System.out.println("And-expr");
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitShift_expr(Python3Parser.Shift_exprContext ctx) {
        System.out.println("shift-expr");
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitArith_expr(Python3Parser.Arith_exprContext ctx) {
        //System.out.println("arith-expr");
        if(ctx.term().size()>1){

            double sum = 0;
            for (int i = 0; i < ctx.term().size() ; i++) {
                String term = (String) visitTerm(ctx.term(i));
                //System.out.println("TERM-------->: "+term);
                if(ctx.getText().contains("+")){
                    if(term.charAt(0) >= 48 && term.charAt(0) <= 57 )
                        sum += Double.parseDouble(term);
                    else{
                        if(LocalVar.containsKey(term))
                            sum +=Double.parseDouble((String) LocalVar.get(term));
                        else
                            System.out.println(term+" is not defined");
                    }
                }
                else{
                    if(term.charAt(0) >= 48 && term.charAt(0) <= 57 )
                        sum -= Double.parseDouble(term);
                    else{
                        if(LocalVar.containsKey(term))
                            sum -=Double.parseDouble((String) LocalVar.get(term));
                        else
                            System.out.println(term+" is not defined");
                    }
                }
            }
            ident = "NUMBER";
            /*System.out.println("term 0:"+ctx.term(0).getText());
            System.out.println("term 1:"+ctx.term(1).getText());*/
            return (T)String.valueOf(sum);
        }else
            return (T)visitChildren(ctx);

    }
    @Override
    public T visitTerm(Python3Parser.TermContext ctx) {
        //System.out.println("Term");
        if(ctx.factor().size()>1){

            double mul = 1;
            for (int i = 0; i < ctx.factor().size() ; i++) {

                String factor = (String) visitFactor(ctx.factor(i));
                //System.out.println("FActor-------->: "+factor);
                if(ctx.getText().contains("*")){
                    if(factor.charAt(0) >= 48 && factor.charAt(0) <= 57 )
                        mul *= Double.parseDouble(factor);
                    else{
                        if(LocalVar.containsKey(factor))
                            mul *=Double.parseDouble((String) LocalVar.get(factor));
                        else
                            System.out.println(factor+" is not defined");
                    }
                }

                else if(ctx.getText().contains("/")){
                    if(factor.charAt(0) >= 48 && factor.charAt(0) <= 57 )
                        mul /= Double.parseDouble(factor);
                    else{
                        if(LocalVar.containsKey(factor))
                            mul /=Double.parseDouble((String) LocalVar.get(factor));
                        else
                            System.out.println(factor+" is not defined");
                    }
                }
                else if(ctx.getText().contains("%")){
                    if(factor.charAt(0) >= 48 && factor.charAt(0) <= 57 )
                        mul %= Double.parseDouble(factor);
                    else{
                        if(LocalVar.containsKey(factor))
                            mul %=Double.parseDouble((String) LocalVar.get(factor));
                        else
                            System.out.println(factor+" is not defined");
                    }
                }
            }
            System.out.println("MULsdfasdfasdf--->: "+mul);
            ident = "NUMBER";
            //System.out.println("term 0:"+ctx.term(0).getText());
            //System.out.println("term 1:"+ctx.term(1).getText());
            return (T)String.valueOf(mul);
        }else
            return (T)visitChildren(ctx);
    }

    @Override
    public T visitFactor(Python3Parser.FactorContext ctx) {
        System.out.println("factor");
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitPower(Python3Parser.PowerContext ctx) {
        System.out.println("power");
        return (T) visitChildren(ctx);
    }

    @Override
    public T visitAtom_expr(Python3Parser.Atom_exprContext ctx) {
        System.out.println("Atom_expr");
        if(ctx.trailer().size()>=1){
            System.out.println("en el Trailer");
            String fun = (String) visitAtom(ctx.atom());
            String cont = "";
            if (visitTrailer(ctx.trailer(0))==null)
                cont = (String) visitArglist(ctx.trailer(0).arglist());
            else
                cont = (String) visitTrailer(ctx.trailer(0));

            //System.out.println("----->"+fun+" "+cont);
            if(fun.equals("print")) {
                switch (ident) {
                    case "ID":
                        //System.out.println("cont:_ "+cont);
                        if (LocalVar.containsKey(cont)) {
                            String a = (String) LocalVar.get(cont);
                            if (a.charAt(0) == 34)
                                System.out.println(a.substring(1, a.length() - 1));
                            else
                                System.out.println(LocalVar.get(cont));

                        } else
                            System.out.println(cont + " is not defined");

                        break;
                    case "NUMBER":
                        System.out.println(cont);
                        break;
                    case "STRING":
                        System.out.println(cont.substring(1, cont.length() - 1));
                        break;
                }
                return null;
            }

            else if(fun.equals("range")){
                //System.out.println("is a range ----------------->");
                return (T)visitArglist(ctx.trailer(0).arglist());
            }else{
                System.out.println("otra cosa");
                return null;
            }

        }else{
            //System.out.println("children of atom_expr");
            return (T)visitChildren(ctx);
        }


    }

    @Override
    public T visitAtom(Python3Parser.AtomContext ctx) {
        System.out.println("Atom");
        if(ctx.NAME()!=null){
            System.out.println("ID");
            ident = "ID";
            return (T)ctx.NAME().getText();
        }else if(ctx.NUMBER()!=null){
            System.out.println("NUMBER");
            ident = "NUMBER";
            return (T)ctx.NUMBER().getText();
        }
        else if(ctx.STRING()!=null){
            System.out.println("STRING");
            ident = "STRING";
            return (T)ctx.STRING(0).getText();
        }
        else{
            return (T)visitChildren(ctx);

        }
    }

    @Override
    public T visitTestlist_comp(Python3Parser.Testlist_compContext ctx) {
        //System.out.println("TestList_comp");
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitTrailer(Python3Parser.TrailerContext ctx) {
        //System.out.println("Trailer");
        return (T)visitChildren(ctx);
    }

    @Override
    public T visitSubscriptlist(Python3Parser.SubscriptlistContext ctx) {
        return (T)visitChildren(ctx);
    }

    @Override public T visitSubscript(Python3Parser.SubscriptContext ctx) { return (T)visitChildren(ctx); }

    @Override public T visitSliceop(Python3Parser.SliceopContext ctx) { return (T)visitChildren(ctx); }
    @Override public T visitExprlist(Python3Parser.ExprlistContext ctx) {
        //System.out.println("Exprlist");
        return (T)visitChildren(ctx);
    }
    @Override public T visitTestlist(Python3Parser.TestlistContext ctx) {
        //System.out.println("TestList");
        return (T)visitChildren(ctx);
    }
    @Override
    public T visitDictorsetmaker(Python3Parser.DictorsetmakerContext ctx) {
        //System.out.println("Dictorsetmaker");
        return (T)visitChildren(ctx);
    }
    @Override public T visitClassdef(Python3Parser.ClassdefContext ctx) { return (T)visitChildren(ctx); }

    @Override
    public T visitArglist(Python3Parser.ArglistContext ctx) {
        //System.out.println("Arglist");
        return (T)visitChildren(ctx);
    }
    @Override public T visitArgument(Python3Parser.ArgumentContext ctx) { return (T)visitChildren(ctx); }
    @Override public T visitComp_iter(Python3Parser.Comp_iterContext ctx) { return (T)visitChildren(ctx); }
    @Override public T visitComp_for(Python3Parser.Comp_forContext ctx) { return (T)visitChildren(ctx); }
    @Override public T visitComp_if(Python3Parser.Comp_ifContext ctx) { return (T)visitChildren(ctx); }
    @Override public T visitEncoding_decl(Python3Parser.Encoding_declContext ctx) { return (T)visitChildren(ctx); }
    @Override public T visitYield_expr(Python3Parser.Yield_exprContext ctx) { return (T)visitChildren(ctx); }
    @Override public T visitYield_arg(Python3Parser.Yield_argContext ctx) { return (T)visitChildren(ctx); }
}
