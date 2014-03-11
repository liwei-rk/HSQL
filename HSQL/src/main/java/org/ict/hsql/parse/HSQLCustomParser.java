package org.ict.hsql.parse;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.ict.hsql.exception.HSQLException;
import org.ict.hsql.plan.QueryPlan;

/**
 * Created with IntelliJ IDEA.
 * User: lwVampire
 * Date: 13-11-24
 * Time: 下午5:31
 * To change this template use File | Settings | File Templates.
 */
public class HSQLCustomParser {
    public static QueryPlan compile(String sql) throws HSQLException {
        ANTLRStringStream str = new ANTLRStringStream(sql);
        HSQLLexer lexer = new HSQLLexer(str);
        HSQLParser parser = new HSQLParser(new CommonTokenStream(lexer));
        try {
            CommonTree tree = (CommonTree) (parser.sql_stat().getTree());
            CommonTreeNodeStream treeNodeStream = new CommonTreeNodeStream(tree);
            HSQLTreeParser walker = new HSQLTreeParser(treeNodeStream);
            QueryPlan plan = walker.sql_stat().plan;
            return plan;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSQLException(HSQLException.HSQLExceptionCode.INVALID_SQL,sql);
        }
    }
}
