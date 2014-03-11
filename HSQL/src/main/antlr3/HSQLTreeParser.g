tree grammar HSQLTreeParser;
options{
    tokenVocab=HSQLParser;
    ASTLabelType=CommonTree;
}
@header{
package org.ict.hsql.parse;
import org.ict.hsql.plan.*;
import org.ict.hsql.schema.*;
import org.ict.hsql.query.*;
import org.ict.hsql.utils.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.Algorithm.*;
}
@member{
    QueryPlan plan;
}
sql_stat
    returns[QueryPlan plan]
    :c=create_stat {$plan=$c.plan;}
    |d=drop_stat {$plan=$d.plan;}
    |i=insert_stat {$plan=$i.plan;}
    |s=select_stat {$plan=$s.plan;}
    |de=delete_stat {$plan=$de.plan;}
    |u=update_stat {$plan=$u.plan;}
    |ci=create_index {$plan=$ci.plan;}
    ;
create_index
    returns[CreateIndexPlan plan]
    @init{
        $plan=new CreateIndexPlan();
    }
    :^(INDEX in=index_name tn=table_name)
    {
        $plan.setProperty(HSQLPropertyConstants.TABLENAME,$tn.tableName);
        $plan.setProperty(HSQLPropertyConstants.INDEXNAME,$in.indexName);
    }
    ;
update_stat
    returns[UpdatePlan plan]
    @init{
        $plan=new UpdatePlan();
    }
    :
    ^(UPDATE tn=table_name ul=update_list w=where_clause?)
    {
        $plan.setProperty(HSQLPropertyConstants.TABLENAME,$tn.tableName);
        $plan.setProperty(HSQLPropertyConstants.UPDATE,$ul.cv);
    }
    ;
update_list
    returns[List<ColumnValueRef> cv]
    @init{
        $cv=new ArrayList<ColumnValueRef>();

    }
    :
    ^(SET a=update_element{$cv.add($a.c);} (b=update_element{$cv.add($b.c);})* )

    ;
update_element
    returns[ColumnValueRef c]
    :
    ^(EQ cn=column  v=expression)
    {

        ColumnRef cr=new ColumnRef($cn.name);
        $c=new ColumnValueRef(cr,$v.v);

    }
    ;
expression
    returns[ValueRef v]
    :l=literals {$v=$l.v;}
    ;
where_clause
    returns[HSQLCondition cond]
    :^(WHERE c=andCondition){$cond=$c.cond;}
    ;
andCondition
    returns[HSQLCondition cond]
    @init{
        $cond=new HSQLAndCondition();
    }
    :a=orCondition {$cond.add($a.cond);}(AND^ b=orCondition {$cond.add($b.cond);})*
    ;
orCondition
    returns[HSQLCondition cond]
    @init{
        $cond=new HSQLOrCondition();
    }
    :a=atomCondition {$cond.add($a.cond);} (OR^ b=atomCondition {$cond.add($b.cond);})*
    ;
atomCondition
    returns[HSQLCondition cond]
    : ^(op=comp_op c=column v=expression) {$cond=new HSQLAtomCondition(new ColumnRef($c.name),$v.v,$op.op);}
    //| cl=condition_list {$cond=$cl.cond;}
    //|^(NOT n=condition) {$cond=new HSQLNotCondition($n.cond);}
    |L_PAREN a=andCondition R_AREN {$cond=$a.cond;}
    ;
delete_stat
    returns[DeletePlan plan]
    @init{
        $plan=new DeletePlan();
    }
    :^(DELETE tn=table_name w=where_clause?)
    {
        $plan.setProperty(HSQLPropertyConstants.TABLENAME,$tn.tableName);
        if(w!=null){
            $plan.setProperty(HSQLPropertyConstants.WHERE,$w.cond);
        }
    }
    ;


comp_op
    returns[CompareFilter.CompareOp op]
    :
    EQ {$op=CompareFilter.CompareOp.EQUAL;}
    |NEQ {$op= CompareFilter.CompareOp.NOT_EQUAL;}
    |LT  {$op=   CompareFilter.CompareOp.LESS;}
    |LTE {$op=  CompareFilter.CompareOp.LESS_OR_EQUAL;}
    |GT  {$op=  CompareFilter.CompareOp.GREATER; }
    |GTE {$op=  CompareFilter.CompareOp.GREATER_OR_EQUAL;}

    ;
select_stat
    returns[SelectPlan plan]
    @init{
        $plan=new SelectPlan();
        HSQLCondition cond=null;
    }
    :^(SELECT s=select_list tn=table_name (w=where_clause {cond=$w.cond;})?)
    {
        $plan.setProperty("TABLENAME",$tn.tableName);
        $plan.setProperty(HSQLPropertyConstants.PROJECTORS,$s.projectors);

        if(cond!=null){
            $plan.setProperty(HSQLPropertyConstants.WHERE,cond);
        }

    }
    ;

select_list
    returns[List<Projector> projectors]
    @init{
        $projectors=new ArrayList<Projector>();
    }
    :
    ^(SELECT_LIST (p=projector{$projectors.add($p.p);})+)
    ;


projector
    returns[Projector p]
    :
    c=column{$p=new Projector($c.name);}
    |^(f=fun c=column) {$p=new Projector($c.name,$f.fun);}
    ;
fun
    returns[Func fun]
    :SUM {$fun=new Func(Func.TYPE.SUM);}
    ;

create_stat
    returns[CreateTablePlan plan]
    @init{
        $plan=new CreateTablePlan();
    }
    :^(CREATE tn=table_name cl=column_withtype_list)
     {
        $plan.setProperty("TABLENAME",$tn.tableName);
        $plan.setProperty("TABLESCHEMA",new TableSchema($tn.tableName,$cl.columns));
     }
    ;
insert_stat
    returns[InsertRecordsPlan plan]
    @init{
        $plan=new InsertRecordsPlan();
    }
    :
    ^(INSERT tn=table_name vl=literal_list)
    {
        $plan.setProperty("TABLENAME",$tn.tableName);
        $plan.setProperty("VALUELIST",$vl.valueList);
    }
    ;
drop_stat
    returns[DropTablePlan plan]
    @init{
        $plan=new DropTablePlan();
    }
    :^(DROP tn=table_name)
    {
      $plan.setProperty("TABLENAME",$tn.tableName);
    }
    ;
table_name
    returns[String tableName]
    :STR  {$tableName=$STR.text;}
    ;
index_name
    returns[String indexName]
    :STR {$indexName=$STR.text;}
    ;
column_withtype_list
    returns[List<ColumnRef> columns]
    @init{
        $columns=new ArrayList<ColumnRef>();
    }
    :a=column_withtype{$columns.add($a.cr);} (COMMA! b=column_withtype{$columns.add($b.cr);})*
    ;
column_withtype
    returns[ColumnRef cr]
    :n=column t=type
    {$cr=new ColumnRef($n.name,$t.t);}
    ;
column
    returns[String name]
    :STR {$name=$STR.text;}
    ;
type
    returns[int t]
    :INT{$t=ColumnRef.Type.INT.ordinal();}
    |STRING {$t=ColumnRef.Type.STRING.ordinal();}
    |LONG {$t=ColumnRef.Type.LONG.ordinal();}
    |DOUBLE {$t=ColumnRef.Type.DOUBLE.ordinal();}
    ;
literals
    returns[ValueRef v]
    :
     i=INTEGER_LITERAL {$v=new ValueRef().setValue(Integer.valueOf($i.text));}
    |l=LONG_LITERAL    {$v=new ValueRef().setValue(Long.valueOf($l.text));}
    |d=DOUBLE_LITERAL  {$v=new ValueRef().setValue(Double.valueOf($d.text));}
    |s=STRING_LITERAL  {$v=new ValueRef().setValue($s.text);}
    ;
literal_list
    returns[List<ValueRef> valueList]
    @init{
        $valueList=new ArrayList<ValueRef>();
    }
    :a=literals{$valueList.add($a.v);}(COMMA b=literals{$valueList.add($b.v);})*
    ;